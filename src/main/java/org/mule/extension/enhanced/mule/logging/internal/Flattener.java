package org.mule.extension.enhanced.mule.logging.internal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Flattener {

    public static Map<String, Object> flatten(String keyPrefix, Object value) {
        Map<String, Object> map = new HashMap<>();
        flatten(map, keyPrefix, value);
        return map;
    }

    private static void flatten(Map<String, Object> map, String key, Object value) {
        if (value instanceof Map) {
            ((Map<?, ?>) value).entrySet().forEach(e -> flatten(map, key != null ? key + "." + e.getKey() : (String) e.getKey(), e.getValue()));
        } else if( value instanceof Collection) {
            int i = 0;
            for (Object s : (Collection)value) {
                String idx = "["+i+"]";
                flatten(map,key != null ? key + "." + idx : idx, s);
                i++;
            };
        } else {
            map.put(key, value);
        }
    }
}
