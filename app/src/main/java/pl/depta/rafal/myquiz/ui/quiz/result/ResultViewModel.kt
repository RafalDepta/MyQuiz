package pl.depta.rafal.myquiz.ui.quiz.result

import android.app.Application
import android.databinding.ObservableField
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import pl.depta.rafal.myquiz.data.DataManager
import pl.depta.rafal.myquiz.events.FinishEvent
import pl.depta.rafal.myquiz.events.OpenQuizEvent
import pl.depta.rafal.myquiz.ui.base.BaseViewModel
import javax.inject.Inject

class ResultViewModel
@Inject constructor(dataManager: DataManager, application: Application) : BaseViewModel(dataManager, application) {

    var result = ObservableField<Int>()
    var resultText = ObservableField<String>()
    var rightAnswers = 0
    var questionsCount = 0
    var quizId = ""

    fun initData(quizId: String, questionsCount: Int) {
        this.questionsCount = questionsCount
        this.quizId = quizId
        this.rightAnswers = 0

        compositeDisposable.add(
                dataManager.getQuizQuestions(quizId)
                        .flatMapIterable { it }
                        .flatMap {
                            dataManager.isAnswerCorrect(it.rightAnswerId)
                        }.map {
                            if (it) {
                                rightAnswers++
                            }
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({},
                                {},
                                { calculateScore() })
        )


    }

    private fun calculateScore() {
        val amount = rightAnswers.toFloat() / questionsCount.toFloat()
        val score = (amount * 100).toInt()

        result.set(score)
        getRateText(score)
    }

    private fun getRateText(score: Int) {
        compositeDisposable.add(
                dataManager.getRatesText(quizId, score)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    resultText.set(it)
                                },
                                { Log.d("ERROR", "Error") },
                                {})
        )
    }

    fun solveQuizAgain() {
        compositeDisposable.add(
                dataManager.getQuizQuestions(quizId)
                        .flatMapIterable { it }
                        .flatMap {
                            dataManager.clearAnswer(it.id)
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({},
                                {},
                                {  EventBus.getDefault().postSticky(OpenQuizEvent(quizId)) })
        )
    }

    fun backToQuizList() {
        EventBus.getDefault().postSticky(FinishEvent())
    }
}