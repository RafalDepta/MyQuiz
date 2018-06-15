package pl.depta.rafal.myquiz.data.local.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation


data class QuestionAndAnswers(
        @Embedded
        var questionEntity: QuestionEntity = QuestionEntity(),
        @Relation(parentColumn = "id", entityColumn = "questionId", entity = AnswerEntity::class)
        var answers: List<AnswerEntity> = listOf()
)
