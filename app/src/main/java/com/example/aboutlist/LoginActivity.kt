package com.example.aboutlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var firebaseAuth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        var goSingup_btn : Button = findViewById(R.id.goSignup_btn)
        goSingup_btn.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))

        }

        var next_btn : Button = findViewById(R.id.next_btn)
        next_btn.setOnClickListener {
            firebaseAuth = FirebaseAuth.getInstance()
            var email = email_input.text.toString().trim()
            var password = password_input.text.toString().trim()
            login(email, password)
        }

        var GooleLogin_btn : SignInButton = findViewById(R.id.loginAsGoogle_btn)
        GooleLogin_btn.setOnClickListener {
            startActivity(Intent(this, GoogleLogIn::class.java))
        }


        var FacebookLogin_btn : Button = findViewById(R.id.loginAsFacebook_btn)
        FacebookLogin_btn.setOnClickListener {
            startActivity(Intent(this, FacebookLogIn::class.java))
        }
    }

    //로그인
    private fun login(email : String, password : String) {
        //사용자 로그인
        firebaseAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { take->
                if(take.isSuccessful) { //로그인에 성공하면
                    Toast.makeText(this, "signInWithEmail success.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, ExplainActivity::class.java))

                } else { //로그인에 실패하면
                    Toast.makeText(this, "signInWithEmail failed. ", Toast.LENGTH_SHORT).show()
                }
            }
    }
}