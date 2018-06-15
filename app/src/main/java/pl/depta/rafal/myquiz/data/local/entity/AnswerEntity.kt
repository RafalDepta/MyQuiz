package pl.depta.rafal.myquiz.data.local.entity

import android.arch.persistence.room.*
import pl.depta.rafal.myquiz.data.model.Image

@Entity(foreignKeys = [
    ForeignKey(
            entity = QuestionEntity::class,
            parentColumns = ["id"],
            childColumns = ["questionId"],
            onDelete = ForeignKey.CASCADE
    )
],
        indices = [
            Index("questionId")
        ])
data class AnswerEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0,
        var questionId: Long = 0,
        var imageUrl: String = "",
        var order: Int = 0,
        var text: String = "",
        var correct: Int = 0
) {

    constructor() : this(0, 0, "", 0, "", 0)
}