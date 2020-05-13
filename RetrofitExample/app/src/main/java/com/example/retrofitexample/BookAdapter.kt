package com.example.retrofitexample

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(private val bookList: List<Book>): RecyclerView.Adapter<BookAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookAdapter.ViewHolder, position: Int) {
        holder.book = bookList[position]
        holder.text_book.text = bookList[position].name

        holder.itemView.setOnClickListener { v->
            val context = v.context
            val intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra(BookDetailActivity.ARG_ITEM_ID, holder.book!!.isbn)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val text_book: TextView = itemView.findViewById(R.id.text_book)
        var book: Book? = null

        override fun toString(): String
        {
            return """${super.toString()} '${text_book.text}'"""
        }
    }
}