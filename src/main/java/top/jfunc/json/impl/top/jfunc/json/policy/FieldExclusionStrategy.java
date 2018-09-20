package top.jfunc.json.impl.top.jfunc.json.policy;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import top.jfunc.json.annotation.JsonField;

/**
 * 包括明确指定的和JsonField排除的
 * @author xiongshiyan at 2018/9/20 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class FieldExclusionStrategy implements ExclusionStrategy {
    private String[] ignoreFields;
    public FieldExclusionStrategy(String... ignoreFields){
        this.ignoreFields = ignoreFields;
    }
    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {

        if(shouldIgnore(f.getName())){
            return true;
        }

        JsonField annotation = f.getAnnotation(JsonField.class);

        if(null == annotation){
            return false;
        }

        return !annotation.serialize();
    }

    private boolean shouldIgnore(String fieldName){
        if(this.ignoreFields == null || this.ignoreFields.length == 0){
            return false;
        }

        for (int i = 0; i < this.ignoreFields.length; i++) {
            if(fieldName.equals(ignoreFields[i])){
                return true;
            }
        }
        return false;
    }
}
