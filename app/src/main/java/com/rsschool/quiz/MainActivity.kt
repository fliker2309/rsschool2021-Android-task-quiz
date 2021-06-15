package com.rsschool.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        openQuestionFragment()

    }

    private fun openQuestionFragment() {
        val quizFragment = QuizFragment.newInstance()

        supportFragmentManager.beginTransaction()
            .add(R.id.container, quizFragment, QuizFragment.TAG)
            .commit()

    }
}