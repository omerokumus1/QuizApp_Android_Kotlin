package com.example.quizapp_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val userName = intent.getStringExtra(Constants.USER_NAME)
        val totalQuestions = intent.getStringExtra(Constants.TOTAL_QUESTIONS)
        val correctAnswers = intent.getStringExtra(Constants.CORRECT_ANSWERS)

        val userNameText: TextView = findViewById(R.id.userNameText)
        val resultText: TextView = findViewById(R.id.resultText)

        userNameText.text = userName
        resultText.text = "Your score is $correctAnswers out of $totalQuestions"

        val finishBtn: Button = findViewById(R.id.finishButton)
        finishBtn.setOnClickListener {
            finish()
        }

    }
}