package com.example.aboutlist.LogIn_LogOut


import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.aboutlist.R
import com.example.aboutlist.sampledata.FirebaseDB
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
            //firebase Authentication 연결
            firebaseAuth = FirebaseAuth.getInstance()

            var signupNick_input = signup_email_input.text.toString().trim()
            var signupPassword_input = signupPassword_input.text.toString().trim()
            var checkPassword_input = checkPassword_input.text.toString().trim()
            createAccount(signupNick_input, signupPassword_input, checkPassword_input)
        }
    }

    //회원가입 계정 생성
    private fun createEmail(email : String, password : String) {
        //firebase에 사용자 추가하는 메소드
        firebaseAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if(it.isSuccessful) { //추가에 성공하면
                    
                    //dialog 생성하기
                    var dialog = AlertDialog.Builder(this)
                    var v1 = layoutInflater.inflate(R.layout.signup_dialog_custom, null)
                    var okButton = v1.findViewById<View>(R.id.OkButton)
                    okButton.setOnClickListener {
                        finish()
                    }
                    dialog.setView(v1)
                        .show()

                    val user = firebaseAuth?.currentUser //현재 사용자
                    val uid = user?.uid
                    FirebaseDB(
                        uid,
                        signupId_input.text.toString().trim()
                    )

                } else { //추가에 실패하면
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }


    //회원가입 계정 생성 가능 확인
    private fun createAccount (email : String, password: String, checkPassword_input : String){

        //비밀번호와 비밀번호 확인이 다르면
        if (password != checkPassword_input){
            //비밀번호 확인 칸 밑에 비밀번호가 틀렸습니다. 빨간색으로 출력
            passwordErorr_text.setText("비밀번호가 틀렸습니다.")
            passwordErorr_text.setTextColor(getResources().getColor(R.color.colorRed))
            val checkpassword = findViewById<EditText>(R.id.checkPassword_input)
            checkpassword.setText(null)
        } else{
            //같으면 빈칸으로 바꾸기
            passwordErorr_text.setText(null)
        }


        var count : Int = 0 //숫자 / 알파벳 / 특수문자 세는 변수

        //비밀번호 길이가 6~15 사이, 비밀번호와 비밀번호 확인이 같으면
        if(password.length >= 6 && password.length <= 15 && password == checkPassword_input) {
            message_text.setText("사용할 수 있는 비밀번호입니다. ") //비밀번호 칸 밑에 출력하는 문자열
            message_text.setTextColor(getResources().getColor(R.color.colorBlue))

            var digit_count = 0 //숫자 세는 카운트
            var alpha_count = 0 //알파벳 세는 카운트
            var special_count = 0 //특수문자 세는 카운트

            for (c in password.toLowerCase()) { //비밀번호를 모두 소문자로 바꾸고 하나씩 char로 꺼내옴
                if (c in '0'..'9') digit_count = 1
                else if (c in 'a'..'z') alpha_count = 1
                else if (c in "~!@#$%^&*()_-`?/:;'") special_count = 1
                else Toast.makeText(this, "사용할 수 없는 문자가 포함되어있습니다. ", Toast.LENGTH_SHORT).show()
            }

            // 숫자 / 알파벳 / 특수문자를 모두 더해서 2가지 이상 사용했으면. (다 사용하면 3)
            if (digit_count + alpha_count + special_count >= 2) {
                createEmail(email, password)
            } else {
                message_text.setText("비밀번호는 영어/ 숫자 / 특수문자 중 2가지 이상을 사용하세요")
                message_text.setTextColor(getResources().getColor(R.color.colorRed))
            }
            
        } else if (password == checkPassword_input){ // 비밀번호 길이가 맞지 않으면
            message_text.setText("비밀번호 길이는 6~15자 입니다.")
            message_text.setTextColor(getResources().getColor(R.color.colorRed))
        }
    }
}