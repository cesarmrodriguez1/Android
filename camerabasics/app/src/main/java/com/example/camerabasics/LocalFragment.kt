package com.example.camerabasics

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.local_fragment.*
import java.util.*

class LocalFragment : Fragment() {
    var recyclerView: RecyclerView? = null
    private var myDatabase: DBHelper? = null
    private var db: SQLiteDatabase? = null
    private var singleRowArrayList: ArrayList<ImageModel>? = null
    private var singleRow: ImageModel? = null
    var image: String? = null
    var uid = 0
    var cursor: Cursor? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.local_fragment, container, false)
        //RecyclerView must be initialized. This is a Fragment, not an Activity
        recyclerView = view.findViewById(R.id.recyclerview)
        myDatabase = DBHelper(context!!)
        db= myDatabase!!.writableDatabase
        setData()
        return view
    }

    private fun setData()
    {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView!!.layoutManager = layoutManager // I missed this while coding.
        singleRowArrayList = ArrayList<ImageModel>()
        val columns = arrayOf(DBHelper.KEY_ID, DBHelper.KEY_IMG_URL )
        cursor = db!!.query(DBHelper.TABLE_NAME, columns, null, null, null, null, null)

        while(cursor!!.moveToNext())
        {
            val index1 = cursor!!.getColumnIndex(DBHelper.KEY_ID)
            val index2 = cursor!!.getColumnIndex(DBHelper.KEY_IMG_URL)
            uid = cursor!!.getInt(index1)
            image = cursor!!.getString(index2)
            singleRow = ImageModel(image!!, uid)
            singleRowArrayList!!.add(singleRow!!)
        }
        if(singleRowArrayList!!.size == 0)
        {
            recyclerView!!.visibility = View.GONE
        }
        else
        {
            val localDataBaseResponse = Adapter(context!!, singleRowArrayList!!, db!!, myDatabase!!)
            recyclerView!!.adapter = localDataBaseResponse
        }


    }
}