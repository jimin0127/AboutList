package com.example.aboutlist


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*;


class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        back_btn.setOnClickListener{
            finish()
        }

        //회원가입 페이지에서 다음 버튼 누르기
        signup_btn.setOnClickListener {
            var signupNick_input = signupNick_input.text.toString() 
            var signupId_input = signupId_input.text.toString()
            var signupPassword_input = signupPassword_input.text.toString()

            //데이터 베이스 연결(firebase)
            val database = FirebaseDatabase.getInstance()
            
            //데이터베이스 테이블 연결(테이블 명 : User)
            val UserDataBase = database.getReference("User")

            //User 증가 구현해야함
            //User 테이블 밑에 User2라는 유저 안에 닉네임과 아이디, 패스워드가 들어감
            UserDataBase.child("User2").child("nickname").setValue(signupNick_input)
            UserDataBase.child("User2").child("id").setValue(signupId_input)
            UserDataBase.child("User2").child("password").setValue(signupPassword_input)

            //다이얼로그 띄우기
            var dialog = AlertDialog.Builder(this)
            //확인 버튼 누르면 회원가입 페이지 종료
            dialog.setPositiveButton("확인") { dialog, id ->
                finish()
            }
            dialog.setTitle("회원가입")
            dialog.setMessage("회원가입이 완료되었습니다.")
            //다이얼로그 보여주기
            dialog.show()
        }
    }
}