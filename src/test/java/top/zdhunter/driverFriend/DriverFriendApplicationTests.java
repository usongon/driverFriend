package top.zdhunter.driverFriend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.zdhunter.driverFriend.bean.entity.CompanyEntity;
import top.zdhunter.driverFriend.common.utils.PasswordUtil;
import top.zdhunter.driverFriend.common.utils.UuidUtil;
import top.zdhunter.driverFriend.dao.CompanyDao;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DriverFriendApplicationTests {

	@Resource
	private CompanyDao companyDao;
	@Test
	public void contextLoads() {
		CompanyEntity entity = new CompanyEntity();
		entity.setCompanyId(UuidUtil.randomUUID());
		entity.setCompanyBoss("1e10d1f4817b40569ae062439040845c");
		entity.setCompanyName("青桔乐园");
		entity.setCompanyMobile("0476-3885561");
		entity.setCompanyCity("赤峰市-内蒙古自治区");
		entity.setCompanyAddress("中通快递-西桥镇-喀喇沁旗");
		entity.setCompanyLogo("test");
//		companyDao.addCompany(UuidUtil.randomUUID(), "1e10d1f4817b40569ae062439040845c", "青桔乐园", "0476-3885561",
//				"赤峰市-内蒙古自治区", "中通快递-西桥镇-喀喇沁旗", "test");
        companyDao.addCompany(entity);
	}

}
