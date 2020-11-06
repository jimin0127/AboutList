package com.example.aboutlist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter

class Adapter(private val models: List<Model>, private val context: Context) :
    PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null
    override fun getCount(): Int {
        return models.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        val layoutInflater : LayoutInflater = layoutInflater as LayoutInflater
        val view = layoutInflater.inflate(R.layout.item_bookmark, container, false)
        val title: TextView
        var desc: TextView
        title = view.findViewById(R.id.title)
        title.text = models[position].title
        view.setOnClickListener {
            val intent = Intent(context, AddList::class.java)
            //intent.putExtra("param", models.get(position).getTitle());
            context.startActivity(intent)
        }
        container.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}