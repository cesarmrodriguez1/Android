package com.example.speechtextexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_text_speech.*
import java.util.*

class TextSpeech : AppCompatActivity() , AdapterView.OnItemSelectedListener {

    var t1: TextToSpeech? = null
    var languages =
        arrayOf<String?>("Italian", "English", "French")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_speech)

        spinner.onItemSelectedListener = this
        //Creating the ArrayAdapter instance having the country list
        val aa: ArrayAdapter<*> =
            ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, languages)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Setting the ArrayAdapter data on the Spinner
        spinner.adapter = aa

        t1 = TextToSpeech(applicationContext, OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                t1!!.language = Locale.UK
            }
        })
        bTts!!.setOnClickListener {
            val toSpeak = edit_tts!!.text.toString()
            Toast.makeText(applicationContext, toSpeak, Toast.LENGTH_SHORT).show()
            t1!!.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null,"SpeakRequest")
        }
    }

    public override fun onPause() {
        if (t1 != null) {
            t1!!.stop()
            t1!!.shutdown()
        }
        super.onPause()
    }

    //Performing action onItemSelected and onNothing selected
    override fun onItemSelected(
        arg0: AdapterView<*>?,
        arg1: View,
        position: Int,
        id: Long
    ) {
        when(languages[position])
        {
            "English" -> {t1 = TextToSpeech(applicationContext, OnInitListener { status ->
                if (status != TextToSpeech.ERROR) {
                    t1!!.language = Locale.UK
                }
            })}
        }
        Toast.makeText(applicationContext, languages[position], Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(arg0: AdapterView<*>?) { // TODO Auto-generated method stub
    }
}
