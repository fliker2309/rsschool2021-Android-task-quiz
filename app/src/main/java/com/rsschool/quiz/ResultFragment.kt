package com.rsschool.quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding: FragmentResultBinding
        get() = _binding!!
    private var mainInterface: QuizInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is QuizInterface)
            mainInterface = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)

        binding.apply {
            textResult.text = mainInterface?.countResult()
            icRepeat.setOnClickListener {
                mainInterface?.repeatQuiz()
            }
            icClose.setOnClickListener {
                mainInterface?.closeApp()
            }
            icShare.setOnClickListener {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"

                    putExtra(Intent.EXTRA_TEXT, mainInterface?.getShareText())
                }
                startActivity(intent)
            }
            return binding.root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        mainInterface = null
    }

    companion object {
        fun newInstance() = ResultFragment()
    }
}



