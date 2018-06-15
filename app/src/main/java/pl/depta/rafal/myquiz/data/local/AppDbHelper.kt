package pl.depta.rafal.myquiz.data.local

import android.arch.lifecycle.LiveData
import io.reactivex.Flowable
import io.reactivex.Maybe
import pl.depta.rafal.myquiz.data.local.entity.*
import javax.inject.Inject

class AppDbHelper @Inject constructor(private val appDatabase: AppDatabase) : DbHelper {
    override fun updateQuizProgress(quizId: Long, progress: Int): Flowable<Int> {
        return Flowable.fromCallable { appDatabase.quizDao().updateProgress(quizId, progress) }
    }

    override fun clearAnswer(questionId: Long): Flowable<Int> {
        return Flowable.fromCallable{appDatabase.questionDao().clearAnswer(questionId)}
    }

    override fun getRatesText(quizId: String, score: Int): Flowable<String> {
        return Flowable.fromCallable{appDatabase.rateDao().getRateText(quizId,score)}
    }

    override fun isAnswerCorrect(rightAnswerId: Long): Flowable<Boolean> {
        return Flowable.fromCallable{appDatabase.answerDao().getAnswerById(rightAnswerId) == 1}
    }

    override fun getQuizQuestions(quizId: String): Flowable<List<QuestionEntity>> {
        return Flowable.fromCallable{appDatabase.questionDao().getQuizQuestions(quizId)}
    }

    override fun getQuizResult(quizId: String): Flowable<Int> {
        return Flowable.fromCallable{appDatabase.questionDao().getQuizResult(quizId)}
    }

    override fun setQuestionCount(questionCount: Int, quizId: String): Flowable<Int> {
        return Flowable.fromCallable{appDatabase.quizDao().setQuestionCount(questionCount, quizId)}
    }

    override fun getQuestionsCount(quizId: String): Flowable<Int> {
        return Flowable.fromCallable { appDatabase.questionDao().getQuestionsCount(quizId) }
    }

    override fun getQuestionsWithAnswers(quizId: String): Flowable<List<QuestionAndAnswers>> {
        return Flowable.fromCallable { appDatabase.questionDao().getQuestionsWithAnswers(quizId) }
    }

    override fun getQuestionOrder(questionId: Long?): Flowable<Int> {
        return Flowable.fromCallable { appDatabase.questionDao().getOrderFromId(questionId) }
    }

    override fun makeAnswer(answerId: Long?, questionId: Long?): Flowable<Int> {
        return Flowable.fromCallable { appDatabase.questionDao().makeAnswer(answerId, questionId) }
    }

    override fun getQuestionAndAnswers(quizId: String, questionOrder: Int): LiveData<QuestionAndAnswers> {
        return appDatabase.questionDao().getQuestionWithAnswers(quizId, questionOrder)
    }

    override fun getQuestion(quizId: String): Maybe<QuestionAndAnswers> {
        return Maybe.fromCallable { appDatabase.questionDao().questionExist(quizId) }
    }

    override fun getQuizByIdLiveData(quizId: Long): LiveData<QuizEntity> {
        return appDatabase.quizDao().getQuizById(quizId)
    }

    override fun getFullQuiz(quizId: String): Flowable<QuizEntity> {
        return Flowable.fromCallable { appDatabase.quizDao().getFullQuiz(quizId) }
    }

    override fun insertRate(rateEntity: RateEntity): Flowable<Long> {
        return Flowable.fromCallable { appDatabase.rateDao().insertRate(rateEntity) }
    }

    override fun insertAnswer(answerEntity: AnswerEntity): Flowable<Long> {
        return Flowable.fromCallable { appDatabase.answerDao().insertAnswer(answerEntity) }
    }

    override fun insertQuestion(questionEntity: QuestionEntity): Flowable<Long> {
        return Flowable.fromCallable { appDatabase.questionDao().insertQuestion(questionEntity) }
    }

    override fun insertQuiz(quiz: QuizEntity): Flowable<Long> {
        return Flowable.fromCallable { appDatabase.quizDao().insertQuiz(quiz) }
    }

    override fun isAnyQuiz(): Flowable<Boolean> {
        return Flowable.fromCallable { appDatabase.quizDao().isAnyQuiz() == 0 }
    }

    override fun getQuizList(): LiveData<List<QuizEntity>> {
        return appDatabase.quizDao().getQuizList()
    }

    override fun insertQuizzes(quizzes: List<QuizEntity>): Flowable<Any> {
        return Flowable.fromCallable { appDatabase.quizDao().insertAll(quizzes) }
    }


}