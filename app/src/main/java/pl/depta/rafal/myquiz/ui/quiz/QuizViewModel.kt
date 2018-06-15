package pl.depta.rafal.myquiz.ui.quiz

import android.app.Application
import android.arch.lifecycle.LiveData
import android.databinding.ObservableField
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.depta.rafal.myquiz.SingleLiveEvent
import pl.depta.rafal.myquiz.data.DataManager
import pl.depta.rafal.myquiz.data.local.entity.AnswerEntity
import pl.depta.rafal.myquiz.data.local.entity.QuestionEntity
import pl.depta.rafal.myquiz.data.local.entity.QuizEntity
import pl.depta.rafal.myquiz.data.local.entity.RateEntity
import pl.depta.rafal.myquiz.data.model.QuizDetails
import pl.depta.rafal.myquiz.ui.base.BaseViewModel
import javax.inject.Inject

class QuizViewModel
@Inject constructor(dataManager: DataManager, application: Application) : BaseViewModel(dataManager, application) {
    var initQuestionFragmentEvent: SingleLiveEvent<String> = SingleLiveEvent()
    var initResultFragmentEvent: SingleLiveEvent<String> = SingleLiveEvent()
    var errorEvent: SingleLiveEvent<Any> = SingleLiveEvent()
    var quiz = ObservableField<QuizEntity>()
    var showTopBar = ObservableField<Boolean>(true)
    var quizLiveData: LiveData<QuizEntity>? = null
    var quizId: Long = 0
    var currentQuestionOrder: Int = 1
    var questionsCount: Int = 0

    fun initData(quizId: Long) {
        this.quizId = quizId
        quizLiveData = dataManager.getQuizByIdLiveData(quizId)

        compositeDisposable.add(
                dataManager.getQuestion(quizId.toString())
                        .doOnComplete {
                            dataManager.getQuizDetails(quizId.toString())
                                    .flatMap {
                                        Flowable.concat(insertRates(it, quizId), insertQuestions(it, quizId))
                                    }
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(
                                            {},
                                            { errorEvent.call() })
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    getQuestionsCount(quizId.toString())
                                },
                                { },
                                {
                                    getQuestionsCount(quizId.toString())
                                }
                        )
        )

        updateProgress()
    }

    private fun insertRates(quizDetail: QuizDetails, quizId: Long): Flowable<Long> {
        return Flowable.fromIterable(quizDetail.rates)
                .flatMap {
                    dataManager.insertRate(RateEntity(it.from, it.to, it.content, quizId))
                }
    }

    private fun insertQuestions(quizDetail: QuizDetails, quizId: Long): Flowable<Long> {
        return Flowable.fromIterable(quizDetail.questions)
                .flatMap { question ->
                    dataManager.insertQuestion(QuestionEntity(0, question.image.url, question.text, question.order, 0, quizId))
                            .flatMap { questionId ->
                                Flowable.fromIterable(question.answers)
                                        .flatMap {
                                            dataManager.insertAnswer(AnswerEntity(0, questionId, it.image.url, it.order, it.text, it.isCorrect))
                                        }
                            }
                }
    }

    private fun getQuestionsCount(quizId: String) {
        compositeDisposable.add(
                dataManager.getQuestionsCount(quizId)
                        .flatMap {
                            questionsCount = it
                            dataManager.setQuestionCount(it, quizId)
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({},
                                {},
                                { initQuestionFragmentEvent.value = quizId })
        )
    }

    fun checkQuestion(questionOrder: Int) {
        when {
            questionOrder < questionsCount -> {
                currentQuestionOrder++
                initQuestionFragmentEvent.value = quizId.toString()
                showTopBar.set(true)
            }
            questionOrder == questionsCount -> {
                initResultFragmentEvent.value = quizId.toString()
                showTopBar.set(false)
            }
        }

        updateProgress()
    }

    private fun updateProgress() {
        compositeDisposable.add(
                dataManager.updateQuizProgress(quizId, currentQuestionOrder)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
        )
    }

    fun reset() {
        showTopBar.set(true)
        currentQuestionOrder = 1
        updateProgress()
    }
}