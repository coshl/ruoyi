package com.ruoyi.system.api;

import com.ruoyi.common.utils.http.ApiHttpUtils;
import com.ruoyi.common.utils.http.HttpUtils;

import java.util.HashMap;
import java.util.Map;


public class DeptApi {

    private static final String host = "http://47.57.187.27:81";

    public static String getPayOrder(String name){
        Map map  = new HashMap();
        map.put("name",name);
        String result = ApiHttpUtils.doGet(host+"/payOrder/getPayOrder", map);

        return result;
    }

    public static void main(String[] args) {
        System.out.println(getPayOrder("张三"));
        //{"code":0,"msg":"success","data":{"monthLoanOrder":0,"dayhRepayOrder":1,"monthRepayOrder":5,"dayLoanOrder":0,"orderByName":[{"id":85076,"createDate":"2023-02-03 01:40:03","lastUpdateDate":"2023-02-03
        //01:40:03","name":"张三","phone":"1","bankCode":"","status":"1","amount":"2000.00","merchant":"小宇宙（新）","orderDate":"2023-02-02 13:16:05"},{"id":78507,"createDate":"2023-02-02 21:52:03",
        //"lastUpdateDate":"2023-02-02 21:52:03","name":"张三","phone":"1","bankCode":"","status":"1","amount":"2000.00","merchant":"小宇宙（新）","orderDate":"2023-01-31 13:19:18"},{"id":76338,"createDate":"2023-02-02
        //21:09:03","lastUpdateDate":"2023-02-02 21:09:03","name":"张三","phone":"1","bankCode":"","status":"1","amount":"500.00","merchant":"小宇宙（新）","orderDate":"2023-01-30 20:07:45"},{"id":25286,
        //"createDate":"2023-02-02 04:07:02","lastUpdateDate":"2023-02-02 04:07:02","name":"张三","phone":"1","bankCode":"","status":"1","amount":"2000.00","merchant":"小宇宙（新）","orderDate":"2023-01-20 17:16:05"},
        //{"id":19458,"createDate":"2023-02-02 02:10:04","lastUpdateDate":"2023-02-02 02:10:04","name":"张三","phone":"1","bankCode":"","status":"1","amount":"2000.00","merchant":"小宇宙（新）","orderDate":"2023-01-19
        //13:01:34"}]}}
    }

}
