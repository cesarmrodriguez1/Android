package com.example.camerabasics

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.camera_fragment.*
import java.io.ByteArrayOutputStream

class CameraFragment : Fragment() {
var text: TextView? = null
    var text1: TextView? = null
    //Bitmap photo;
    var photo: String? = null
    var databaseHandler: DBHelper? = null
    private var db: SQLiteDatabase? = null
    var theImage: Bitmap? = null


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.camera_fragment, container, false)

        //In fragment, components must be initialized. I skipped this in the video
        text = view.findViewById(R.id.text)
        text1 = view.findViewById(R.id.text1)
        databaseHandler = DBHelper(getActivity()!!.applicationContext)

        text!!.setOnClickListener{
            if(activity!!.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), MY_CAMERA_PERMISSION_CODE)
            }
            else
            {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            }
        }

        text1!!.setOnClickListener{
            (activity as MainActivity?)!!.loadFragment(LocalFragment(), true)
        }

        return view
    }

    private fun setDataToDataBase() {
        db = databaseHandler!!.writableDatabase
        val cv = ContentValues()
        cv.put(DBHelper.KEY_IMG_URL, photo)
        val id = db!!.insert(DBHelper.TABLE_NAME, null, cv)
        if(id<0)
        {
            Toast.makeText(context, "Something went wrong with database", Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(context, "Photo was successfully added to database", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Reuqesting for premissons
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(context, "User permission granted", Toast.LENGTH_SHORT).show()
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            }
            else
            {
                Toast.makeText(context, "User permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Start an activity for result
     * @param requestCode
     * @param resultCode
     * @param data
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
       if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
       {
           theImage = data!!.extras!!["data"] as Bitmap
           //photo in string:
           photo = getEncodedString(theImage)
           setDataToDataBase()
       }
    }

    private fun getEncodedString(bitmap: Bitmap?): String {
        val outputstream = ByteArrayOutputStream()
        bitmap!!.compress(Bitmap.CompressFormat.JPEG,  100, outputstream)

        val imageArray = outputstream.toByteArray()
        return Base64.encodeToString(imageArray, Base64.URL_SAFE)
    }

    companion object {
        private const val CAMERA_REQUEST = 1888
        private const val MY_CAMERA_PERMISSION_CODE = 100
    }
}