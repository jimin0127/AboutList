package com.example.aboutlist

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Firebase {
    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val myRef : DatabaseReference = database.getReference()

    constructor(id : String, nickname : String){
        myRef.child("id").setValue(id)
        myRef.child("닉네임").setValue(nickname)
    }
}
