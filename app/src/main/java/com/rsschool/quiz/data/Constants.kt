package com.rsschool.quiz.data

import com.rsschool.quiz.data.model.Question

object Constants {

    fun getQuestions(): ArrayList<Question> {

        val questionList = ArrayList<Question>()
        val question1 = Question(
            0,
            "What is the capital of Great Britain?",
            " Madrid", //лондон
            "London",
            "Paris", //1
            "Istanbul",
            "Manchester",
            "London",
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
            "France"
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
            "1961"
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
            "JetBrains"
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
            "Antoine de Saint-Exupéry" //дефо
            )
        questionList.add(question5)

        return questionList
    }
}