package pl.depta.rafal.myquiz.ui.quizzes

import android.app.Application
import android.arch.lifecycle.LiveData
import android.util.Log
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.depta.rafal.myquiz.data.DataManager
import pl.depta.rafal.myquiz.data.local.entity.AnswerEntity
import pl.depta.rafal.myquiz.data.local.entity.QuestionEntity
import pl.depta.rafal.myquiz.data.local.entity.QuizEntity
import pl.depta.rafal.myquiz.data.local.entity.RateEntity
import pl.depta.rafal.myquiz.data.model.QuizDetails
import pl.depta.rafal.myquiz.ui.base.BaseViewModel
import javax.inject.Inject

class QuizzesViewModel
@Inject constructor(dataManager: DataManager, application: Application) : BaseViewModel(dataManager, application) {

    var mQuizList: LiveData<List<QuizEntity>> = dataManager.getQuizList()

    init {
        compositeDisposable.add(dataManager.isAnyQuiz()
                .flatMap {
                    if (it)
                        dataManager.getQuizzes()
                    else
                        Flowable.empty()
                }
                .flatMap {
                    Flowable.fromIterable(it.quizzes)
                            .map {
                                QuizEntity(it.id, it.title, it.type, it.content, it.mainPhoto.url, it.createdAt, it.questions)
                            }
                            .toList()
                            .toFlowable()
                }
                .flatMap { dataManager.insertQuizzes(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Log.d("QUIZ: ", it.toString())
                        },
                        { Log.d("VM", it.message) }))
    }


}