package cn.zytx.common.json.impl;

import cn.zytx.common.json.Json;
import cn.zytx.common.json.JsonArray;
import cn.zytx.common.json.JsonObject;
import com.google.gson.Gson;

import java.util.HashMap;
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
