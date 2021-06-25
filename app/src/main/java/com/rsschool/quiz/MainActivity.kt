package com.rsschool.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rsschool.quiz.data.Constants
import com.rsschool.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), QuizInterface {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            openQuizFragment(0)
        }
    }

    private fun openQuizFragment(previousNumber: Int) {
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, QuizQuestionsFragment.newInstance(previousNumber))
            .commit()
    }

    private fun openResultFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, ResultFragment.newInstance())
            .commit()
    }

    override fun repeatQuiz() {
        openQuizFragment(0)
    }

    override fun countResult(): String {
        var resultCount = 0
        val questions = Constants.getQuestions()
        for (question in questions) {
            if (question.userAnswer == question.correctAnswer)
                resultCount++
        }
        return "You result is $resultCount of ${questions.size}"
    }

    override fun getShareText(): String {
        val resultStr: StringBuilder = StringBuilder("You complete the quiz! ")
        resultStr.append(countResult()).append("\n")

        val questions = Constants.getQuestions()
        for ((index, question) in questions.withIndex()) {
            resultStr.append("$index").append(".")
            resultStr.append(question.question).append("\n")
            resultStr.append("You answered: ${question.userAnswer} ")
            when (question.userAnswer) {
                0 -> resultStr.append(question.optionOne)
                1 -> resultStr.append(question.optionTwo)
                2 -> resultStr.append(question.optionThree)
                3 -> resultStr.append(question.optionFour)
                4 -> resultStr.append(question.optionFive)
            }
            resultStr.append("\nCorrect is: ${question.correctAnswer}\n")
        }
        return resultStr.toString()
    }

    override fun closeApp() {
        finish()
    }

    override fun openQuestion(numOfQuestion: Int?) {
        when (numOfQuestion) {
            5 -> openResultFragment()
            else -> openQuizFragment(numOfQuestion!!)
        }
    }

}