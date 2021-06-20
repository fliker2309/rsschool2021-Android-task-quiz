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
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, QuizQuestionsFragment.newInstance(0,0, intArrayOf(0)))
                .commit()
        }
    }

    private fun openQuizFragment(previousNumber: Int, correctAnswers: Int, answers: IntArray?) {

        supportFragmentManager.beginTransaction()
            .replace(binding.container.id,QuizQuestionsFragment.newInstance(previousNumber,correctAnswers, answers))
            .commit()
    }

    private fun openResultFragment(result: String, numberOfQuestion: String){
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id,ResultFragment.newInstance(result, numberOfQuestion))
            .commit()
    }

    override fun shareResult(result: String?) {
        //TODO

    }

    override fun repeatQuiz() {
    //TODO
    }

    override fun openQuestion(numOfQuestion: Int?, correctAnswers: Int?, answers: IntArray?) {
        if (numOfQuestion != null && correctAnswers != null && numOfQuestion < 5)
            openQuizFragment(numOfQuestion, correctAnswers, answers)
        if (numOfQuestion != null && correctAnswers != null && numOfQuestion == 5)
            openResultFragment(correctAnswers, numberOfQuestion)
    }

}