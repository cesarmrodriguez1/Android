package com.example.camerabasics

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    var frameLayout: FrameLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(CameraFragment(), false)
    }

    fun loadFragment(fragment: Fragment?, bool: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment!!)
        if (bool) transaction.addToBackStack(null)
        transaction.commit()
    }
}