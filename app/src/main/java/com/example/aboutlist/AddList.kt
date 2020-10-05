package com.example.aboutlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.aboutlist.R.id.sharetitle_text
import kotlinx.android.synthetic.main.activity_addlist.*
import kotlinx.android.synthetic.main.share_dialog_custom.*

class AddList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addlist)


        val share_btn = findViewById<View>(R.id.share_btn)
        var sharetitleText = findViewById<TextView>(R.id.sharetitle_text)

        var share_title = "수연이랑"

        var title = findViewById<EditText>(R.id.title_input)

        share_btn.setOnClickListener {
            var temp = title.text.toString()

            if (sharetitleText == null) {
                Log.d("n    ull", "null")
            }
            sharetitleText.setText(temp + "을(를) 공유합니다. ")



            var dialog = android.app.AlertDialog.Builder(this)
            var v1 = layoutInflater.inflate(R.layout.share_dialog_custom, null)

            dialog.setView(v1)
                .show()
        }
    }
}