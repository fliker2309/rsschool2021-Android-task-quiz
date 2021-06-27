package com.rsschool.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rsschool.quiz.data.Constants
import com.rsschool.quiz.databinding.ActivityMainBinding

private const val TAG = "myLog"

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
            Log.d(TAG, question.userAnswer)
        }
        return "You result is $resultCount of ${questions.size}"
    }

    override fun getShareText(): String {
        val resultStr: StringBuilder = StringBuilder("You complete the quiz!\n")
        resultStr.append(countResult()).append("\n")
        val questions = Constants.getQuestions()
        for ((index, question) in questions.withIndex()) {
            resultStr.append(index+1).append(".")
            resultStr.append(question.question).append("\n")
            Log.d(TAG, question.userAnswer)
            resultStr.append("You answered: ").append(question.userAnswer).append("\n")
           /* when (question.userAnswer) {

                2131231002 -> resultStr.append("You answered: ${question.optionOne}\n")//213 123 1002
                2131231004 -> resultStr.append("You answered: ${question.optionTwo}\n")//2131231004
                2131231003 -> resultStr.append("You answered: ${question.optionThree}\n")//2131231003
                2131231001 -> resultStr.append("You answered: ${question.optionFour}\n")//2131231001
                2131231000 -> resultStr.append("You answered: ${question.optionFive}\n") //2131231000
            }*/
            resultStr.append(question.userAnswer).append("TEST")
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