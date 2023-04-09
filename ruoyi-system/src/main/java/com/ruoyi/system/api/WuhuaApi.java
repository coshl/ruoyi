package com.ruoyi.system.api;


import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

@Component
@Slf4j
public class WuhuaApi {

//http://122.226.223.231:5555/ext/v2/queryRadar
//mercode ： me-3NeuEpsHDznhz5tNWTCUkcNyM4CtEybGPq
//mercode ：
public  static  String getRadar(TreeMap map){
    //String mobile,String idCard,String name
    /*addParam("mobile", mobile).
            addParam("idCard", idCard).
            addParam("name", name).
            addParam("merCode", "me-1e73ec5fd48828c7ed5dd7b6e8fb8").*/
    map.put("merCode", "me-1e73ec5fd48828c7ed5dd7b6e8fb8");
    JSONObject jsonObject = JSONUtil.parseObj(map);
    log.info(jsonObject.toString());
    //String result = OkHttpUtils.builder().url("http://122.226.223.231:5555/ext/v2/queryRadar").
    String result = HttpRequest.post("http://122.226.223.231:5555/ext/v2/queryRadar").
            header("Content-Type", "application/json; charset=utf-8").
            header("appCode", "b809fe01f3c64c5284c06ff3c1fabe50").
            body(jsonObject.toString()).
            timeout(3000).//超时，毫秒
            execute().
            body();
    //System.out.println("getRadar==========="+result);
    //log.info("getRadar==========="+result);
    return result;

}

    public static void main(String[] args) {
        Map map = new TreeMap();
        map.put("mobile", "18640356167");
        map.put("idCard", "210114199702195425");
        map.put("name", "白桂莲");
        map.put("merCode", "me-3NeuEpsHDznhz5tNWTCUkcNyM4CtEybGPq");
        //form(map). 是表单 body(map).是json
        JSONObject jsonObject = JSONUtil.parseObj(map);
        System.out.println(jsonObject);
        String result = HttpRequest.post("http://122.226.223.231:5555/ext/v2/queryRadar").
                header("Content-Type", "application/json; charset=utf-8").
                header("appCode", "b809fe01f3c64c5284c06ff3c1fabe50").
                body(jsonObject.toString()).
                timeout(3000).//超时，毫秒
                execute().
                body();
        System.out.println(result);
    }


}
