package cn.kingcd.up;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class JSONUtils {

    public static boolean isPrintException = true;

    private JSONUtils() {
        throw new AssertionError();
    }


    public static Long getLong(JSONObject jsonObject, String key, Long defaultValue) {
        if (jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getLong(key);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }


    public static Long getLong(String jsonData, String key, Long defaultValue) {
        if (StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getLong(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }


    public static long getLong(JSONObject jsonObject, String key, long defaultValue) {
        return getLong(jsonObject, key, (Long) defaultValue);
    }


    public static long getLong(String jsonData, String key, long defaultValue) {
        return getLong(jsonData, key, (Long) defaultValue);
    }


    public static Integer getInt(JSONObject jsonObject, String key, Integer defaultValue) {
        if (jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getInt(key);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }


    public static Integer getInt(String jsonData, String key, Integer defaultValue) {
        if (StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getInt(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }


    public static int getInt(JSONObject jsonObject, String key, int defaultValue) {
        return getInt(jsonObject, key, (Integer) defaultValue);
    }


    public static int getInt(String jsonData, String key, int defaultValue) {
        return getInt(jsonData, key, (Integer) defaultValue);
    }


    public static Double getDouble(JSONObject jsonObject, String key, Double defaultValue) {
        if (jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getDouble(key);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    public static Double getDouble(String jsonData, String key, Double defaultValue) {
        if (StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getDouble(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }


    public static double getDouble(JSONObject jsonObject, String key, double defaultValue) {
        return getDouble(jsonObject, key, (Double) defaultValue);
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     * @see JSONUtils#getDouble(String, String, Double)
     */
    public static double getDouble(String jsonData, String key, double defaultValue) {
        return getDouble(jsonData, key, (Double) defaultValue);
    }

    /**
     * get String from jsonObject
     *
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return <ul>
     */
    public static String getString(JSONObject jsonObject, String key, String defaultValue) {
        if (jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getString(key);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * 获取string类型
     */
    public static String getString(String jsonData, String key, String defaultValue) {
        if (StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getString(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }


    /**
     * get String from jsonData
     *
     * @param jsonData
     * @param defaultValue
     * @param keyArray
     * @return <ul>
     */
    public static String getStringCascade(String jsonData, String defaultValue, String... keyArray) {
        if (StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        String data = jsonData;
        for (String key : keyArray) {
            data = getString(data, key, defaultValue);
            if (data == null) {
                return defaultValue;
            }
        }
        return data;
    }

    /**
     * get String array from jsonObject
     *
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return <ul>
     */
    public static String[] getStringArray(JSONObject jsonObject, String key, String[] defaultValue) {
        if (jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            JSONArray statusArray = jsonObject.getJSONArray(key);
            if (statusArray != null) {
                String[] value = new String[statusArray.length()];
                for (int i = 0; i < statusArray.length(); i++) {
                    value[i] = statusArray.getString(i);
                }
                return value;
            }
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
        return defaultValue;
    }

    /**
     * get String array from jsonData
     *
     * @param jsonData
     * @param key
     * @param defaultValue
     * @return <ul>
     */
    public static String[] getStringArray(String jsonData, String key, String[] defaultValue) {
        if (StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getStringArray(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get String list from jsonObject
     *
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return <ul>
     */
    public static List<String> getStringList(JSONObject jsonObject, String key, List<String> defaultValue) {
        if (jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            JSONArray statusArray = jsonObject.getJSONArray(key);
            if (statusArray != null) {
                List<String> list = new ArrayList<String>();
                for (int i = 0; i < statusArray.length(); i++) {
                    list.add(statusArray.getString(i));
                }
                return list;
            }
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
        return defaultValue;
    }

    /**
     * get String list from jsonData
     *
     * @param jsonData
     * @param key
     * @param defaultValue
     * @return <ul>
     */
    public static List<String> getStringList(String jsonData, String key, List<String> defaultValue) {
        if (StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getStringList(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get JSONObject from jsonObject
     *
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return <ul>
     */
    public static JSONObject getJSONObject(JSONObject jsonObject, String key, JSONObject defaultValue) {
        if (jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getJSONObject(key);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }





    /**
     * get JSONArray from jsonObject
     *
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return <ul>
     */
    public static JSONArray getJSONArray(JSONObject jsonObject, String key, JSONArray defaultValue) {
        if (jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getJSONArray(key);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }


    public static JSONArray getJSONArray(String jsonData, String key, JSONArray defaultValue) {
        if (StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getJSONArray(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }


    public static boolean getBoolean(JSONObject jsonObject, String key, Boolean defaultValue) {
        if (jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getBoolean(key);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get Boolean from jsonData
     *
     * @param jsonData
     * @param key
     * @param defaultValue
     * @return <ul>
     * <li>if jsonObject is null, return defaultValue</li>
     * <li>if jsonData {@link JSONObject#JSONObject(String)} exception, return defaultValue</li>
     * <li>return {@link JSONUtils#getBoolean(JSONObject, String, Boolean)}</li>
     * </ul>
     */
    public static boolean getBoolean(String jsonData, String key, Boolean defaultValue) {
        if (StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getBoolean(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

}
