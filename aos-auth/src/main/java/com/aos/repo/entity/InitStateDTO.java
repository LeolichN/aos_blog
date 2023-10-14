package com.aos.repo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class InitStateDTO {
    private Integer userId;
    private String username;
    private Map<String,Object> otherStates;

    public InitStateDTO setUser(Integer userId, String username){
        this.userId = userId;
        this.username = username;
        return this;
    }

    public InitStateDTO putOtherStates(String code, Object initState){
        if (otherStates == null) {
            otherStates = new HashMap<>();
        }
        otherStates.put(code,initState);
        return this;
    }
}
