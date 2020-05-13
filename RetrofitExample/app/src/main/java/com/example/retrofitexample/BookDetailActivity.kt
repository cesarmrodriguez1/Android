package com.example.retrofitexample

import android.content.Intent
import android.os.Bundle
import android.util.Log

import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.activity_book_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

     //   setSupportActionBar(detail_toolbar as Toolbar?)
        // Show the Up button in the action bar.
    //    supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bundle: Bundle? = intent.extras

        if (bundle?.containsKey(ARG_ITEM_ID)!!) {

            val isbn = intent.getIntExtra(ARG_ITEM_ID, 0)

            loadBookDetails(isbn)

            initUpdateButton(isbn)

            initDeleteButton(isbn)
        }
    }

    private fun loadBookDetails(isbn: Int) {

    //    val book = SampleData.getBookByIsbn(isbn)

        val bookService = ServiceBuilder.buildService(BookService::class.java)

       // val requestCall = bookService.getBooksList()

         val requestCall = bookService.getBook(isbn)

        //   val requestCall = bookService.getBooksList("Practical")

        requestCall.enqueue(object: Callback<Book>
        {
            override fun onResponse(call: Call<Book>, response: Response<Book>)
            {
                if(response.isSuccessful)
                {
                    val book = response.body()
                    edit_name.setText(book?.name)
                    edit_description.setText(book?.description)
                    edit_editorial.setText(book?.editorial)

                    collapsing_toolbar_detail.title = book?.name
                }
                else if(response.code()==401)
                    Log.d(null, "Your session has expired");
                else if(response.code()==404)
                    Log.d(null, "Unable to access server");
            }
            override fun onFailure(call: Call<Book>, t: Throwable) {
                Log.d(null, "something went wrong", t)
            }
        })

/*
        book?.let {
            edit_name.setText(book.name)
            edit_description.setText(book.description)
            edit_editorial.setText(book.editorial)

            collapsing_toolbar_detail.title = book.name
        }
*/
    }

    private fun initUpdateButton(isbn: Int) {

        btn_update.setOnClickListener {

            val name = edit_name.text.toString()
            val description = edit_description.text.toString()
            val editorial = edit_editorial.text.toString()

            // To be replaced by retrofit code
            val book = Book()
            book.isbn = isbn
            book.name = name
            book.description = description
            book.editorial = editorial

            val bookService = ServiceBuilder.buildService(BookService::class.java)
            val requestCall = bookService.updateBook(isbn, name, description, editorial)

            requestCall.enqueue(object: Callback<Book>
            {
                override fun onResponse(call: Call<Book>, response: Response<Book>) {
                   if(response.isSuccessful)
                   {
                       finish()
                       var updatedBook = response.body()
                       Toast.makeText(this@BookDetailActivity, "Book was updated", Toast.LENGTH_SHORT).show()
                   }
                    else
                       Toast.makeText(this@BookDetailActivity, "Book was not updated", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Book>, t: Throwable) {
                    Toast.makeText(this@BookDetailActivity, "Problem, book was not updated", Toast.LENGTH_SHORT).show()
                }
            }
            )
        }
    }

    private fun initDeleteButton(isbn: Int) {

        val name = edit_name.text.toString()
        val description = edit_description.text.toString()
        val editorial = edit_editorial.text.toString()

        val book = Book()
        book.isbn = isbn
        book.name = name
        book.description = description
        book.editorial = editorial

        btn_delete.setOnClickListener {

            val bookService = ServiceBuilder.buildService(BookService::class.java)
            val requestCall = bookService.deleteBook(isbn)

            requestCall.enqueue(object: Callback<Unit>
            {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if(response.isSuccessful)
                    {
                        finish()
                        Toast.makeText(this@BookDetailActivity, "Book was removed", Toast.LENGTH_SHORT).show()
                    }
                    else
                        Toast.makeText(this@BookDetailActivity, "Problem. Book was not removed", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Toast.makeText(this@BookDetailActivity, "Problem. Book was removed", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            navigateUpTo(Intent(this, ActivityBookList::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        const val ARG_ITEM_ID = "item_id"
    }
}
