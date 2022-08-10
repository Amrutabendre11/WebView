package com.example.webview

import android.app.TaskStackBuilder
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "MyDB"
val TABLE_NAME="Users"
val COL_NAME = "name"
val COL_AGE="age"
val COL_ID ="id"

class DataBaseHandler(var context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null,1)
{
    override fun onCreate(db: SQLiteDatabase?) {
       val createTable = "CREATE TABLE " + TABLE_NAME +" (" +
               COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
               COL_NAME + " VARCHAR(256)," +
               COL_AGE +" INTEGER)";

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
    fun insertData(user:User){
        val db = this.writableDatabase
        var cv =ContentValues()
        cv.put(COL_NAME,user.name)
        cv.put(COL_AGE,user.age)
        var result = db.insert(TABLE_NAME,null,cv)

        if(result == -1.toLong())
            Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show()
        else
            Toast.makeText(context,"Sucess",Toast.LENGTH_LONG).show()

    }
    fun readData() : MutableList<User>{
        val list : MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query ="Select * from " + TABLE_NAME
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do{
                var user = User()
                user.id = result.getString(0).toInt()
                user.name = result.getString(1)
                user.age = result.getString(2).toInt()
                list.add(user)
            }while(result.moveToNext())
        }
        result.close()
        return list
    }
    fun deleteDate()
    {
        var db =this.writableDatabase
        db.delete(TABLE_NAME, COL_ID+"=?",arrayOf(1.toString()))
        db.close()
    }
    fun UpdateData() {
        val list : MutableList<User> = ArrayList()

        val db = this.writableDatabase
        val query ="Select * from " + TABLE_NAME
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do{
                var cv = ContentValues()
                cv.put(COL_AGE,result.getInt(2)+1)
                db.update(TABLE_NAME,cv, COL_ID+"=? AND " + COL_NAME + "=?",
                arrayOf(result.getString(0),
                result.getString(1)))

            }while(result.moveToNext())
        }
        result.close()
     db.close()
    }
}