package com.example.aboutlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aboutlist.databinding.RecyclerItemBinding
import com.example.aboutlist.sampledata.FireStoreDB
import com.example.aboutlist.sampledata.LayoutData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_addlist.*


class AddList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addlist)

        val share_btn = findViewById<ImageButton>(R.id.share_btn)
        val title_input = findViewById<EditText>(R.id.title_input)

        val firebaseAuth : FirebaseAuth = FirebaseAuth.getInstance()

        var user = firebaseAuth.currentUser
        var uid = user?.uid
        var database = FirebaseFirestore.getInstance()
        //val database = FirebaseDatabase.getInstance()
        //var uidRef = database.getReference(uid.toString())
        Log.d("uidRef", uid.toString())
        val list = arrayListOf<LayoutData>()



        for(i in 0..30){
            list.add(
                LayoutData(
                    true,
                    "밥 먹기 $i"
                )
            )
        }

        FireStoreDB(
            uid.toString(),
            title_input.text.toString().trim(),
            list
        )

        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@AddList)
            adapter = ListAdapter(list) {
                Toast.makeText(this@AddList, "$it", Toast.LENGTH_LONG).show()
            }
        }


        val back_btn = findViewById<ImageButton>(R.id.addBack_btn)
        back_btn.setOnClickListener {
            Log.d("백버튼", "${back_btn}")
            finish()
        }





        share_btn.setOnClickListener {
            ShareFragment
                .newInstance("안녕", "어랑", "어럄", "러ㅑㅇ")
                .show(supportFragmentManager, ShareFragment.TAG)
        }
    }
}

// recyclerView Adapter
class ListAdapter(val items : List<LayoutData>, private val clickListener : (list : LayoutData) -> Unit ) : RecyclerView.Adapter<ListAdapter.ListViewHolder>(){

    class ListViewHolder(val binding : RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)


        val viewHolder = ListViewHolder(RecyclerItemBinding.bind(view))

        view.setOnClickListener {
            clickListener.invoke(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    public override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.layoutData = items[position]
    }
}
