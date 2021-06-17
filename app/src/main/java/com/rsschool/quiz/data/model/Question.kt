package com.rsschool.quiz.data.model

data class Question(
    val id: Int,
    val question: String,
    val optionOne: String,
    val optionTho: String,
    val optionThree: String,
    val optionFour: String,
    val optionFive: String,
    val correctAnswer: Int
)
