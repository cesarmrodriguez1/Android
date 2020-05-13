package com.example.retrofitexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_book_create.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityBookCreate : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_create)

        //setSupportActionBar(toolbar as Toolbar?)
        val context = this

        // Show the Up button in the action bar.
       // supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_add.setOnClickListener {
            val book = Book()
            book.name = text_name.text.toString()
            book.description = text_desc.text.toString()
            book.editorial = text_editorial.text.toString()

            var bookService = ServiceBuilder.buildService(BookService::class.java)
            val requestCall = bookService.saveBook(book)

            requestCall.enqueue(object: Callback<Book>
            {
                override fun onResponse(call: Call<Book>, response: Response<Book>) {
                   if(response.isSuccessful)
                   {
                       finish()
                       var newBook = response.body()
                       Toast.makeText(context, "Book was added", Toast.LENGTH_SHORT).show()
                   }
                    else
                       Toast.makeText(context, "Book was not added", Toast.LENGTH_SHORT).show()

                }
                override fun onFailure(call: Call<Book>, t: Throwable) {
                    Toast.makeText(context, "Problem adding book", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
