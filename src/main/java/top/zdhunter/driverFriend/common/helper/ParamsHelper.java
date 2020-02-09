package top.zdhunter.driverFriend.common.helper;

import com.alibaba.fastjson.JSONArray;
import org.springframework.util.StringUtils;
import top.zdhunter.driverFriend.common.utils.DateUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParamsHelper {

    /**
     * 处理搜索参数
     * @param params
     * @return
     */
    public static String processStrSearchParams(String params) {
        if (params == null) {
            return null;
        }

        params = params.trim();
        if (StringUtils.isEmpty(params)) {
            return null;
        }

        return params;
    }

    /**
     * 处理时间搜索参数
     * @param params
     * @return
     */
    public static LocalDateTime processTimeSearchParams(String params) {
        return StringUtils.isEmpty(params) ? null : DateUtil.parserLocalDateTime(params);
    }

    /**
     * 处理Json列表型参数
     * @param params
     * @return
     */
    public static List<String> processStrListSearchParams(String params) {
        params = processStrSearchParams(params);

        if (params == null) {
            return null;
        }

        JSONArray jsonArray = JSONArray.parseArray(params);
        if (jsonArray.size() == 0) {
            return null;
        }

        List<String> result = new ArrayList<>(jsonArray.size());
        for (int i = 0; i < jsonArray.size(); i++) {
            result.add(jsonArray.getString(i));
        }
        return result;
    }
}
