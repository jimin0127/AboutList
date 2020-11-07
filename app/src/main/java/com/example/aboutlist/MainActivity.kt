package com.example.aboutlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_addlist.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var viewPager: ViewPager? = null
    var adapter: Adapter? = null
    private var models: ArrayList<Model>? = null
    lateinit var add : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        add = findViewById(R.id.add_list)

        add.setOnClickListener{
            startActivity(Intent(this, AddList::class.java))

        }

        //models = ArrayList()
        //val titleEdit = findViewById<View>(R.id.title_input) as EditText
        //val value = titleEdit.text.toString()
        //(models as ArrayList<Model>).add(Model(value))
        //adapter = Adapter(models as ArrayList<Model>, this)
        //viewPager = findViewById(R.id.viewPager)
        //val viewPager : ViewPager = viewPager as ViewPager
        //viewPager.setAdapter(adapter)
        //viewPager.setPadding(130, 360, 130, 0)
    }
}