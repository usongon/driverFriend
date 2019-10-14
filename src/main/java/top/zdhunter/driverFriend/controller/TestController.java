package top.zdhunter.driverFriend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangdehua
 * @date 2019-10-15
 */
@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello,world!";
    }
}
