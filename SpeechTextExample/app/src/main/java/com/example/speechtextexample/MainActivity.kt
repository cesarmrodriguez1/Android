package com.example.speechtextexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bTextSpeech.setOnClickListener(){
            val intent = Intent(this, TextSpeech::class.java)
            startActivity(intent)
        }
        bSpeechText.setOnClickListener(){
            val intent = Intent(this, SpeechText::class.java)
            startActivity(intent)
        }
    }
}
