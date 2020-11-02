package com.example.aboutlist.sampledata

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FireStoreDB {

    var firestore : FirebaseFirestore? = null
    var firebaseAuth : FirebaseAuth? = null

    constructor(uid : String, nick_name : String, email : String, id : String?) {
        var userDTO = User(nick_name, id, email)
        var mapUser = userDTO.toMap()

        firestore = FirebaseFirestore.getInstance()

        firestore?.collection(uid)?.document("user")?.set(mapUser)?.addOnCompleteListener{
            task ->
            if(task.isSuccessful) {
                Log.d("firestore", "firestore 쓰기 성공")
            }
        }
    }

    constructor(uid : String, title : String, list: ArrayList<LayoutData>){
        var bucketList = BucketList(title, list)
        var mapBuckets = bucketList.toMap()

        firestore = FirebaseFirestore.getInstance()

        firestore?.collection(uid)?.document("Buckets")?.set(mapBuckets)?.addOnCompleteListener{
                task ->
            if(task.isSuccessful) {
                Log.d("firestore", "firestore 쓰기 성공")
            }
        }

    }
}