package com.qkl.testlifecycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * @title:
 * @projectName NewDriverSchool
 * @description:
 * @author qiukailong
 * @date 2021/10/11
 */
class MainFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main,container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textview = view.findViewById<TextView>(R.id.textview)
        textview.setOnClickListener {
            startActivity(Intent(activity,SecondActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        Log.e("qkl--->fragment","onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.e("qkl--->fragment","onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.e("qkl--->fragment","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("qkl--->fragment","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("qkl--->fragment","onDestroy")
    }
}