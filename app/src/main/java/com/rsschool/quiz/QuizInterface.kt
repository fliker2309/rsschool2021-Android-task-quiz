package com.rsschool.quiz

interface QuizInterface {
    fun openQuestion(numOfQuestion: Int?)
    fun countResult() : String
    fun repeatQuiz()
    fun closeApp()
    fun getShareText(): String
}