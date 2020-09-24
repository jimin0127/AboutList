package com.example.aboutlist

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

class ExplainActivity : AppCompatActivity() {
    internal lateinit var  vp : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explain)

        vp = findViewById(R.id.vp) as ViewPager

        vp.adapter = ExplainViewAdapter(this)
    }
}