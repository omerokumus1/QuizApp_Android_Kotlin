package com.example.quizapp_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn: Button = findViewById(R.id.startBtn)
        val nameText: EditText = findViewById(R.id.nameText)

        startBtn.setOnClickListener {
            if (nameText.text.isEmpty()) {
                Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, nameText.text.toString())
                startActivity(intent)
            }
        }
    }
}