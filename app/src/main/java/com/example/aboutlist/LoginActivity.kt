package com.example.aboutlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.database.FirebaseDatabase
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var goSingup_btn : Button = findViewById(R.id.goSignup_btn)
        goSingup_btn.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("message")

            myRef.setValue("Hello, 수연 ")
        }
    }
}