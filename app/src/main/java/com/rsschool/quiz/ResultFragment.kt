package com.rsschool.quiz

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.rsschool.quiz.data.model.Question
import com.rsschool.quiz.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var result: String? = null
    private var numberOfQuestions: String? = null

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
            result = it.getString(RESULT)
            numberOfQuestions = it.getString(NUMBER_OF_QUESTIONS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mQuestionsList: ArrayList<Question>? = null
        val count =
            mQuestionsList?.count { question -> question.correctAnswer == question.checkedRadioButtonId }

        binding.apply {

            icShare.setOnClickListener {
                val intent = Intent().apply {
                    action = Intent.ACTION_SENDTO
                    data = Uri.parse("mailto")
                    val body = StringBuilder("${binding.textResult.text}\n\n")

                }
            }



            icRepeat.setOnClickListener {
                resultAction?.repeatQuiz()
            }
            icClose.setOnClickListener {
                requireActivity().finish()
            }
        }
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
        private const val NUMBER_OF_QUESTIONS = "numberOfQuestions"

        fun newInstance(result: String, numberOfQuestion: String): Fragment {
            return ResultFragment().apply {
                arguments = Bundle().apply {
                    putString(RESULT, result)
                    putString(NUMBER_OF_QUESTIONS, numberOfQuestions)
                }
            }


        }
    }
}



