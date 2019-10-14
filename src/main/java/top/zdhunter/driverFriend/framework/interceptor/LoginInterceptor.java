package top.zdhunter.driverFriend.framework.interceptor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.zdhunter.driverFriend.bean.session.AdminSession;
import top.zdhunter.driverFriend.bean.session.LoginSession;
import top.zdhunter.driverFriend.bean.session.UserSession;
import top.zdhunter.driverFriend.common.helper.GlobalHelper;
import top.zdhunter.driverFriend.enums.EResponseCode;
import top.zdhunter.driverFriend.framework.annotation.Authorize;
import top.zdhunter.driverFriend.framework.exception.BusinessException;
import top.zdhunter.driverFriend.redis.SessionRedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private SessionRedis sessionRedis;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 有注解标明不需要登录则直接放行
        Authorize authorizeAnnotation = getAuthorizeByHandler(handler);
        if (authorizeAnnotation == null || !authorizeAnnotation.login()) {
            return true;
        }
        doCheckLogin(request.getRequestURI(), request.getHeader("token"));
        return true;
    }

    /**
     * 检查登录状态和CSRF
     */
    private void doCheckLogin(String uri, String token) {
        if (StringUtils.isEmpty(token)) {
            throw new BusinessException(EResponseCode.NoLogin, "未登录", "");
        }
        LoginSession session = sessionRedis.getSession(token);
        if (session != null) {
            if (uri.startsWith("/admin/") && !(session instanceof AdminSession)) {
                throw new BusinessException(EResponseCode.PermissionDenied, "无权限", "");
            }
            if (uri.startsWith("/employee/") && !(session instanceof UserSession)) {
                throw new BusinessException(EResponseCode.PermissionDenied, "无权限", "");
            }
            sessionRedis.setExpire(token);
            GlobalHelper.set(session);
        } else {
            throw new BusinessException(EResponseCode.NoLogin, "未登录", "");
        }
    }

    /**
     * 从handler中获取注解
     * @param handler
     * @return
     */
    private Authorize getAuthorizeByHandler(Object handler) {
        // 取出注解
        HandlerMethod method = (HandlerMethod) handler;
        Authorize authorizeAnnotation = method.getMethodAnnotation(Authorize.class);
        if (authorizeAnnotation == null) {
            authorizeAnnotation = method.getMethod().getDeclaringClass().getAnnotation(Authorize.class);
        }
        return authorizeAnnotation;
    }
}
