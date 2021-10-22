package com.qkl.testlifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        Log.e("qkl--->activity","onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.e("qkl--->activity","onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.e("qkl--->activity","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("qkl--->activity","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("qkl--->activity","onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("qkl--->activity","onRestart")
    }
}