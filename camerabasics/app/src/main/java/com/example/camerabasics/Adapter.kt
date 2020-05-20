package com.example.camerabasics

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.camerabasics.Adapter.MyViewHolder
import java.util.*

class Adapter(var context: Context, singleRowArrayList: ArrayList<ImageModel>, db: SQLiteDatabase, myDatabase: DBHelper) : RecyclerView.Adapter<MyViewHolder>() {

    var singleRowArrayList: ArrayList<ImageModel>
    var db: SQLiteDatabase
    var myDatabase: DBHelper
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.database_items, null)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        myViewHolder.newsImage.setImageBitmap(getBitmapFromEncodedString(singleRowArrayList[i].image))
        myViewHolder.delete.setOnClickListener { deletedata(i, singleRowArrayList) }
    }

    override fun getItemCount(): Int {
        return singleRowArrayList.size
    }

    class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        var newsImage: ImageView
        var delete: ImageView

        init {
            newsImage = itemView.findViewById<View>(R.id.newsImage) as ImageView
            delete = itemView.findViewById<View>(R.id.delete) as ImageView
        }
    }

    fun deletedata(position: Int, singleRowArrayList: ArrayList<ImageModel>) {
        AlertDialog.Builder(context)
                .setIcon(R.drawable.ic_launcher_background)
                .setTitle("Delete result")
                .setMessage("Are you sure you want delete this result?")
                .setPositiveButton("Yes") { dialog, which ->
                    /* This is where deletions should be handled */
                    myDatabase.deleteEntry(singleRowArrayList[position].uid.toLong())
                    singleRowArrayList.removeAt(position)
                    notifyItemRemoved(position)
                    notifyDataSetChanged()
                    myDatabase.close()
                    //((MainActivity) context).loadFragment(new LocalFragment(), true);
                }
                .setNegativeButton("No", null)
                .show()
    }

    private fun getBitmapFromEncodedString(encodedString: String): Bitmap {
        val arr = Base64.decode(encodedString, Base64.URL_SAFE)
        return BitmapFactory.decodeByteArray(arr, 0, arr.size)
    }

    init {
        this.singleRowArrayList = singleRowArrayList
        this.db = db
        this.myDatabase = myDatabase
    }
}