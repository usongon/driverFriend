package com.usongon.driverFriend.common.utils;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Random;

public class PasswordUtil {

    private static final String passwordSalt = "_m#CCt!lH4m";

    /**
     * 匹配密码
     * @param pwdInput
     * @param pwdStore
     * @return
     */
    public static boolean match(String pwdInput, String pwdStore) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(pwdInput + passwordSalt, pwdStore);
    }


    /**
     * 加密
     * @param pwdInput
     * @return
     */
    public static String encode(String pwdInput) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pwdInput + passwordSalt);
    }

    public static String create(){
        int count = 0;
        char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c',
                'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z'};
        StringBuffer pwd = new StringBuffer();
        Random r = new Random();
        while (count < 8) {
            int i = Math.abs(r.nextInt(str.length));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }
}
