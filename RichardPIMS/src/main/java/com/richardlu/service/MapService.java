package com.richardlu.service;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Richard on 14-1-26.
 */
public class MapService {

    public static Object getSingleValueOfMap(Map map,String Key) {
        Set set = map.entrySet();
        Object value = null;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry item = (Map.Entry) it.next();
            if ((String) item.getKey() == Key) {
               value = item.getValue();
                break;
            }
        }
        return value;
    }


}
