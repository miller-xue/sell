package com.xue.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Json工具
 * Created by miller on 2018/5/12
 */
public class JsonUtil {

    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
