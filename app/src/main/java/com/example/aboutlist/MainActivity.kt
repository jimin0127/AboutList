package com.example.aboutlist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var add : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add = findViewById(R.id.add_list)

        logout_btn.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            G_SignOut(FirebaseAuth.getInstance(), GoogleSignIn.getClient(this, gso))
            startActivity(Intent(this, LoginActivity::class.java))
        }

        val facebooklogout_btn = findViewById<Button>(R.id.facebook_logout)
        facebooklogout_btn.setOnClickListener {
            finish()
            startActivity(Intent(this, LoginActivity::class.java))
            F_SignOut(FirebaseAuth.getInstance())

        }

        add.setOnClickListener{
            val intent = Intent(this, AddList::class.java)
            startActivity(intent)
        }

    }
}
