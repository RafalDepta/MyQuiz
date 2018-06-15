package pl.depta.rafal.myquiz.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import pl.depta.rafal.myquiz.data.local.entity.QuestionAndAnswers
import pl.depta.rafal.myquiz.data.local.entity.QuestionEntity

@Dao
interface QuestionDao {

    @Insert(onConflict = REPLACE)
    fun insertQuestion(questionEntity: QuestionEntity): Long

    @Query("SELECT * FROM QuestionEntity WHERE quizId=:quizId AND `order`=:questionOrder")
    fun getQuestionWithAnswers(quizId: String, questionOrder: Int): LiveData<QuestionAndAnswers>

    @Query("SELECT * FROM QuestionEntity WHERE quizId=:quizId")
    fun questionExist(quizId: String): QuestionAndAnswers

    @Query("UPDATE QuestionEntity SET rightAnswerId =:answerId WHERE id=:questionId")
    fun makeAnswer(answerId: Long?, questionId: Long?): Int

    @Query("SELECT `order` FROM QuestionEntity WHERE id=:questionId")
    fun getOrderFromId(questionId: Long?): Int

    @Query("SELECT * FROM QuestionEntity WHERE quizId=:quizId")
    fun getQuestionsWithAnswers(quizId: String): List<QuestionAndAnswers>

    @Query("SELECT COUNT(`id`) FROM QuestionEntity WHERE quizId=:quizId")
    fun getQuestionsCount(quizId: String): Int

    @Query("SELECT COUNT(`id`) FROM QuestionEntity WHERE quizId=:quizId ")
    fun getQuizResult(quizId: String): Int

    @Query("SELECT * FROM QuestionEntity WHERE quizId=:quizId ")
    fun getQuizQuestions(quizId: String): List<QuestionEntity>

    @Query("UPDATE QuestionEntity SET rightAnswerId =0 WHERE id=:questionId")
    fun clearAnswer(questionId: Long): Int
}