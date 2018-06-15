package pl.depta.rafal.myquiz.data.local.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

/*
class FullQuiz(
        @Embedded
        var quizEntity: QuizEntity = QuizEntity(),
        @Relation(parentColumn = "id", entityColumn = "quizId", entity = QuestionAndAnswers::class)
        var questionAndAnswers: List<QuestionAndAnswers> = listOf(),
        @Relation(parentColumn = "id", entityColumn = "quizId", entity = RateEntity::class)
        var rate: List<RateEntity> = listOf()
)*/
