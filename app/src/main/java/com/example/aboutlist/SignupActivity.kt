package com.example.aboutlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.activity_signup.*;


class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        back_btn.setOnClickListener{
            finish()
        }

        signup_btn.setOnClickListener {
            finish()

        }
    }
}