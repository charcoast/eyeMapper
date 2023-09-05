package br.com.augusto;

import br.com.augusto.example.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, JsonProcessingException {
        User augusto = new User(10, "Augusto", "123456", "augusto@augusto.com");

        Map<String, Object> result =  EyeMapper.toMap(augusto);

//        System.out.println(result);
//        result.put("userId", "teste");
//        User user = new EyeMapper<User>().fromMap(result, User.class);
//
//        System.out.println(user);
//
//
//        String s = new ObjectMapper().writer().writeValueAsString(augusto);
//        System.out.println(s);
//
        String json = EyeMapper.toJson(result);
        System.out.println(json);
    }
}