package com.example.aboutlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.example.aboutlist.LogIn_LogOut.LoginActivity

class SplashActiviy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SystemClock.sleep(1200)
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}