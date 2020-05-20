package com.example.camerabasics

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper //Toast.makeText(context, "Constructor called", Toast.LENGTH_LONG).show();
(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun deleteEntry(row: Long) {
        val sqLiteDatabase = writableDatabase
        sqLiteDatabase.delete(TABLE_NAME, "$KEY_ID=$row", null)
    }

    companion object {
        const val DATABASE_NAME = "dataManager"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "data"
        const val KEY_ID = "id"
        const val KEY_IMG_URL = "ImgFavourite"
        const val CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_IMG_URL + " TEXT" + ")"
        const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    }

}