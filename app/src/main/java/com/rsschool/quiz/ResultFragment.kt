package com.rsschool.quiz

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.data.Constants
import com.rsschool.quiz.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var result: Int? = null
    private var answers: IntArray? = null

    private var _binding: FragmentResultBinding? = null
    private val binding: FragmentResultBinding
        get() = _binding!!
    private var resultAction: ClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickListener)
            resultAction = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            result = it.getInt(RESULT)
            answers = it.getIntArray(ANSWERS_LIST)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)

        binding.apply {
            textResult.text = "Result is: $result/5"
            icRepeat.setOnClickListener {
                resultAction?.repeatQuiz()
            }
            icClose.setOnClickListener {
                requireActivity().moveTaskToBack(true)
                requireActivity().finish()
            }
            icShare.setOnClickListener {
                val intent = Intent().apply {
                    action = Intent.ACTION_SENDTO
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_TEXT, prepareMessage(answers, result))
                }
                startActivity(intent)
            }
            return binding.root
        }
    }

    private fun prepareMessage(answers: IntArray?, result: Int?): String {
        val resultStr: StringBuilder = StringBuilder("Your result is: ")
        resultStr.append(result).append("!").append("\n")

        val questions = Constants.getQuestions()
        for ((count, question) in questions.withIndex()) {
            resultStr.append("$count").append(".")
            resultStr.append(question.question).append("\n")
            resultStr.append("You answered: ")
            when (answers?.get(count).toString().last()) {
                '2' -> resultStr.append(question.optionOne)
                '4' -> resultStr.append(question.optionTwo)
                '3' -> resultStr.append(question.optionThree)
                '1' -> resultStr.append(question.optionFour)
                '0' -> resultStr.append(question.optionFive)
            }
            resultStr.append("\nCorrect is: ${question.correctAnswer}\n")
        }
        return resultStr.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        resultAction = null
    }

    companion object {
        private const val RESULT = "result"
        private const val ANSWERS_LIST = "answers"

        fun newInstance(result: Int, answers: IntArray?): Fragment {
            return ResultFragment().apply {
                arguments = Bundle().apply {
                    putInt(RESULT, result)
                    putIntArray(ANSWERS_LIST, answers)
                }
            }
        }
    }
}



