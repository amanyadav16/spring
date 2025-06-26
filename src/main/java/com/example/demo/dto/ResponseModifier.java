package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.HashMap;
import java.util.Map;

public class ResponseModifier {
    private final Map<String,Object> modifiedResponse = new HashMap<>();

    public ResponseModifier(Object response, String key){
        modifiedResponse.put(key,response);
    }

    public void addProperty(String key, Object value){
        modifiedResponse.put(key, value);
    }

    @JsonAnyGetter
    public Map<String,Object> getModifiedResponse(){
        return  modifiedResponse;
    }
}
