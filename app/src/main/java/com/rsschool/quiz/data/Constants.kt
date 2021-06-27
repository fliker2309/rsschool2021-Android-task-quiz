package com.rsschool.quiz.data

import com.rsschool.quiz.data.model.Question

object Constants {

    fun getQuestions(): ArrayList<Question> {
        val questionList = ArrayList<Question>()
        val question1 = Question(
            "What is the capital of Great Britain?",
            "Madrid",
            "London",
            "Paris",
            "Istanbul",
            "Manchester",
            "London"
        )
        questionList.add(question1)

        val question2 = Question(
            "Who won the 2018 FIFA World Cup?",
            "France",
            "Croatia",
            "Russia",
            "Belgium",
            "England",
            "France"
        )
        questionList.add(question2)

        val question3 = Question(
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
            "Who is the developer of Kotlin??",
            "Google",
            "Intellij IDEA",
            "JetBrains",
            "Eclipse",
            "Oracle",
            "JetBrains"
        )
        questionList.add(question4)

        val question5 = Question(
            "Who wrote the book The Little Prince?",
            "Daniel Defoe",
            "Oscar Wilde ",
            "Lewis Carroll",
            "William Shakespeare",
            "Antoine de Saint-Exupéry",
            "Antoine de Saint-Exupéry"
        )
        questionList.add(question5)

        return questionList
    }
}