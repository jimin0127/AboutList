package com.example.aboutlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.*

class FacebookLogIn : AppCompatActivity(){
    lateinit var auth: FirebaseAuth
    lateinit var callbackManager: CallbackManager
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_login)
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        callbackManager = CallbackManager.Factory.create()

        facebookLogin()
    }


    public fun facebookLogin() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
        LoginManager.getInstance().registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {

            override fun onSuccess(result: LoginResult) {
                //페이스북 로그인 성공
                handleFacebookAccessToken(result.accessToken)
                Toast.makeText(this@FacebookLogIn, "로그인 성공", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@FacebookLogIn, MainActivity::class.java))
                finish()
            }
            override fun onCancel() {
                //페이스북 로그인 취소
                updateUI(null)
            }

            override fun onError(error: FacebookException?) {
                //페이스북 로그인 실패
                updateUI(null)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        //onActivityResult에서는 callbackManager에 로그인 결과를 넘겨줍니다
        //여기에 callbackManager?.onAcitivyResult가 있어야 onSuccess를 호출할 수 있습니다.
    }

    private fun handleFacebookAccessToken(token: AccessToken?) {
        Log.d("FacebookLogIn", "handleFacebookAccessToken:$token")
        if (token != null) {
            val credential = FacebookAuthProvider.getCredential(token.token)
            Log.d("토큰 ", "${credential}")
            auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("FacebookLogIn", "signInWithCredential:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("FacebookLogIn", "signInWithCredential:failure", task.exception)
                        Toast.makeText(this@FacebookLogIn, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }
    }

    override fun onStart() { //로그인유저되있는 유저를 확인함
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    public fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            Toast.makeText(this@FacebookLogIn, user.displayName, Toast.LENGTH_SHORT).show()
            Toast.makeText(this@FacebookLogIn, user.photoUrl.toString(), Toast.LENGTH_SHORT).show()


        } else {
            //Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            Log.d("로그", "로그인 실패")
        }
    }
}

class F_SignOut {
    lateinit var auth: FirebaseAuth
    constructor(firebaseAuth : FirebaseAuth) {
        this.auth = firebaseAuth
        try {
            auth.signOut()
        } catch (e : Exception) {
            Log.d("오류", e.toString())
        }

        LoginManager.getInstance().logOut()
        FacebookLogIn().updateUI(null)
        Log.d("로그아웃 ", "로그아웃")
    }

}
