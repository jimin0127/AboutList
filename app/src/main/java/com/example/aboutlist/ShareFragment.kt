package com.example.aboutlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_share.view.*
import java.awt.font.TextAttribute
import kotlin.jvm.JvmStatic as JvmStatic

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val TITLE = "param1"
private const val CHECK1 = "param1"
private const val CHECK2 = "param1"
private const val CHECK3 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [ShareFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShareFragment : DialogFragment() {

    companion object {

        const val TAG = "ShareFragment"

        private const val TITLE = "TITLE"
        private const val CHECK1 = "check1"
        private const val CHECK2 = "check2"
        private const val CHECK3 = "check3"
        @JvmStatic
        fun newInstance(title: String, list1 : String, list2 : String, list3 : String) =
            ShareFragment().apply {
                arguments = Bundle().apply {
                    putString(TITLE, title)
                    putString(CHECK1, list1)
                    putString(CHECK2, list2)
                    putString(CHECK3, list3)
                }
            }
    }

    private var title: String? = null
    private var check1: String? = null
    private var check2: String? = null
    private var check3: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(TITLE)
            check1 = it.getString(CHECK1)
            check2 = it.getString(CHECK2)
            check3 = it.getString(CHECK3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_share, container, false)
        val sharetitle = view.findViewById<TextView>(R.id.sharetitle_text)
        view.OkButton.setOnClickListener{
            val firebaseAuth : FirebaseAuth = FirebaseAuth.getInstance()
            var user = firebaseAuth.currentUser
            var uid = user?.uid

            Log.d("온 클릭 이스너", "확인버튼 클릭")
            FirebaseDB(uid, title, check1, check2, check3)


        }
        sharetitle.setText(title)
        return view
    }


}