package com.rsschool.quiz.data.model

data class Question(
    val question: String,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val optionFive: String,
    var correctAnswer: Int,
    var userAnswer: Int = -1
)
