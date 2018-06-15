package pl.depta.rafal.myquiz.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import pl.depta.rafal.myquiz.data.local.entity.QuizEntity

@Dao
interface QuizDao {

    @Insert(onConflict = REPLACE)
    fun insertQuiz(quizEntity: QuizEntity): Long

    @Insert(onConflict = REPLACE)
    fun insertAll(quizzes: List<QuizEntity>)

    @Query("SELECT * FROM QuizEntity")
    fun getQuizList(): LiveData<List<QuizEntity>>

    @Query("SELECT COUNT(id) FROM QuizEntity ")
    fun isAnyQuiz(): Int

    @Query("SELECT * FROM QuizEntity WHERE id=:quizId")
    fun getFullQuiz(quizId: String): QuizEntity

    @Query("SELECT * FROM QuizEntity WHERE id=:quizId")
    fun getQuizById(quizId: Long): LiveData<QuizEntity>

    @Query("UPDATE QuizEntity SET questionCount=:questionCount WHERE id=:quizId")
    fun setQuestionCount(questionCount: Int, quizId: String): Int

    @Query("UPDATE QuizEntity SET progress=:progress WHERE id=:quizId")
    fun updateProgress(quizId: Long, progress: Int): Int
}