package pl.depta.rafal.myquiz.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import pl.depta.rafal.myquiz.data.local.entity.AnswerEntity

@Dao
interface AnswerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnswer(answerEntity: AnswerEntity): Long

    @Query("SELECT correct FROM AnswerEntity WHERE id=:rightAnswerId")
    fun getAnswerById(rightAnswerId: Long): Int
}