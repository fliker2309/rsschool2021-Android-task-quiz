package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.activity.OnBackPressedCallback
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import com.rsschool.quiz.data.Constants
import com.rsschool.quiz.data.model.Question
import com.rsschool.quiz.databinding.FragmentQuizBinding

private const val TAG = "myLog"

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
        quizQuestion = mQuestionsList!![numOfQuestion!!] //initialisation?
        enableButton()
        setQuestion()
        onPreviousClickListener()
        onNextClickListener()

        binding.nextButton.isEnabled = false
        //при переходе на предыдущую страницу, чтение ИЗ дата класса наше ИД
        binding.run {
            if (quizQuestion?.selectedCheckedIdButton != -1) {
                radioGroup.check(quizQuestion?.selectedCheckedIdButton!!)
                Log.d(TAG, "Произошло чтение")
            } else
                Log.d(TAG, "Чтения не произошло, т.к. переменная SelectedCheckedIdButton для вопроса $numOfQuestion = ${quizQuestion?.selectedCheckedIdButton}")
        }

        binding.run {
           /* if (quizQuestion?.selectedCheckedIdButton != -1) {
                radioGroup.forEachIndexed { g, id ->
                    if (id == quizQuestion?.selectedCheckedIdButton) {
                        radioGroup.check(id)
                    }
                }
            }*///mb budet rabotat'       (для чтения при нажатии Превиус)


            toolbar.title = "Question ${numOfQuestion?.plus(1)}"
            //чекнутый вариант ответа сохранить в датакласс !!!!!записьб ппри клике selectById

            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                quizQuestion?.selectedCheckedIdButton = checkedId
                binding.nextButton.isEnabled = true
                quizQuestion?.userAnswer = binding.radioGroup.findViewById<RadioButton>(checkedId).text.toString()
                Log.d(TAG,"Произошла запись $checkedId , ${quizQuestion?.userAnswer} в переменную ${quizQuestion?.selectedCheckedIdButton} для вопроса $numOfQuestion")
            }

            /*radioGroup.setOnCheckedChangeListener { g, id -> //неверно использовано(группа, чекИд) g,id  вернуть должен себя и айдишку
                radioGroup.forEachIndexed { index, view -> //рассказ про адресаА и посылки, в лямбде-что с ними сделать
                    if ((view as RadioButton).isChecked) {
                        quizQuestion?.userAnswer = index
                        Log.e(TAG, "${quizQuestion?.userAnswer}") // button по id вытянуть текст
                    }
                    binding.nextButton.isEnabled = true
                }
            }*/
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

    private fun setQuestion() { //обязательно записать id чекнутого баттона, проверить, был ли вызван этот вопрос впервые? если не впервые, то написать.
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
        binding.run {
            if (quizQuestion?.selectedCheckedIdButton != -1) {
                radioGroup.check(quizQuestion?.selectedCheckedIdButton!!)
                Log.d(TAG, "Произошло чтение")
            } else
                Log.d(TAG, "Чтения не произошло, т.к. ${quizQuestion?.selectedCheckedIdButton}")
        }
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

           /* quizQuestion?.selectedCheckedIdButton = binding.radioGroup.checkedRadioButtonId*/
            /* Log.d(TAG, "${quizQuestion?.userAnswer}")*/

            numOfQuestion = numOfQuestion?.inc()
            passData?.openQuestion(numOfQuestion)
            Log.d(TAG, "${quizQuestion?.selectedCheckedIdButton} для вопроса $numOfQuestion")
        }
    }

    private fun enableButton() {
        binding.run {
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




