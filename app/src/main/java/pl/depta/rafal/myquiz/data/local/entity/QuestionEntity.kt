package pl.depta.rafal.myquiz.data.local.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(
            entity = QuizEntity::class,
            parentColumns = ["id"],
            childColumns = ["quizId"],
            onDelete = ForeignKey.CASCADE
    )
],
        indices = [
            Index("quizId")
        ])
data class QuestionEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0,
        var imageUrl: String = "",
        var text: String = "",
        var order: Int = 0,
        var rightAnswerId: Long = 0,
        var quizId: Long = 0
) {

    constructor() : this(0, "", "", 0, 0, 0)
}