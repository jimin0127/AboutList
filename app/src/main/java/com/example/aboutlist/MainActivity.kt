package com.example.aboutlist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var add : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        startActivity(Intent(this, LoginActivity::class.java))

        add = findViewById(R.id.add_list)

        add.setOnClickListener{
            val intent = Intent(this, AddList::class.java)
        }

    }
}
