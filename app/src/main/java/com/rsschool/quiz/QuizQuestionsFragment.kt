package com.rsschool.quiz

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.rsschool.quiz.data.Constants
import com.rsschool.quiz.data.model.Question
import com.rsschool.quiz.databinding.FragmentQuizBinding

class QuizQuestionsFragment : Fragment() {

    private var numOfQuestion: Int? = null
    private var correctAnswers: Int? = null
    private var answers: IntArray? = null
    private var mQuestionsList: ArrayList<Question>? = null
    private var passData: PassData? = null

    private var _binding: FragmentQuizBinding? = null
    private val binding: FragmentQuizBinding
        get() = _binding!!


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is PassData)
            passData = context

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            numOfQuestion = it.getInt(NUMBER_OF_QUESTION)
            correctAnswers = it.getInt(CORRECT_ANSWERS)
            answers = it.getIntArray(ANSWERS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        numOfQuestion?.let { changeTheme(it) }
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        mQuestionsList = Constants.getQuestions()
        setQuestion()


        binding.nextButton.isEnabled = false
        binding.toolbar.title = "Question $numOfQuestion"
        //TODO дописать обработку кнопки next при выборе ответа

//correctAnswer = count

        val count =
            mQuestionsList?.count { question -> question.correctAnswer == question.checkedRadioButtonId }


        when (numOfQuestion) {
            1 -> binding.previousButton.isEnabled = false
            5 -> binding.nextButton.text = "Submit"
        }

        binding.toolbar.setOnClickListener {
            //TODO дописать обработку кнопки назад
        }

        return binding.root
    }


    override fun onDetach() {
        super.onDetach()
        passData = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun changeTheme(numOfQuestion: Int) {
        when (numOfQuestion % 5) {
            0 -> {
                requireContext().setTheme(R.style.Theme_Quiz_First)
            }
            1 -> {
                requireContext().setTheme(R.style.Theme_Quiz_Second)
            }
            2 -> {
                requireContext().setTheme(R.style.Theme_Quiz_Third)
            }
            3 -> {
                requireContext().setTheme(R.style.Theme_Quiz_Fourth)
            }
            4 -> {
                requireContext().setTheme(R.style.Theme_Quiz_Fifth)
            }
        }
    }

    private fun setQuestion() {
        val thisQuestion = mQuestionsList?.get(numOfQuestion!! - 1)
        binding.apply {
            if (thisQuestion != null) {
                question.text = thisQuestion.question
                optionOne.text = thisQuestion.optionOne
                optionTwo.text = thisQuestion.optionTwo
                optionThree.text = thisQuestion.optionThree
                optionFour.text = thisQuestion.optionFour
                optionFive.text = thisQuestion.optionFive
            }
        }
    }

    companion object {

        private const val NUMBER_OF_QUESTION = "numberOfQuestion"
        private const val CORRECT_ANSWERS = "correctAnswers"
        private const val ANSWERS = "answers"

        fun newInstance(
            numOfQuestion: Int,
            correctAnswers: Int,
            answers: IntArray?
        ): QuizQuestionsFragment = QuizQuestionsFragment().apply {
            arguments = Bundle().apply {
                putInt(NUMBER_OF_QUESTION, numOfQuestion)
                putInt(CORRECT_ANSWERS, correctAnswers)
                putIntArray(ANSWERS, answers)
            }
        }
    }

}




