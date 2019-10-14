package top.zdhunter.driverFriend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.zdhunter.driverFriend.common.utils.PasswordUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DriverFriendApplicationTests {

	@Test
	public void contextLoads() {
		if (PasswordUtil.match("qwe1234","$2a$10$Ih97XW4i5WIlmjFr0g3GtuoQ7G5qzocn5b07OEwd.CogNysupN2v.")){
			System.out.println("卧槽无情");
		}
	}

}
