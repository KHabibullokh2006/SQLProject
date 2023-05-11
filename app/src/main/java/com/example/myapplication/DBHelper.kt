package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, 1) {
    companion object{
        const val DB_NAME = "contact"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        var query = "create table 'contact'('id' integer NOT NULL PRIMARY KEY AUTOINCREMENT, 'name' TEXT NOT NULL, 'phone_number' INTEGER NOT NULL)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun addContact(contact: Contact){
        val writabledb = this.writableDatabase
        var cv = ContentValues()
        cv.put("name", contact.name)
        cv.put("phone_number", contact.phone)
        writabledb.insert("contact",null,cv)
    }



}