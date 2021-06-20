package com.rsschool.quiz.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    val id: Int,
    val question: String,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val optionFive: String,
    val correctAnswer: Int,
    var checkedRadioButtonId: Int,
) : Parcelable
