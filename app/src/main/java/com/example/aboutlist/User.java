package com.example.aboutlist;

import java.util.HashMap;
import java.util.Map;

public class User {
    public String id;


    public User(String id) {
        this.id = id;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);

        return result;
    }
}