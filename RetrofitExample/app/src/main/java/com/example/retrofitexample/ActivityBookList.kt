package com.example.retrofitexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView

//import com.example.retrofitexample.helpers.SampleData
import kotlinx.android.synthetic.main.activity_book_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityBookList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        //setSupportActionBar(toolbar as Toolbar?)

        fab.setOnClickListener {
            val intent = Intent(this@ActivityBookList, ActivityBookCreate::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        loadBooks()
    }

    private fun loadBooks() {

        (recyclerView as RecyclerView).adapter = BookAdapter(SampleData.books)

        val bookService = ServiceBuilder.buildService(BookService::class.java)

        val requestCall = bookService.getBooksList()

       // val requestCall = bookService.getBook(1)

      //   val requestCall = bookService.getBooksList("Practical")

        requestCall.enqueue(object: Callback <List<Book>>
        {
            override fun onResponse(call: Call<List <Book>>, response: Response<List<Book>>)
            {
                if(response.isSuccessful)
                {
                    val BookList = response.body()!!
                    (recyclerView as RecyclerView).adapter = BookAdapter(BookList)
                }
                else if(response.code()==401)
                    Log.d(null, "Your session has expired");
                else if(response.code()==404)
                    Log.d(null, "Unable to access server");
            }
            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                 Log.d(null, "something went wrong", t)
            }
        })
    }
}