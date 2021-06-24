package com.rsschool.quiz.data

import com.rsschool.quiz.data.model.Question

object Constants {

    fun getAnswers(): ArrayList<Int> {
        return arrayListOf(1, 0, 3, 2, 4)
    }

    fun getQuestions(): ArrayList<Question> {

        val questionList = ArrayList<Question>()
        val answersList = ArrayList<Int>()
        val question1 = Question(
            0,
            "What is the capital of Great Britain?",
            "Madrid", //лондон
            "London",
            "Paris", //1
            "Istanbul",
            "Manchester",
            1,
        )
        questionList.add(question1)

        val question2 = Question(
            1,
            "Who won the 2018 FIFA World Cup?",
            "France",
            "Croatia",//бельгия
            "Russia", //1
            "Belgium",
            "England",
            0
        )
        questionList.add(question2)

        val question3 = Question(
            2,
            "In what year did Yuri Gagarin flew into space?",
            "1957",
            "1968",
            "1959",
            "1961",
            "1969",
            3
        )
        questionList.add(question3)

        val question4 = Question(
            3,
            "Who is the developer of Kotlin??",
            "Google",
            "Intellij IDEA",
            "JetBrains",
            "Eclipse", //ничего
            "Oracle",
            2
        )
        questionList.add(question4)

        val question5 = Question(
            4,
            "Who wrote the book The Little Prince?",
            "Daniel Defoe",
            "Oscar Wilde ",
            "Lewis Carroll", //1
            "William Shakespeare",
            "Antoine de Saint-Exupéry",
            4 //дефо
        )
        questionList.add(question5)

        return questionList
    }
}