package com.example.aboutlist

import android.animation.ArgbEvaluator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.aboutlist.LogIn_LogOut.F_SignOut
import com.example.aboutlist.LogIn_LogOut.G_SignOut
import com.example.aboutlist.LogIn_LogOut.LoginActivity
import com.example.gaboza.Adapter
import com.example.gaboza.Model
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var viewPager: ViewPager? = null
    var adapter: Adapter? = null
    var models: List<Model>? = null
    var colors: Array<Int>? = null
    var argbEvaluator = ArgbEvaluator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val setTitle = findViewById(R.id.title_input) as EditText
        val value = setTitle.text.toString()

        startActivity(Intent(this, AddList::class.java))

        models = ArrayList()
        (models as ArrayList<Model>).add(Model(value))

        adapter = Adapter(models as ArrayList<Model>, this)


        viewPager = findViewById(R.id.viewPager);
        val viewPager : ViewPager = viewPager as ViewPager
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 360, 130, 0);

        adapter = Adapter(models as ArrayList<Model>, this)

        logout_btn.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

            G_SignOut(
                FirebaseAuth.getInstance(),
                GoogleSignIn.getClient(this, gso)
            )
            startActivity(Intent(this, LoginActivity::class.java))
        }

        val facebooklogout_btn = findViewById<Button>(R.id.facebook_logout)
        facebooklogout_btn.setOnClickListener {
            finish()
            startActivity(Intent(this, LoginActivity::class.java))
            F_SignOut(FirebaseAuth.getInstance())

        }

    }

}
