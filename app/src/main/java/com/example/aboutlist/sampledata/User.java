package com.example.aboutlist.sampledata;

import java.util.HashMap;
import java.util.Map;

public class User {
    public String nick_name;
    public String id;
    public String email;

    public User(String nick_name, String id, String email) {
        this.nick_name = nick_name;
        this.id = id;
        this.email = email;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nick_name", nick_name);
        result.put("id", id);
        result.put("email", email);

        return result;
    }
}