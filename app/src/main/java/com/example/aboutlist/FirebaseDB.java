package com.example.aboutlist;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FirebaseDB {
    DatabaseReference mDBReference = null;
    HashMap<String, Object> childUpdates = null;
    Map<String, Object> userValue = null;
    User users = null;

    public FirebaseDB(String uid, String id) {

        mDBReference = FirebaseDatabase.getInstance().getReference();
        childUpdates = new HashMap<>();
        users = new User(id);
        userValue = users.toMap();

        childUpdates.put("/User_info/" + uid, userValue);
        mDBReference.updateChildren(childUpdates);
    }


}


