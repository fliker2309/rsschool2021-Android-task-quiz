package com.rsschool.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rsschool.quiz.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), ClickListener, PassData {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            openQuizFragment(0, 0, intArrayOf(0, 0, 0, 0, 0))
        }
    }

    private fun openQuizFragment(
        previousNumber: Int,
        correctAnswers: Int,
        answers: IntArray?
    ) {
        supportFragmentManager.beginTransaction()
            .replace(
                binding.container.id,
                QuizQuestionsFragment.newInstance(previousNumber, correctAnswers, answers)
            )
            .commit()
    }

    private fun openResultFragment(result: Int, answers: IntArray?) {
        supportFragmentManager.beginTransaction()
            .replace(
                binding.container.id,
                ResultFragment.newInstance(result, answers)
            )
            .commit()
    }

    override fun repeatQuiz() {
        openQuizFragment(0, 0, intArrayOf(0, 0, 0, 0, 0))
    }

    override fun openQuestion(
        numOfQuestion: Int?,
        correctAnswers: Int?,
        answers: IntArray?
    ) {
        when (numOfQuestion) {
            0, 1, 2, 3, 4 -> correctAnswers?.let { openQuizFragment(numOfQuestion, it, answers) }
            5 -> correctAnswers?.let { openResultFragment(it, answers) }
        }
    }

}