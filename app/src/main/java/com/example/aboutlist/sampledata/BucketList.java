package com.example.aboutlist.sampledata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BucketList {

    String title;
    ArrayList<LayoutData> buckets;

    public BucketList(String title, ArrayList<LayoutData> buckets) {
        this.title = title;
        this.buckets = buckets;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", title);
        for(int i = 0; i < buckets.size(); i++){
            result.put("list" + i , buckets.get(i));
        }

        return result;
    }

}
