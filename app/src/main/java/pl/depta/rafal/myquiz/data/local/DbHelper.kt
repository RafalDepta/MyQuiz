package pl.depta.rafal.myquiz.data.local

import android.arch.lifecycle.LiveData
import io.reactivex.Flowable
import io.reactivex.Maybe
import pl.depta.rafal.myquiz.data.local.entity.*

interface DbHelper {

    fun insertQuizzes(quizzes: List<QuizEntity>): Flowable<Any>
    fun insertQuiz(quiz: QuizEntity): Flowable<Long>
    fun getQuizList(): LiveData<List<QuizEntity>>
    fun isAnyQuiz(): Flowable<Boolean>
    fun insertQuestion(questionEntity: QuestionEntity): Flowable<Long>
    fun insertAnswer(answerEntity: AnswerEntity): Flowable<Long>
    fun insertRate(rateEntity: RateEntity): Flowable<Long>
    fun getFullQuiz(quizId: String): Flowable<QuizEntity>
    fun getQuizByIdLiveData(quizId: Long): LiveData<QuizEntity>
    fun getQuestion(quizId: String): Maybe<QuestionAndAnswers>
    fun getQuestionAndAnswers(quizId: String, questionOrder: Int): LiveData<QuestionAndAnswers>
    fun makeAnswer(answerId: Long?, questionId: Long?): Flowable<Int>
    fun getQuestionOrder(questionId: Long?): Flowable<Int>
    fun getQuestionsWithAnswers(quizId: String): Flowable<List<QuestionAndAnswers>>
    fun getQuestionsCount(quizId: String): Flowable<Int>
    fun setQuestionCount(questionCount: Int, quizId: String): Flowable<Int>
    fun getQuizResult(quizId: String): Flowable<Int>
    fun getQuizQuestions(quizId: String): Flowable<List<QuestionEntity>>
    fun isAnswerCorrect(rightAnswerId: Long): Flowable<Boolean>
    fun getRatesText(quizId: String, score: Int): Flowable<String>
    fun clearAnswer(questionId: Long): Flowable<Int>
    fun updateQuizProgress(quizId: Long, progress: Int): Flowable<Int>
}