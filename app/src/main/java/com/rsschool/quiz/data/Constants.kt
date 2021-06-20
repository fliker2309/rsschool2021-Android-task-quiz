package com.rsschool.quiz.data

import com.rsschool.quiz.data.model.Question

object Constants {

    fun getQuestions(): ArrayList<Question> {

        val questionList = ArrayList<Question>()
        val question1 = Question(
            1,
            "What is the capital of Great Britain?",
            " Madrid",
            "London",
            "Paris",
            "Istanbul",
            "Manchester",
            2,
            0,

        )
        questionList.add(question1)

        val question2 = Question(
            2,
            "Who won the 2018 FIFA World Cup?",
            "France",
            "Croatia",
            "Russia",
            "Belgium",
            "England",
            1,
            0,

        )
        questionList.add(question2)

        val question3 = Question(
            3,
            "In what year did Yuri Gagarin flew into space?",
            "1957",
            "1968",
            "1959",
            "1961",
            "1969",
            4,
            0,

        )
        questionList.add(question3)

        val question4 = Question(
            4,
            "Who is the developer of Kotlin??",
            "Google",
            "Intellij IDEA",
            "JetBrains",
            "Eclipse",
            "Oracle",
            3,
            0,

        )
        questionList.add(question4)

        val question5 = Question(
            5,
            "Who wrote the book The Little Prince?",
            "Daniel Defoe",
            "Oscar Wilde ",
            "Lewis Carroll",
            "William Shakespeare",
            "Antoine de Saint-Exupéry",
            5,
            0,

        )
        questionList.add(question5)

        return questionList
    }
}