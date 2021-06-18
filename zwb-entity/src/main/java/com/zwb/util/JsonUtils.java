package com.zwb.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.utils
 * @Author: bruceliu
 * @QQ:944750010
 * @CreateTime: 2020-04-03 10:20
 * @Description: Json解析工具类
 */
public class JsonUtils {


        /**
         * 将String类型转换为json字符串
         *
         * @param object
         * @return
         */
        public static Object strJson(String object) {
            Object o = JSON.toJSONString(object);
            return o;
        }

        /**
         * 将List 集合转换成json字符串
         *
         * @param list
         * @return
         */
        public static String listJson(List list) {
            String listJson = JSON.toJSONString(list);
            return listJson;
        }

        /**
         * 将map 转换成json
         *
         * @param map
         * @return
         */
        public static Object mapJson(Map map) {
            String mapJson = JSON.toJSONString(map);
            return mapJson;
        }

        /**
         * 将josn字符串转成list<T>集合
         *
         * @param list
         * @param tClass pojo对象
         * @param <T>
         * @return
         */
        public static <T> List<T> strListToList(String list, Class<T> tClass) {
            List<T> strListToList = (List<T>) JSONArray.parseArray(list, tClass);
            return strListToList;
        }

        /**
         * 将json字符串转换为map
         *
         * @param strMap 序列化的map
         * @return
         */
        public static Map strMapToMap(String strMap) {
            Map map = JSONObject.parseObject(strMap, Map.class);
            return map;
    }
    //原字符串：[{"id":23,"text":"美特斯邦威"},{"id":24,"text":"森马"},{"id":32,"text":"阿迪达斯"},{"id":42,"text":"花花公子"}]
    //结果: 美特斯邦威,森马,阿迪达斯,花花公子
    public static String parseJson(String json){
        ObjectMapper objectMapper=new ObjectMapper();
        StringBuffer sb=new StringBuffer();
        try {
            List<JsonObject> brandObjects= objectMapper.readValue(json,new TypeReference<ArrayList<JsonObject>>(){});
            for (JsonObject brandObject : brandObjects) {
               if(brandObject!=null){
                   String text = brandObject.getText();
                   sb.append(text+",");
               }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = sb.toString();
        s=s.substring(0,s.length()-1);
        return s;
    }

    public static List<JsonObject> parsejson2List(String json){
        ObjectMapper objectMapper=new ObjectMapper();
        List<JsonObject> list=null;
        try {
            list= objectMapper.readValue(json,new TypeReference<ArrayList<JsonObject>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * json转map
     * @param json
     * @return
     */
    public static Map json2Map(String json){
    	Map mps=null;
    	ObjectMapper objectMapper=new ObjectMapper();
    	try {
    		mps= objectMapper.readValue(json,new TypeReference<Map>(){});
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return mps;
    }
    
    
}

