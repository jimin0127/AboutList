package com.example.aboutlist

import android.content.Intent
import android.os.Bundle
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

    }

    public fun goMain_btnClickListener() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}