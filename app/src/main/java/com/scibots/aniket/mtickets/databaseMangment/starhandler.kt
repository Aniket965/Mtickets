package com.scibots.aniket.mtickets.databaseMangment

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by anike on 06-06-2017.
 */

class starhandler(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val Query = "CREATE TABLE " + TABLE_NAME + "(" +

                COLOUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLOUMN_Movie_TITLE_liked + " TEXT " +
                ");"
        db.execSQL(Query)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXITS " + TABLE_NAME)
        onCreate(db)
    }

    fun addLikedMovie(Name: String) {
        val values = ContentValues()
        values.put(COLOUMN_Movie_TITLE_liked, Name)
        val db = writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun removeLike(movieName: String) {
        val db = writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME WHERE $COLOUMN_Movie_TITLE_liked=\"$movieName\";")
    }

    fun isMovieLiked(Name: String): Boolean {
        val db = this.readableDatabase
        val Query = "Select * from $TABLE_NAME where $COLOUMN_Movie_TITLE_liked"
        val cursor = db.rawQuery(Query, null)
        if (cursor.count <= 0) {
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "liked.db"
        private val TABLE_NAME = "likes"
        private val COLOUMN_ID = "_id"
        private val COLOUMN_Movie_TITLE_liked = "_ml"
    }

}
