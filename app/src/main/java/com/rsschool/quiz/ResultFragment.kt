package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentResultBinding


class ResultFragment : Fragment(), ResultFragmentAction {

    private var _binding: FragmentResultBinding? = null
    private val binding: FragmentResultBinding
        get() = _binding!!

    private var resultAction: ResultFragmentAction? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ResultFragmentAction)
            resultAction = context
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
        val result = arguments?.getString(RESULT)
        val numberOfQuestion = arguments?.get(NUMBER_OF_QUESTION)
        val resultTextView = "Your result: $result/$numberOfQuestion "
        binding.apply {
            textResult.text = resultTextView
            icShare.setOnClickListener {
                result?.let { it1 -> resultAction?.submitResult(it1) }
            }
            icRepeat.setOnClickListener {
                resultAction?.repeatQuiz()
            }
            icClose.setOnClickListener {
                resultAction?.closeApp()
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


    override fun submitResult(result: String) {
        TODO("Not yet implemented")
    }

    override fun closeApp() {

    }

    override fun repeatQuiz() {
        TODO("Not yet implemented")
    }


    companion object {
        private const val RESULT = "totalResult"
        private const val NUMBER_OF_QUESTION = "numberOfQuestion"
        fun newInstance(result: String, numberOfQuestion: String): Fragment {
            return ResultFragment().apply {
                arguments = Bundle().apply {
                    putString(RESULT, result)
                    putString(NUMBER_OF_QUESTION, numberOfQuestion)
                }
            }
        }
    }

}

