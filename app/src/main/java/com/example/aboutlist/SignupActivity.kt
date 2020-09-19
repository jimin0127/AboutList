package com.example.aboutlist


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*


class SignupActivity : AppCompatActivity() {
    private var firebaseAuth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        back_btn.setOnClickListener{
            finish()
        }

        //회원가입 페이지에서 다음 버튼 누르기
        signup_btn.setOnClickListener {
            firebaseAuth = FirebaseAuth.getInstance()

            var signupNick_input = signupNick_input.text.toString().trim()
            //var signupId_input = signupId_input.text.toString()
            var signupPassword_input = signupPassword_input.text.toString().trim()
            createEamil(signupNick_input, signupPassword_input)
        }
    }

    //회원가입 
    private fun createEamil(email : String, password : String) {
        //firebase에 사용자 추가하는 메소드
        firebaseAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if(it.isSuccessful) { //추가에 성공하면
                    val user = firebaseAuth?.currentUser
                    Toast.makeText(this, "Authentication success", Toast.LENGTH_SHORT).show()
                } else { //추가에 실패하면
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}