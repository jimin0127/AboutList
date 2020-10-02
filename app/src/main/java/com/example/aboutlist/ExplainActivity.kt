package com.example.aboutlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.explain_imageview.*

class ExplainActivity : AppCompatActivity() {
    internal lateinit var  vp : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explain)


        vp = findViewById(R.id.vp) as ViewPager


        vp.adapter = ExplainViewAdapter(this)

        val goMain_btn = findViewById<Button>(R.id.goMain_btn)

        goMain_btn.setOnClickListener {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
            Log.d("goMain_btn 클릭", "클릭")

        }
    }

}