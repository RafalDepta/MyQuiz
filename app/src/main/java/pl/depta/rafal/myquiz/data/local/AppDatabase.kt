package pl.depta.rafal.myquiz.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import pl.depta.rafal.myquiz.data.local.dao.AnswerDao
import pl.depta.rafal.myquiz.data.local.dao.QuestionDao
import pl.depta.rafal.myquiz.data.local.dao.QuizDao
import pl.depta.rafal.myquiz.data.local.dao.RateDao
import pl.depta.rafal.myquiz.data.local.entity.AnswerEntity
import pl.depta.rafal.myquiz.data.local.entity.QuestionEntity
import pl.depta.rafal.myquiz.data.local.entity.QuizEntity
import pl.depta.rafal.myquiz.data.local.entity.RateEntity

@Database(entities =
[AnswerEntity::class, QuestionEntity::class, QuizEntity::class, RateEntity::class],
        version = 1,
        exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun quizDao(): QuizDao

    abstract fun questionDao(): QuestionDao

    abstract fun answerDao(): AnswerDao

    abstract fun rateDao(): RateDao
}