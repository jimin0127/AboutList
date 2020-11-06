package com.example.aboutlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aboutlist.databinding.RecyclerItemBinding
import com.example.aboutlist.sampledata.FireStoreDB
import com.example.aboutlist.sampledata.LayoutData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_addlist.*
import kotlinx.android.synthetic.main.recycler_item.view.*

var list = arrayListOf<LayoutData>()


class AddList : AppCompatActivity() {
    val firebaseAuth : FirebaseAuth = FirebaseAuth.getInstance()

    var user = firebaseAuth.currentUser
    var uid = user?.uid
    var firestore = FirebaseFirestore.getInstance()

    var fireStore = FireStoreDB()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addlist)

        val share_btn = findViewById<ImageButton>(R.id.share_btn)
        val title_input = findViewById<EditText>(R.id.title_input)
        val add_btn = findViewById<Button>(R.id.add_btn)
        val edit_query_btn = findViewById<Button>(R.id.edit_query_btn)


        //데이터 읽기
        firestore?.collection(uid.toString())?.get()?.addOnSuccessListener { result ->
            var layoutList = arrayListOf<LayoutData>()
            for (document in result) {
                if (document.id == "Buckets") {
                    Log.d("title", document.data.get("title").toString())
                    for (data in document.data) {
                        if (data.key != "title") {
                            var dataValueString: String = data.value.toString()
                            var dataValueList = dataValueString.split(',')

                            var first = dataValueList[0].substring(1)
                            var firstValue = first.split('=')

                            var secondLength = dataValueList[1].length
                            var second = dataValueList[1].substring(1, secondLength - 1)
                            var secondValue = second.split('=')

                            var check = firstValue[1]
                            var list = secondValue[1]
                            Log.d("check", check)
                            Log.d("list", list)

                            layoutList.add(LayoutData(check.toBoolean(), list))

                            Log.d("layoutListValue", layoutList.toString())

                        }
                    }
                }
            }
            list = layoutList

            mRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@AddList)
                adapter = ListAdapter(list) {
                    Toast.makeText(this@AddList, "$it", Toast.LENGTH_LONG).show()
                }
            }

        }

        add_btn.setOnClickListener {
            addList()
        }

        edit_query_btn.setOnClickListener {
            FireStoreWrite()
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
    
    fun addList() {

        list.add(
            LayoutData(
                false,
                ""
            )
        )

        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@AddList)
            adapter = ListAdapter(list) {
                Toast.makeText(this@AddList, "$it", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun FireStoreWrite() {
        FireStoreDB(
            uid.toString(),
            title_input.text.toString(),
            list
        )
    }
}

// recyclerView Adapter
class ListAdapter(val items : List<LayoutData>, private val clickListener : (list : LayoutData) -> Unit ) : RecyclerView.Adapter<ListAdapter.ListViewHolder>(){

    class ListViewHolder(val binding : RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)

        val viewHolder = ListViewHolder(RecyclerItemBinding.bind(view))

        var check = view.findViewById<CheckBox>(R.id.checkbox)
        var list_input = view.findViewById<EditText>(R.id.list_input)

        @Suppress("DEPRECATION")
        check.setOnCheckedChangeListener(object  : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                if(p0?.isChecked!!) {
                    list[viewHolder.position].check = false
                } else {
                    Log.d("check", "해제됨")
                    list[viewHolder.position].check = true
                }
            }

        })

        list_input.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            @Suppress("DEPRECATION")
            override fun onTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
                list[viewHolder.position].list = p0.toString()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        view.setOnClickListener {
            clickListener.invoke(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }


    override fun getItemCount() = items.size


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.layoutData = items[position]

    }
}
