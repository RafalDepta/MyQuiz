package pl.depta.rafal.myquiz.data.local.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity
open class QuizEntity {
    @PrimaryKey
    var id: Long = 0
    var title: String = ""
    var type: String = ""
    var content: String = ""
    var imageUrl: String = ""
    var createdAt: String = ""
    var lastResult: Int = 0
    var questionCount: Int = 0
    var progress: Int = 0
    var isSolved: Boolean = false

    @Ignore
    constructor(id: Long, title: String, type: String, content: String, imageUrl: String, createdAt: String, questionCount:Int) {
        this.id = id
        this.title = title
        this.type = type
        this.content = content
        this.imageUrl = imageUrl
        this.createdAt = createdAt
        this.questionCount = questionCount
    }

    constructor()
}