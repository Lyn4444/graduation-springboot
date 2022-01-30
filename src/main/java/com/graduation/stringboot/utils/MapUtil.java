package com.graduation.stringboot.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * MapUtil
 *
 * @author Lyn
 * @date 2022/01/30
 */
public class MapUtil {

    public static Map<String, Object> stringSplit(String s) {
        if (s.length() > 0) {
            Map<String, Object> res = new HashMap<>();
            String[] tmp = s.split(",");
            for (String t: tmp) {
                String[] tmp1 = t.split("=");
                res.put(tmp1[0], tmp1[1]);
            }
            System.out.println(res.toString());
            return res;
        }
        return null;
    }
}
