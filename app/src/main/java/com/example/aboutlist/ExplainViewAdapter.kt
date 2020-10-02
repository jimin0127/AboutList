package com.example.aboutlist

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.activity_signup.view.*

class ExplainViewAdapter(private val c : Context) : PagerAdapter() {


    private lateinit var li : LayoutInflater

    //배열에 설명화면을 차례대로 넣음
    val i = arrayOf(
        R.drawable.explain1,
        R.drawable.explain2,
        R.drawable.explain3,
        R.drawable.explain4,
        R.drawable.explain5
    )

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return i.size
    }

    
    //위치에 맞는 페이지 생성
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        //Context(맥략)에서 LayoutInflater를 가져옴
        li = c.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        
        //explain_imageview.xmlㅇ에서 파일의 정의된 내용을 가지고 LayoutInflater를 이용하여 새로운 뷰를 생성
        val v = li!!.inflate(R.layout.explain_imageview, null)
        
        //새로운 뷰의 iv라는 아이디 값을 가진 ImageView 참조
        val iv = v.findViewById<View>(R.id.iv) as ImageView



        //배열의 담긴 이미중 position 값에 해당되는 이미지를 설정한다. 
        iv.setImageResource(i[position])

        if(position == 3) {

        } else {

            var btn : Button?= v.findViewById(R.id.goMain_btn)
            btn?.setVisibility(View.INVISIBLE)
            btn?.setEnabled(false)
        }

        //ViewPager에 만들어낸 view에 더함
        container.addView(v, 0)

        // 이미지가 설정된 view의 정보를 반환함
        return v
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.invalidate()
    }




}