package top.jfunc.json.impl;

import com.google.gson.Gson;
import top.jfunc.json.Json;
import top.jfunc.json.JsonArray;
import top.jfunc.json.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiongshiyan at 2018/6/10
 */
public class JSONObject extends BaseMapJSONObject {

    public JSONObject(Map<String , Object> map){
        super(map);
    }
    public JSONObject(){
    }
    public JSONObject(String jsonString){
        super(jsonString);
    }

    @Override
    protected Map<String, Object> str2Map(String jsonString) {
        return new Gson().fromJson(jsonString, Map.class);
    }

    @Override
    public JsonObject parse(String jsonString) {
        this.map = new Gson().fromJson(jsonString, Map.class);
        return this;
    }

    @Override
    public String serialize(Object javaBean) {
        return new Gson().toJson(javaBean);
    }

    @Override
    public <T> T deserialize(String jsonString, Class<T> clazz) {
        return new Gson().fromJson(jsonString , clazz);
    }

    @Override
    public JsonObject getJsonObject(String key) {
        assertKey(key);
        //这里不能使用getJSONObject，因为每一种Json实现不一样，给出的JsonObject类型是不一致的。
        //这里就是各种JsonObject不能混用的原因
        Object temp = this.map.get(key);
        Object t = checkNullValue(key, temp);

        if(t instanceof Map){
            return new JSONObject((Map<String, Object>) t);
        }

        return (JsonObject) t;
    }

    @Override
    public JsonArray getJsonArray(String key) {
        assertKey(key);
        //这里不能使用getJSONObject，因为每一种Json实现不一样，给出的JsonObject类型是不一致的。
        //这里就是各种JsonObject不能混用的原因
        Object temp = this.map.get(key);
        Object t = checkNullValue(key, temp);

        if(t instanceof List){
            return new JSONArray((List<Object>)t);
        }
        return (JsonArray) t;
    }
    @Override
    public String toString() {
        //需要针对JsonObject/JsonArray处理
        Map<String , Json> map = new HashMap<>();
        for (String key : map.keySet()) {
            Object o = map.get(key);
            if(o instanceof JsonObject || o instanceof JsonArray){
                map.put(key , (Json) o);
            }
        }
        map.forEach((k , v)-> this.map.put(k , v.unwrap()));

        return new Gson().toJson(this.map , Map.class);
    }

    @Override
    public JsonObject fromMap(Map<String, Object> map) {
        return new JSONObject(map);
    }
}
