package pl.depta.rafal.myquiz.data.remote

import io.reactivex.Flowable
import pl.depta.rafal.myquiz.data.model.Quiz
import pl.depta.rafal.myquiz.data.model.QuizDetails
import pl.depta.rafal.myquiz.data.model.Quizzes
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @GET("quizzes/0/100")
    fun getQuizList(): Flowable<Quizzes>

    @GET("quiz/{id}/0")
    fun getQuizDetails(@Path("id") id: String): Flowable<QuizDetails>
}