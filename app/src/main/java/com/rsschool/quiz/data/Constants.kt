package com.rsschool.quiz.data

import com.rsschool.quiz.data.model.Question

object Constants {

    private val questionList = arrayListOf<Question>(
        Question(
            "What is the capital of Great Britain?",
            "Madrid",
            "London",
            "Paris",
            "Istanbul",
            "Manchester",
            "London"

        ), Question(
            "Who won the 2018 FIFA World Cup?",
            "France",
            "Croatia",
            "Russia",
            "Belgium",
            "England",
            "France"
        ), Question(
            "In what year did Yuri Gagarin flew into space?",
            "1957",
            "1968",
            "1959",
            "1961",
            "1969",
            "1961"
        ), Question(
            "Who is the developer of Kotlin??",
            "Google",
            "Intellij IDEA",
            "JetBrains",
            "Eclipse",
            "Oracle",
            "JetBrains"
        ), Question(
            "Who wrote the book The Little Prince?",
            "Daniel Defoe",
            "Oscar Wilde ",
            "Lewis Carroll",
            "William Shakespeare",
            "Antoine de Saint-Exupéry",
            "Antoine de Saint-Exupéry"
        )
    )

    fun getQuestions(): ArrayList<Question> {
        return questionList
    }

}
