package com.rsschool.quiz.data.model

data class Question(
    val question: String,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val optionFive: String,
    val correctAnswer: String,
    var selectedCheckedIdButton : Int = -1,
    var userAnswer: String = ""
)
