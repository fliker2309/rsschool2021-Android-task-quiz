package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.activity.OnBackPressedCallback
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
            answers = it.getIntArray(ANSWERS_LIST)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        numOfQuestion?.let { changeTheme(it) }
        mQuestionsList = Constants.getQuestions()

        setQuestion()
        binding.nextButton.isEnabled = false

        when (numOfQuestion) {
            0 -> binding.toolbar.navigationIcon = null
            else -> binding.toolbar.setOnClickListener {
                onBackClickListener()
            }
        }

        binding.toolbar.title = "Question ${numOfQuestion?.plus(1)}"

        onPreviousClickListener()
        onNextClickListener()

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            binding.nextButton.isEnabled = true
            numOfQuestion?.let { answers?.set(it, checkedId) }
        }

        numOfQuestion?.let {
            answers?.get(it)?.let {
                if (answers!![numOfQuestion!!] != 0)
                    binding.radioGroup.check(it)
            }
        }

        when (numOfQuestion) {
            0 -> binding.previousButton.isEnabled = false
            4 -> binding.nextButton.text = "Submit"
            else -> binding.previousButton.isEnabled = true
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackClickListener()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
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
            0 -> requireContext().setTheme(R.style.Theme_Quiz_First)
            1 -> requireContext().setTheme(R.style.Theme_Quiz_Second)
            2 -> requireContext().setTheme(R.style.Theme_Quiz_Third)
            3 -> requireContext().setTheme(R.style.Theme_Quiz_Fourth)
            4 -> requireContext().setTheme(R.style.Theme_Quiz_Fifth)
        }
    }

    private fun setQuestion() {
        val thisQuestion = mQuestionsList?.get(numOfQuestion!!)
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

    private fun onBackClickListener() {
        binding.radioGroup.also {
            val selected = it.findViewById<RadioButton>(it.checkedRadioButtonId)
            if (selected != null /*&& selected.text == mQuestionsList!![numOfQuestion!!].correctAnswer*/) {
                correctAnswers = correctAnswers?.dec()
            }
        }
        numOfQuestion = numOfQuestion?.dec()
        passData?.openQuestion(numOfQuestion, correctAnswers, answers)
    }

    private fun onPreviousClickListener() {
        binding.previousButton.setOnClickListener {
            onBackClickListener()
        }
    }

    private fun onNextClickListener() {
        binding.nextButton.setOnClickListener {
            binding.radioGroup.also {
                val selected = it.findViewById<RadioButton>(it.checkedRadioButtonId)
                if (selected.text == mQuestionsList!![numOfQuestion!!].correctAnswer) {
                    correctAnswers = correctAnswers?.inc()
                }
            }
            numOfQuestion = numOfQuestion?.inc()
            passData?.openQuestion(numOfQuestion, correctAnswers, answers)
        }
    }

    companion object {

        private const val NUMBER_OF_QUESTION = "numberOfQuestion"
        private const val CORRECT_ANSWERS = "correctAnswers"
        private const val ANSWERS_LIST = "answers"

        fun newInstance(
            numOfQuestion: Int,
            correctAnswers: Int,
            answers: IntArray?
        ): QuizQuestionsFragment = QuizQuestionsFragment().apply {
            arguments = Bundle().apply {
                putInt(NUMBER_OF_QUESTION, numOfQuestion)
                putInt(CORRECT_ANSWERS, correctAnswers)
                putIntArray(ANSWERS_LIST, answers)
            }
        }
    }

}




