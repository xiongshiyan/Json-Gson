package top.jfunc.json.policy;

import com.google.gson.FieldNamingStrategy;
import top.jfunc.json.annotation.JsonField;

import java.lang.reflect.Field;

/**
 * @author xiongshiyan at 2018/9/20 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class FieldNameChangeNamingStrategy implements FieldNamingStrategy {
    @Override
    public String translateName(Field f) {
        boolean present = f.isAnnotationPresent(JsonField.class);
        if(!present){
            return f.getName();
        }
        JsonField annotation = f.getAnnotation(JsonField.class);
        return "".equals(annotation.value()) ? f.getName() : annotation.value();
    }
}
