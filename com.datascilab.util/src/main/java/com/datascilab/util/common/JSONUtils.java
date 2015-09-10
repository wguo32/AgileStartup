package com.datascilab.util.common;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONUtils {

    private Logger log = LoggerFactory.getLogger(JSONUtils.class);

    private static class JSONUtilsHolder {

        private static final JSONUtils INSTANCE = new JSONUtils();
    }

    public static JSONUtils getInstance() {
        return JSONUtilsHolder.INSTANCE;
    }

    public String json(Object obj) {
        return json(obj, new ArrayList<String>());
    }

    public String json(Object obj, List<String> ignoredFields) {
        JSONObject ja = JSONObject.fromObject(obj);
        for (String key : ignoredFields) {
            ja.remove(key);
        }

        return ja.toString();
    }

}
