package com.phillipdev.mathapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var add : Button
    lateinit var subtract : Button
    lateinit var multiple : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add = findViewById(R.id.btnAdd)
        subtract = findViewById(R.id.btnSubt)
        multiple = findViewById(R.id.btnMult)

        add.setOnClickListener {
            var intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("game","Addition")
            startActivity(intent)
        }
        subtract.setOnClickListener {
            var intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("game","Subtraction")
            startActivity(intent)
        }
        multiple.setOnClickListener {
            var intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("game","Multiplication")
            startActivity(intent)
        }
    }
}