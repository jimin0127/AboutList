package com.example.aboutlist;

import java.util.HashMap;
import java.util.Map;

public class BucketList {

    String[] buckets = {"", "", ""};

    public BucketList(String... buckets) {
        for(int i = 0; i < buckets.length; i++) {
            this.buckets[i] =
                    buckets[i];
        }
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        for(int i = 0; i < buckets.length; i++){
            result.put("list" + i , buckets[i]);
        }

        return result;
    }
}
