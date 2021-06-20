package com.rsschool.quiz

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rsschool.quiz.data.Constants
import com.rsschool.quiz.data.model.Question
import com.rsschool.quiz.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), ClickListener,PassData {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            openQuizFragment(0,0, intArrayOf(0))

        }
    }

    private fun openQuizFragment(previousNumber: Int, correctAnswers: Int, answers: IntArray?) {

        supportFragmentManager.beginTransaction()
            .replace(binding.container.id,QuizQuestionsFragment.newInstance(previousNumber,correctAnswers, answers))
            .commit()
    }

    private fun openResultFragment(result: Int, totalQuestions: Int){
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id,ResultFragment.newInstance(result, totalQuestions))
            .commit()
    }

    override fun shareResult(result: String?) {
        //TODO

    }

    override fun repeatQuiz() {
        openQuizFragment(0,0, intArrayOf(0))
    }

    override fun openQuestion(numOfQuestion: Int?, correctAnswers: Int?, answers: IntArray?) {
    //TODO
    }

}