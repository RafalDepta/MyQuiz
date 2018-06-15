package pl.depta.rafal.myquiz.ui.quiz.question

import android.app.Application
import android.arch.lifecycle.LiveData
import android.databinding.ObservableField
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import pl.depta.rafal.myquiz.data.DataManager
import pl.depta.rafal.myquiz.data.local.entity.QuestionAndAnswers
import pl.depta.rafal.myquiz.data.local.entity.QuestionEntity
import pl.depta.rafal.myquiz.events.QuestionAnsweredEvent
import pl.depta.rafal.myquiz.ui.base.BaseViewModel
import javax.inject.Inject

class QuestionViewModel
@Inject constructor(dataManager: DataManager, application: Application) : BaseViewModel(dataManager, application) {

    var questionAndAnswers: LiveData<QuestionAndAnswers>? = null
    var question = ObservableField<QuestionEntity>()
    var radioButtonMap: MutableMap<Int, Long> = hashMapOf()
    var questionOrder: Int = 0

    fun initData(quizId: String?, questionOrder: Int) {
        this.questionOrder = questionOrder
        quizId?.let {
            questionAndAnswers = dataManager.getQuestionAndAnswers(quizId, questionOrder)
        }
    }

    fun makeAnswer(checkedId: Int) {
        compositeDisposable.add(dataManager.makeAnswer(radioButtonMap[checkedId], question.get()?.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ },
                        {},
                        { EventBus.getDefault().postSticky(QuestionAnsweredEvent(questionOrder)) })
        )
    }

}
