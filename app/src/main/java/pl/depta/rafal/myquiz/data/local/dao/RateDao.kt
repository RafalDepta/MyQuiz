package pl.depta.rafal.myquiz.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import pl.depta.rafal.myquiz.data.local.entity.QuizEntity
import pl.depta.rafal.myquiz.data.local.entity.RateEntity

@Dao
interface RateDao {

    @Insert(onConflict = REPLACE)
    fun insertRate(rateEntity: RateEntity): Long

    @Query("SELECT content FROM RateEntity WHERE (`from`<=:score AND `to`>=:score) AND quizId=:quizId")
    fun getRateText(quizId: String, score: Int): String
}