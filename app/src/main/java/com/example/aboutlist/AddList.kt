package com.example.aboutlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class AddList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addlist)
        val share_btn = findViewById<View>(R.id.share_btn)

        share_btn.setOnClickListener {
            var title = "수연이랑"
            val sharetitle_text = findViewById<TextView>(R.id.sharetitle_text)


            var dialog = android.app.AlertDialog.Builder(this)
            var v1 = layoutInflater.inflate(R.layout.share_dialog_custom, null)
            sharetitle_text.setText(title + "을(를) 공유합니다. ")
            dialog.setView(v1)
                .show()
        }
    }
}