package pl.depta.rafal.myquiz.data

import android.arch.lifecycle.LiveData
import io.reactivex.Flowable
import io.reactivex.Maybe
import pl.depta.rafal.myquiz.data.local.DbHelper
import pl.depta.rafal.myquiz.data.local.entity.*
import pl.depta.rafal.myquiz.data.model.QuizDetails
import pl.depta.rafal.myquiz.data.model.Quizzes
import pl.depta.rafal.myquiz.data.remote.ApiInterface
import javax.inject.Inject


class AppDataManager
@Inject constructor(private val apiInterface: ApiInterface, private val dbHelper: DbHelper) : DataManager {
    override fun updateQuizProgress(quizId: Long, progress: Int): Flowable<Int>{
        return dbHelper.updateQuizProgress(quizId,progress)
    }

    override fun clearAnswer(questionId: Long): Flowable<Int> {
        return dbHelper.clearAnswer(questionId)
    }

    override fun getRatesText(quizId: String, score: Int): Flowable<String> {
        return dbHelper.getRatesText(quizId, score)
    }

    override fun isAnswerCorrect(rightAnswerId: Long): Flowable<Boolean> {
        return dbHelper.isAnswerCorrect(rightAnswerId)
    }

    override fun getQuizQuestions(quizId: String): Flowable<List<QuestionEntity>> {
        return dbHelper.getQuizQuestions(quizId)
    }

    override fun getQuizResult(quizId: String): Flowable<Int> {
        return dbHelper.getQuizResult(quizId)
    }

    override fun setQuestionCount(questionCount: Int, quizId: String): Flowable<Int> {
        return dbHelper.setQuestionCount(questionCount, quizId)
    }

    override fun getQuestionsCount(quizId: String): Flowable<Int> {
        return dbHelper.getQuestionsCount(quizId)
    }

    override fun getQuestionsWithAnswers(quizId: String): Flowable<List<QuestionAndAnswers>> {
        return dbHelper.getQuestionsWithAnswers(quizId)
    }

    override fun getQuestionOrder(questionId: Long?): Flowable<Int> {
        return dbHelper.getQuestionOrder(questionId)
    }

    override fun makeAnswer(answerId: Long?, questionId: Long?): Flowable<Int> {
        return dbHelper.makeAnswer(answerId, questionId)
    }

    override fun getQuestionAndAnswers(quizId: String, questionOrder: Int): LiveData<QuestionAndAnswers> {
        return dbHelper.getQuestionAndAnswers(quizId, questionOrder)
    }

    override fun getQuestion(quizId: String): Maybe<QuestionAndAnswers> {
        return dbHelper.getQuestion(quizId)
    }

    override fun getQuizByIdLiveData(quizId: Long): LiveData<QuizEntity> {
        return dbHelper.getQuizByIdLiveData(quizId)
    }

    override fun getFullQuiz(quizId: String): Flowable<QuizEntity> {
        return dbHelper.getFullQuiz(quizId)
    }

    override fun insertQuestion(questionEntity: QuestionEntity): Flowable<Long> {
        return dbHelper.insertQuestion(questionEntity)
    }

    override fun insertAnswer(answerEntity: AnswerEntity): Flowable<Long> {
        return dbHelper.insertAnswer(answerEntity)
    }

    override fun insertRate(rateEntity: RateEntity): Flowable<Long> {
        return dbHelper.insertRate(rateEntity)
    }

    override fun insertQuiz(quiz: QuizEntity): Flowable<Long> {
        return dbHelper.insertQuiz(quiz)
    }

    override fun isAnyQuiz(): Flowable<Boolean> {
        return dbHelper.isAnyQuiz()
    }

    override fun getQuizList(): LiveData<List<QuizEntity>> {
        return dbHelper.getQuizList()
    }

    override fun insertQuizzes(quizzes: List<QuizEntity>): Flowable<Any> {
        return dbHelper.insertQuizzes(quizzes)
    }


    override fun getQuizDetails(quizId: String): Flowable<QuizDetails> {

        return apiInterface.getQuizDetails(quizId)
    }

    override fun getQuizzes(): Flowable<Quizzes> {
        return apiInterface.getQuizList()
    }


}