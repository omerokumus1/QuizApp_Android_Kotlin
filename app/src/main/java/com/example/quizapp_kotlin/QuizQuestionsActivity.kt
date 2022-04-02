package com.example.quizapp_kotlin

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    var question: TextView? = null
    var image: ImageView? = null
    var progressBar: ProgressBar? = null
    var progress: TextView? = null
    var optionOne: TextView? = null
    var optionTwo: TextView? = null
    var optionThree: TextView? = null
    var optionFour: TextView? = null
    var submitBtn: Button? = null
    var questionNumber = 0
    var correctAnswers = 0
    var selectedOption: TextView? = null
    var selectedOptionIndex: Int? = null

    var questions: ArrayList<Question>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)


        getViews()

        placeFirstQuestion()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        optionOne?.setOnClickListener(this)
        optionTwo?.setOnClickListener(this)
        optionThree?.setOnClickListener(this)
        optionFour?.setOnClickListener(this)
        submitBtn?.setOnClickListener(this)
    }

    private fun placeFirstQuestion() {
        placeQuestion(questions?.get(questionNumber))
    }

    private fun placeQuestion(questionToPlace: Question?) {
        question?.text = questionToPlace?.question
        questionToPlace?.image?.let { image?.setImageResource(it) }
        progressBar?.progress = 1
        progress?.text = "${questionToPlace?.id}/${progressBar?.max}"
        optionOne?.text = questionToPlace?.optionOne
        optionTwo?.text = questionToPlace?.optionTwo
        optionThree?.text = questionToPlace?.optionThree
        optionFour?.text = questionToPlace?.optionFour
    }

    private fun getViews() {
        question = findViewById(R.id.questionText)
        image = findViewById(R.id.flagImage)
        progressBar = findViewById(R.id.progressBar)
        progress = findViewById(R.id.progress)
        optionOne = findViewById(R.id.optionOne)
        optionTwo = findViewById(R.id.optionTwo)
        optionThree = findViewById(R.id.optionThree)
        optionFour = findViewById(R.id.optionFour)
        submitBtn = findViewById(R.id.submitBtn)

        questions = Constants.getQuestions()
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.optionOne -> {
                setDefaultBackgrounds()
                optionOne?.let {
                    it.setBackgroundResource(R.drawable.selected_option_border_bg)
                    it.setTypeface(it.typeface, Typeface.BOLD)
                }
                selectedOption = optionOne
                selectedOptionIndex = 0
            }
            R.id.optionTwo -> {
                setDefaultBackgrounds()
                optionTwo?.let {
                    it.setBackgroundResource(R.drawable.selected_option_border_bg)
                    it.setTypeface(it.typeface, Typeface.BOLD)
                }
                selectedOption = optionTwo
                selectedOptionIndex = 1
            }
            R.id.optionThree -> {
                setDefaultBackgrounds()
                optionThree?.let {
                    it.setBackgroundResource(R.drawable.selected_option_border_bg)
                    it.setTypeface(it.typeface, Typeface.BOLD)
                }
                selectedOption = optionThree
                selectedOptionIndex = 2
            }
            R.id.optionFour -> {
                setDefaultBackgrounds()
                optionFour?.let {
                    it.setBackgroundResource(R.drawable.selected_option_border_bg)
                    it.setTypeface(it.typeface, Typeface.BOLD)
                }
                selectedOption = optionFour
                selectedOptionIndex = 3
            }
            R.id.submitBtn -> {
                if (isSubmit()) {
                    handleAnswer()
                    questionNumber++
                    changeSubmitBtnText()
                }
                else if (isNext()){
                    placeQuestion(questions?.get(questionNumber))
                    submitBtn?.text = "SUBMIT"
                    setDefaultBackgrounds()
                }
                else if (isFinish()){

                }
            }
        }
    }

    private fun isFinish() = submitBtn?.text == "FINISH"

    private fun isNext() = submitBtn?.text == "NEXT"

    private fun isSubmit() = submitBtn?.text == "SUBMIT"

    private fun changeSubmitBtnText() {
        submitBtn?.text = if (lastQuestionAnswered()){
            "FINISH"
        }
        else {
            "NEXT"
        }
    }

    private fun lastQuestionAnswered() = questionNumber == 10

    private fun handleAnswer() {
        if (isSelectedOptionCorrect()) {
            selectedOption?.setBackgroundResource(R.drawable.correct_answer_bg)
            correctAnswers++
        } else {
            val correctOption = getCorrectOption()
            correctOption?.setBackgroundResource(R.drawable.correct_answer_bg)
            selectedOption?.setBackgroundResource(R.drawable.wrong_answer_bg)
        }
    }

    private fun getCorrectOption(): TextView? {
        return when (questions?.get(questionNumber)?.correctAnswer) {
            1 -> optionOne
            2 -> optionTwo
            3 -> optionThree
            4 -> optionFour
            else -> null
        }
    }

    private fun isSelectedOptionCorrect() =
        questions?.get(questionNumber)?.correctAnswer == (selectedOptionIndex?.plus(1))

    private fun setDefaultBackgrounds() {
        optionOne?.let {
            it.setBackgroundResource(R.drawable.default_option_border_bg)
            it.typeface = Typeface.DEFAULT
            it.setTextColor(Color.parseColor("#363a43"))
        }
        optionTwo?.let {
            it.setBackgroundResource(R.drawable.default_option_border_bg)
            it.typeface = Typeface.DEFAULT
            it.setTextColor(Color.parseColor("#363a43"))
        }
        optionThree?.let {
            it.setBackgroundResource(R.drawable.default_option_border_bg)
            it.typeface = Typeface.DEFAULT
            it.setTextColor(Color.parseColor("#363a43"))
        }
        optionFour?.let {
            it.setBackgroundResource(R.drawable.default_option_border_bg)
            it.typeface = Typeface.DEFAULT
            it.setTextColor(Color.parseColor("#363a43"))
        }
    }
}