package pl.depta.rafal.myquiz.data.local.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(foreignKeys = [
    (ForeignKey(
            entity = QuizEntity::class,
            parentColumns = ["id"],
            childColumns = ["quizId"],
            onDelete = ForeignKey.CASCADE
    ))
],
        indices = [
            (Index("quizId"))
        ])
open class RateEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var from: Int = 0
    var to: Int = 0
    var content: String = ""
    var quizId: Long = 0

    constructor(from: Int, to: Int, content: String, quizId: Long) {
        this.from = from
        this.to = to
        this.content = content
        this.quizId = quizId
    }
}