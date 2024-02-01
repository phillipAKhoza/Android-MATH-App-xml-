package com.phillipdev.mathapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Locale
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private lateinit var score: TextView
    private lateinit var live: TextView
    private lateinit var time: TextView
    private lateinit var btnOk: Button
    private lateinit var btnNext: Button
    private lateinit var question: TextView
    private lateinit var answer: EditText
    private var correctAnswer = 0
    private var userScore = 0
    var userLive = 3
    private lateinit var  timer: CountDownTimer
    private  val startTimeMms : Long = 40000
    var timeLeftMms : Long = startTimeMms

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        var game = intent.getStringExtra("game")
        supportActionBar!!.title= game

        score = findViewById(R.id.tvScore)
        live = findViewById(R.id.tvLives)
        time = findViewById(R.id.tvTimer)
        btnNext = findViewById(R.id.btnNext)
        btnOk = findViewById(R.id.btnOk)
        question = findViewById(R.id.tvQuestion)
        answer = findViewById(R.id.etnAnswer)
        getQuestion(game)
        btnOk.setOnClickListener {
            val input = answer.text.toString()
            if (input== ""){
                Toast.makeText(applicationContext,
                    "Please enter the answer of Move to the next question",
                    Toast.LENGTH_LONG).show()
            }else{
                pauseTimer()
                    val inputAnswer = input.toInt()

                if (inputAnswer == correctAnswer){
                    userScore += 10
                    score.text = userScore.toString()
                    question.text = "Correct ;)!!!"
                }else{

                        userLive -= 1
                        live.text = userLive.toString()
                        question.text = "Incorrect :(..."


                }
            }
        }

        btnNext.setOnClickListener {
            pauseTimer()
            resetTimer()

            answer.setText("")

            if (userLive ==0) {
                Toast.makeText(applicationContext,"Game Over!!!",Toast.LENGTH_LONG).show()
                val intent = Intent(this@GameActivity, ResultsActivity::class.java)
                intent.putExtra("score", userScore)
                startActivity(intent)
                finish()
            }else{
                getQuestion(game)
            }
        }
    }

    private fun getQuestion(game: String?) {
        val num1 =Random.nextInt(0,100)
        val num2 =Random.nextInt(0,100)

        if(game =="Addition"){
            question.text= "$num1 + $num2"

            correctAnswer = num1+num2
        }else if (game == "Subtraction"){
            question.text= "$num1 - $num2"

            correctAnswer = num1-num2
        }else{
            question.text= "$num1 x $num2"

            correctAnswer = num1*num2
        }


        startCountDown()
    }
    fun startCountDown(){
        timer = object : CountDownTimer(timeLeftMms, 1000){

            override fun onTick(mmsLeft: Long) {

                timeLeftMms = mmsLeft
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()
                userLive--
                live.text = userLive.toString()
                question.text="Time Up :(..."
            }

        }.start()
    }

    fun updateText(){
        val remainingTime: Int = (timeLeftMms/1000).toInt()

        time.text = String.format(Locale.getDefault(), "%02d", remainingTime)
    }
    fun pauseTimer(){
        timer.cancel()
    }
    fun resetTimer(){
        timeLeftMms = startTimeMms
        updateText()
    }

}