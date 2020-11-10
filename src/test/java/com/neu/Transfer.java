package com.neu;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：lychallengers@gmail.com
 * @date ：2020/11/10 21:25
 */
public class Transfer {
        public static void main(String[] args)  {
            //string构筑json
            String json = "{\"2\":\"efg\",\"1\":\"abc\"}";
            JSONObject json_test = JSONObject.fromObject(json);
            System.out.print("json_test:" + json_test);
            System.out.print("\n");
//使用list构筑json(list只能使用JSONArray)
            List<String> list = new ArrayList<String>();
            list.add("username");
            list.add("age");
            list.add("sex");
            JSONArray array = new JSONArray();
            array.add(list);
            System.out.print("array:" + array);
            System.out.print("\n");
            //初始化HashMap集合并添加数组(json必须键不能是int类型)
            Map<String, String> map = new HashMap<String, String>();
            map.put("1", "abc");
            map.put("2", "efg");
            JSONArray array_test = new JSONArray();
            array_test.add(map);
            System.out.print("array_test:" + array_test);
            System.out.print("\n");
            JSONObject jsonObject = JSONObject.fromObject(map);
            System.out.print("jsonObject:" + jsonObject);
            System.out.print("\n");
            //解析json
            JSONObject string_to_json = JSONObject.fromObject("{\"data\": {\"pages\": [ {\"comment\": \"just for test1\"},{\"comment\": \"just for test2\"}],\"total_count\": 2 },\"errcode\": 0}");
            JSONObject json_to_data = string_to_json.getJSONObject("data");
            JSONArray json_to_strings = json_to_data.getJSONArray("pages");
            for (Object object : json_to_strings) {
                JSONObject json_to_string = JSONObject.fromObject(object);
                json_to_string.get("pages");
                System.out.print("json_to_string.get(\"pages\"):" + json_to_string.get("comment"));
                System.out.print("\n");
            }
            JSONObject json_to_data1 = string_to_json.getJSONObject("data");
            JSONArray json_to_strings_test = json_to_data1.getJSONArray("pages");
            for (int i = 0; i < json_to_strings_test.size(); i++) {
                JSONObject json_to_string1 = json_to_strings_test.getJSONObject(i);
                System.out.print("json_to_string1.get(\"pages\"):" + json_to_string1.get("comment"));
                System.out.print("\n");
            }
        }

        }
