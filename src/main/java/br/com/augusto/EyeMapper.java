package br.com.augusto;

import br.com.augusto.annotation.JsonIgnore;
import br.com.augusto.annotation.JsonIgnoreErrors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EyeMapper<T> {

    public EyeMapper() {
    }

    public static Map<String, Object> toMap(Object object) throws IllegalAccessException {

        HashMap<String, Object> map = new HashMap<>();

        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.getAnnotation(JsonIgnore.class) != null) {
                continue;
            }

            field.setAccessible(true);
            Object value = field.get(object);
            map.put(field.getName(), value);
        }
        return map;
    }

    public T fromMap(Map<String, Object> map, Class<T> clazz) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        T object = clazz.getDeclaredConstructor().newInstance();

        Field[] fields = object.getClass().getDeclaredFields();


        boolean classIgnoreErros = clazz.getAnnotation(JsonIgnoreErrors.class) != null;

        for (Field field : fields) {
            List<Annotation> fieldAnnotations = List.of(field.getAnnotations());

            boolean jsonIgnore = fieldAnnotations.stream().anyMatch(a -> a.annotationType().equals(JsonIgnore.class));

            if (jsonIgnore) {
                continue;
            }

            boolean jsonIgnoreErrors = fieldAnnotations.stream().anyMatch(a -> a.annotationType().equals(JsonIgnoreErrors.class));
            field.setAccessible(true);
            Object value = map.get(field.getName());
            try {
                field.set(object, value);
            } catch (Exception e) {
                if (!jsonIgnoreErrors && !classIgnoreErros) {
                    throw e;
                }else{
                    System.out.println("Ignoring parse error on field: " + field.getName());
                }
            }

        }
        return object;
    }

    public static String toJson(Map<String, Object> map){
        StringBuilder builder = new StringBuilder();
        builder.append("{");

        map.forEach((key, value) -> {

            Class type = value.getClass();
            System.out.println("Field: " + key + " type: " + type);
            builder.append("\"").append(key).append("\": ");

            if (type.equals(String.class)) {
                builder.append("\"").append(value).append("\"");
            } else if (type.equals(Boolean.class)) {
                builder.append(value);
            } else {
                builder.append(value);
            }

            builder.append(", ");
        });

        String result = builder.substring(0, builder.length() - 2);
//        builder.append("}");
        return result + "}";
    }
}
