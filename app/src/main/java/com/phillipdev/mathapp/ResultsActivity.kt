package com.phillipdev.mathapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultsActivity : AppCompatActivity() {
    private  lateinit var score: TextView
    private  lateinit var tryAgain : Button
    private  lateinit var exit: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        score = findViewById(R.id.tvRSore)
        tryAgain = findViewById(R.id.btnTryAgain)
        exit = findViewById(R.id.btnExit)

        val userScore = intent.getIntExtra("score",0)

        score.text = "Your Score: $userScore"

        tryAgain.setOnClickListener {
            val intent = Intent(this@ResultsActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        exit.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}