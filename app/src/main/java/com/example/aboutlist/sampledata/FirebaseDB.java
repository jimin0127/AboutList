package com.example.aboutlist.sampledata;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FirebaseDB {
    DatabaseReference mDBReference = null;
    HashMap<String, Object> childUpdates = null;
    Map<String, Object> userValue = null;
    Map<String, Object> bucketsValue = null;
    User users = null;
    BucketList buckets = null;

    public FirebaseDB(String uid, String id) {

        mDBReference = FirebaseDatabase.getInstance().getReference();
        childUpdates = new HashMap<>();
        users = new User(id);
        userValue = users.toMap();

        childUpdates.put("/User_info/" + uid, userValue);
        mDBReference.updateChildren(childUpdates);
    }

    public FirebaseDB(String uid, String title, String... args) {


        mDBReference = FirebaseDatabase.getInstance().getReference();
        childUpdates = new HashMap<>();
        buckets = new BucketList(args);
        bucketsValue = buckets.toMap();


        childUpdates.put("/User_info/"+ uid +  "/BuketList/" + title, bucketsValue);
        mDBReference.updateChildren(childUpdates);
    }


}


