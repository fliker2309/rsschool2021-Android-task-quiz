package com.rsschool.quiz

interface QuizInterface {
    fun openQuestion(numOfQuestion: Int?, /*correctAnswers: Int?, *//*answers: IntArray?*/)
    fun countResult() : String
    fun repeatQuiz()
    fun closeApp()
    fun getShareText(): String
}