package com.example.aboutlist.sampledata

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.reflect.typeOf

class FireStoreDB {

    var firestore : FirebaseFirestore? = null
    var firebaseAuth : FirebaseAuth? = null

    constructor(){}

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
                Log.d("firestore", "firestore Buckets 쓰기 성공")
            }
        }
    }

    fun read(uid : String, documentName : String) {
        firestore = FirebaseFirestore.getInstance()


        firestore?.collection(uid)?.get()?.addOnSuccessListener { result ->
            var layoutList = arrayListOf<LayoutData>()
            for (document in result) {
                if (document.id == "Buckets") {
                    Log.d("title", document.data.get("title").toString())
                    for (data in document.data) {
                        if (data.key != "title") {
                            var dataValueString: String = data.value.toString()
                            var dataValueList = dataValueString.split(',')

                            var first = dataValueList[0].substring(1)
                            var firstValue = first.split('=')

                            var secondLength = dataValueList[1].length
                            var second = dataValueList[1].substring(1, secondLength - 1)
                            var secondValue = second.split('=')

                            var check = firstValue[1]
                            var list = secondValue[1]
                            Log.d("check", check)
                            Log.d("list", list)

                            layoutList.add(LayoutData(check.toBoolean(), list))

                            Log.d("layoutListValue", layoutList.toString())

                        }
                    }
                }
            }
        }
    }
}