package cn.zytx.common.json.impl;

import cn.zytx.common.json.Json;
import cn.zytx.common.json.JsonArray;
import cn.zytx.common.json.JsonObject;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @author xiongshiyan at 2018/6/11
 */
public abstract class BaseListJSONArray extends BaseJson<BaseListJSONArray> implements JsonArray {
    protected List<Object> list;
    public BaseListJSONArray(List<Object> list){
        this.list = list;
    }
    public BaseListJSONArray(){
        this.list = new LinkedList<>();
    }
    public BaseListJSONArray(String arrayString){
        this.list = str2List(arrayString);
    }

    public abstract List<Object> str2List(String arrayString);

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Object get(int index) {
        assertIndex(index , size());
        return checkNullValue(index , list.get(index));
    }

    @Override
    public String getString(int index) {
        assertIndex(index , size());
        String temp;
        Object value = list.get(index);
        if(isTolerant()){
            temp = ValueCompatible.compatibleValue(value, String.class);
        }else {
            temp = (String)value;
        }
        return checkNullValue(index , temp);
    }

    @Override
    public Boolean getBoolean(int index) {
        assertIndex(index , size());
        Boolean temp;
        Object value = list.get(index);
        if(isTolerant()){
            temp = ValueCompatible.compatibleValue(value, Boolean.class);
        }else {
            temp = (Boolean)value;
        }
        return checkNullValue(index , temp);
    }

    @Override
    public Integer getInteger(int index) {
        assertIndex(index , size());
        Integer temp;
        Object value = list.get(index);
        if(isTolerant()){
            temp = ValueCompatible.compatibleValue(value, Integer.class);
        }else {
            temp = (Integer)value;
        }
        return checkNullValue(index , temp);
    }

    @Override
    public Long getLong(int index) {
        assertIndex(index , size());
        Long temp;
        Object value = list.get(index);
        if(isTolerant()){
            temp = ValueCompatible.compatibleValue(value, Long.class);
        }else {
            temp = (Long)value;
        }
        return checkNullValue(index , temp);
    }

    @Override
    public Double getDouble(int index) {
        assertIndex(index , size());
        Double temp;
        Object value = list.get(index);
        if(isTolerant()){
            temp = ValueCompatible.compatibleValue(value, Double.class);
        }else {
            temp = (Double)value;
        }
        return checkNullValue(index , temp);
    }

    @Override
    public Float getFloat(int index) {
        assertIndex(index , size());
        Float temp;
        Object value = list.get(index);
        if(isTolerant()){
            temp = ValueCompatible.compatibleValue(value, Float.class);
        }else {
            temp = (Float)value;
        }
        return checkNullValue(index , temp);
    }

    @Override
    public BigInteger getBigInteger(int index) {
        assertIndex(index , size());
        BigInteger temp;
        Object value = list.get(index);
        if(isTolerant()){
            temp = ValueCompatible.compatibleValue(value, BigInteger.class);
        }else {
            temp = (BigInteger)value;
        }
        return checkNullValue(index , temp);
    }

    @Override
    public BigDecimal getBigDecimal(int index) {
        assertIndex(index , size());
        BigDecimal temp;
        Object value = list.get(index);
        if(isTolerant()){
            temp = ValueCompatible.compatibleValue(value, BigDecimal.class);
        }else {
            temp = (BigDecimal)value;
        }
        return checkNullValue(index , temp);
    }

    @Override
    public JsonArray remove(int index) {
        list.remove(index);
        return this;
    }

    @Override
    public JsonArray clear() {
        list.clear();
        return this;
    }

    @Override
    public JsonArray put(Object o) {
        list.add(o);
        return this;
    }

    @Override
    public JsonArray put(int index, Object o) {
        list.remove(index);
        list.add(index , o);
        return this;
    }

    @Override
    public JsonArray putAll(Collection<?> os) {
        list.addAll(os);
        return this;
    }

    @Override
    public JsonArray parse(String jsonString) {
        this.list = new Gson().fromJson(jsonString, List.class);
        return this;
    }

    @Override
    public List<Object> unwrap() {
        return list;
    }

    @Override
    public String toString() {
        //需要针对JsonObject/JsonArray处理
        Map<Integer , Json> map = new HashMap<>();
        int size = size();
        for (int i = 0; i < size; i++) {
            Object o = list.get(i);
            if(o instanceof JsonObject || o instanceof JsonArray){
                map.put(i , (Json) o);
            }
        }
        map.forEach((k,v)->{
            list.remove((int)k);
            list.add(k , v.unwrap());
        });

        return new Gson().toJson(this.list , List.class);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return list.equals(obj);
    }
}
