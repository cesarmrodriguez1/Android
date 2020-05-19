package com.example.speechtextexample

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class dummy : AppCompatActivity(), OnItemSelectedListener {
    var country =
        arrayOf<String?>("India", "USA", "China", "Japan", "Other")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        val spinner = findViewById<View>(R.id.spinner) as Spinner
        spinner.onItemSelectedListener = this
        //Creating the ArrayAdapter instance having the country list
        val aa: ArrayAdapter<*> =
            ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, country)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Setting the ArrayAdapter data on the Spinner
        spinner.adapter = aa
    }

    //Performing action onItemSelected and onNothing selected
    override fun onItemSelected(
        arg0: AdapterView<*>?,
        arg1: View,
        position: Int,
        id: Long
    ) {
        Toast.makeText(applicationContext, country[position], Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(arg0: AdapterView<*>?) { // TODO Auto-generated method stub
    }
}