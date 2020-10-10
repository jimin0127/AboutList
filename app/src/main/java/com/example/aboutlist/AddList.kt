package com.example.aboutlist

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton

import androidx.appcompat.app.AppCompatActivity
import com.facebook.share.Share
import androidx.fragment.app.FragmentManager as FragmentManager


class AddList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addlist)

        val back_btn = findViewById<ImageButton>(R.id.addBack_btn)
        back_btn.setOnClickListener {
            Log.d("백버튼", "${back_btn}")
            finish()
        }

        val share_btn = findViewById<ImageButton>(R.id.share_btn)
        val title_input = findViewById<EditText>(R.id.title_input)
        val list1 = findViewById<EditText>(R.id.check1)
        val list2 = findViewById<EditText>(R.id.check2)
        val list3 = findViewById<EditText>(R.id.check3)

        share_btn.setOnClickListener {
            ShareFragment
                .newInstance(title_input.text.toString(), list1.text.toString(), list2.text.toString(), list3.text.toString())
                .show(supportFragmentManager, ShareFragment.TAG)
        }
    }
}