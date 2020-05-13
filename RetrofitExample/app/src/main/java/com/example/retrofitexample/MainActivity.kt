package com.example.retrofitexample

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitexample.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // To be replaced by retrofit code
        message.text = "This can be modified by Retrofit"
    }

    fun getStarted(view: View) {
        val intent = Intent(this, ActivityBookList::class.java)
        startActivity(intent)
        finish()
    }
}
