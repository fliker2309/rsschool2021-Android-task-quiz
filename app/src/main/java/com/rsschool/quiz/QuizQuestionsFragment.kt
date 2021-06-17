package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.rsschool.quiz.data.Constants
import com.rsschool.quiz.data.model.Question
import com.rsschool.quiz.databinding.FragmentQuizBinding


class QuizQuestionsFragment : Fragment() {


    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOption: Int = 0


    private var _binding: FragmentQuizBinding? = null
    private val binding: FragmentQuizBinding
        get() = _binding!!


    private var previous: Button? = null
    private var next: Button? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)

        mQuestionsList = Constants.getQuestions()

        setQuestion()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        previous = binding.previousButton
        next = binding.nextButton

        binding.nextButton.setOnClickListener {
            //TODO
            if (mSelectedOption == 0) {
                next?.isEnabled = false
            }

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    private fun setQuestion() {

        mCurrentPosition = 1
        val thisQuestion = mQuestionsList?.get(mCurrentPosition - 1)

        binding.apply {
            question.text = thisQuestion?.question
            optionOne.text = thisQuestion?.optionOne
            optionTwo.text = thisQuestion?.optionTwo
            optionThree.text = thisQuestion?.optionThree
            optionFour.text = thisQuestion?.optionFour
            optionFive.text = thisQuestion?.optionFive
        }
    }




/* companion object {
     fun newInstance(*//*result: Int, theme: Int*//*): QuizFragment {
            val args = Bundle()
            *//*    args.putInt(THEME, theme)
                args.putInt(QUIZ_RESULT, result)*//*
            val fragment = QuizFragment()
            fragment.arguments = args
            return fragment
        }

        const val TAG = "quizFragment"
        private const val QUIZ_RESULT = ""
        private const val THEME = "THEME"


    }
*/


}