package com.usongon.driverFriend;

import com.usongon.driverFriend.bean.param.MessageInsertParams;
import com.usongon.driverFriend.dao.CompanyDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.usongon.driverFriend.bean.entity.CompanyEntity;
import com.usongon.driverFriend.common.utils.UuidUtil;
import com.usongon.driverFriend.service.MessageService;

import jakarta.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DriverFriendApplicationTests {

	@Resource
	private CompanyDao companyDao;
	@Resource
	private MessageService messageService;
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

	@Test
	public void insertMessageTest(){
		MessageInsertParams params = new MessageInsertParams();
		params.setSenderId("testSender");
		params.setReceiverId("testReceiver");
		params.setContent("testContent");
		params.setTarget("testTarget");

		messageService.send(params);
	}
}
