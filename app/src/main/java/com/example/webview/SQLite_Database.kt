package com.example.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SQLite_Database : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite_database)
        var context = this
        val btn_Update = findViewById<Button>(R.id.btn_Update)
        val btn_Delete = findViewById<Button>(R.id.btn_Delete)
        val btn_Read = findViewById<Button>(R.id.btn_Read)
        val btn_Insert = findViewById<Button>(R.id.btn_Insert)
        val tvtResult = findViewById<TextView>(R.id.tvResult)
        val etvName = findViewById<EditText>(R.id.etvName)
        val etvAge = findViewById<EditText>(R.id.etvAge)
        btn_Insert.setOnClickListener({
            if(etvName.text.toString().length >0 &&
                    etvAge.text.toString().length >0){

                var user = User(etvName.text.toString(),etvAge.text.toString().toInt())
                var db = DataBaseHandler(context)
                db.insertData(user)

            }else{
                Toast.makeText(context,"Please fill all data",Toast.LENGTH_LONG).show()
            }

        })

        btn_Read.setOnClickListener({
            var db = DataBaseHandler(context)
            var data = db.readData()
            tvtResult.text=""
            for(i in 0..(data.size-1)){
                tvtResult.append(data.get(i).id.toString() + " " +data.get(i).name+ " " + data.get(i).age+"/n")
        }
        })

        btn_Update.setOnClickListener({
            var db = DataBaseHandler(context)
            db.UpdateData()
            btn_Read.performClick()
        })

            btn_Delete.setOnClickListener({
                var db = DataBaseHandler(context)
                db.deleteDate()
                btn_Delete.performClick()
            })
    }
}