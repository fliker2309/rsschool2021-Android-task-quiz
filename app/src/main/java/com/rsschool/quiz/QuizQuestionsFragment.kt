package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import com.rsschool.quiz.data.Constants
import com.rsschool.quiz.data.model.Question
import com.rsschool.quiz.databinding.FragmentQuizBinding

class QuizQuestionsFragment : Fragment() {

    private var numOfQuestion: Int? = null
    private var quizQuestion: Question? = null
    private var mQuestionsList: ArrayList<Question>? = null
    private var passData: QuizInterface? = null

    private var _binding: FragmentQuizBinding? = null
    private val binding: FragmentQuizBinding
        get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is QuizInterface)
            passData = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            numOfQuestion = it.getInt(NUMBER_OF_QUESTION)
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
        binding.apply {
            if (quizQuestion?.userAnswer != -1) {
                radioGroup.forEachIndexed { index, view ->
                    if (index == quizQuestion?.userAnswer) {
                        radioGroup.check(view.id)
                    }
                }
            }

            enableButton()
            setQuestion()
            onPreviousClickListener()
            onNextClickListener()
            nextButton.isEnabled = false
            toolbar.title = "Question ${numOfQuestion?.plus(1)}"

            radioGroup.setOnCheckedChangeListener { _, _ ->
                radioGroup.forEachIndexed { index, view ->
                    if ((view as RadioButton).isChecked) {
                        quizQuestion?.userAnswer = index //здесь должно присваиваться
                        Toast.makeText(context, "$index", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            nextButton.isEnabled = true
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
        quizQuestion?.userAnswer = -1
        numOfQuestion = numOfQuestion?.dec()
        passData?.openQuestion(numOfQuestion)
    }

    private fun onPreviousClickListener() {
        binding.previousButton.setOnClickListener {
            onBackClickListener()
        }
    }

    private fun onNextClickListener() {

        binding.nextButton.setOnClickListener {
            when (binding.radioGroup.checkedRadioButtonId) {
                0 -> quizQuestion?.userAnswer = 0
                1 -> quizQuestion?.userAnswer = 1
                2 -> quizQuestion?.userAnswer = 2
                3 -> quizQuestion?.userAnswer = 3
                4 -> quizQuestion?.userAnswer = 4
            }
            Toast.makeText(context, "${quizQuestion?.userAnswer}", Toast.LENGTH_SHORT).show()
            numOfQuestion = numOfQuestion?.inc()
            passData?.openQuestion(numOfQuestion)
        }
    }

    private fun enableButton() {

        binding.apply {
            when (numOfQuestion) {
                0 -> previousButton.isEnabled = false
                4 -> nextButton.text = "Submit"
                else -> previousButton.isEnabled = true
            }

            if (numOfQuestion == 0) {
                toolbar.navigationIcon = null
            } else toolbar.setOnClickListener {
                onBackClickListener()
            }
        }
    }

    companion object {

        private const val NUMBER_OF_QUESTION = "numberOfQuestion"

        fun newInstance(numOfQuestion: Int) = QuizQuestionsFragment().apply {
            arguments = Bundle().apply {
                putInt(NUMBER_OF_QUESTION, numOfQuestion)
            }
        }
    }
}




