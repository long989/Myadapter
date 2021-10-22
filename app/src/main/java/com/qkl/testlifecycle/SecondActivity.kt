package com.qkl.testlifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    override fun onResume() {
        super.onResume()
        Log.e("qkl--->activity2","onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.e("qkl--->activity2","onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.e("qkl--->activity2","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("qkl--->activity2","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("qkl--->activity2","onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("qkl--->activity2","onRestart")
    }
}