package com.usongon.driverFriend.common.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class JsonUtil {
    // 线程安全的
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转Json
     * @param obj
     * @return
     */
    public static String toJson(Object obj){
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    /**
     * Json转对象
     * @param jsonStr
     * @param classType
     * @return
     */
    public static <T> T parseObject(String jsonStr, Class<T> classType){
        try {
            return objectMapper.readValue(jsonStr, classType);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 将 keyList转换成Json
     *
     * @param list
     * @return
     */
    public static String toAttachVOJson(List list) {
        if (list == null || list.size() == 0) {
            return "[]";
        }
        return JSON.toJSONString(list);
    }

}
