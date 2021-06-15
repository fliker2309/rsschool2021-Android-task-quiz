package com.rsschool.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding: FragmentQuizBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }













    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        fun newInstance(/*result: Int, theme: Int*/): QuizFragment {
            val args = Bundle()
        /*    args.putInt(THEME, theme)
            args.putInt(QUIZ_RESULT, result)*/
            val fragment = QuizFragment()
            fragment.arguments = args
            return fragment
        }
        const val TAG = "quizFragment"
        private const val QUIZ_RESULT = ""
        private const val THEME = "THEME"


    }


}