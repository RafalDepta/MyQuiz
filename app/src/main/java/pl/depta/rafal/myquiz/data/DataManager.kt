package pl.depta.rafal.myquiz.data

import android.arch.lifecycle.LiveData
import io.reactivex.Flowable
import pl.depta.rafal.myquiz.data.local.DbHelper
import pl.depta.rafal.myquiz.data.local.entity.QuizEntity
import pl.depta.rafal.myquiz.data.model.Quiz
import pl.depta.rafal.myquiz.data.model.QuizDetails
import pl.depta.rafal.myquiz.data.model.Quizzes
import pl.depta.rafal.myquiz.data.remote.ApiInterface

interface DataManager : DbHelper {

    fun getQuizzes(): Flowable<Quizzes>
    fun getQuizDetails(quizId: String): Flowable<QuizDetails>
}