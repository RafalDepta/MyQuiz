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
data class TagEntity(
        @PrimaryKey(autoGenerate = true) var id: Long,
        val quizId: Long,
        val name: String,
        val type: String
)