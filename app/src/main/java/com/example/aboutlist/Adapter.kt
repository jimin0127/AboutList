package com.example.gaboza

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.aboutlist.AddList
import com.example.aboutlist.R

class Adapter(models: List<Model>, context: Context) :
    PagerAdapter() {
    private val models: List<Model>
    private var layoutInflater: LayoutInflater? = null
    private val context: Context
    override fun getCount(): Int {
        return models.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        val layoutInflater : LayoutInflater = layoutInflater as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.item_bookmark, container, false)
        val title: TextView
        title = view.findViewById(R.id.title)
        title.setText(models[position].getTitle())

        view.setOnClickListener {
            val intent = Intent(context, AddList::class.java)
            //intent.putExtra("param", models.get(position).getTitle());
            //intent.putExtra("param", models.get(position).getTitle());
            context.startActivity(intent)
        }
        container.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    init {
        this.models = models
        this.context = context
    }
}