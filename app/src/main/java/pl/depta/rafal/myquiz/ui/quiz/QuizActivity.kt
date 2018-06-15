package pl.depta.rafal.myquiz.ui.quiz

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AlertDialog
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import pl.depta.rafal.myquiz.BR
import pl.depta.rafal.myquiz.R
import pl.depta.rafal.myquiz.databinding.ActivityQuizBinding
import pl.depta.rafal.myquiz.events.FinishEvent
import pl.depta.rafal.myquiz.events.OpenQuizEvent
import pl.depta.rafal.myquiz.events.QuestionAnsweredEvent
import pl.depta.rafal.myquiz.ui.base.BaseActivity
import pl.depta.rafal.myquiz.ui.quiz.question.QuestionFragment
import pl.depta.rafal.myquiz.ui.quiz.result.ResultFragment
import pl.depta.rafal.myquiz.ui.quizzes.QuizzesActivity.Companion.QUIZ_ID
import javax.inject.Inject


class QuizActivity : BaseActivity<ActivityQuizBinding, QuizViewModel>() {

    @Inject
    lateinit var mViewModel: QuizViewModel
    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mFragmentTransaction: FragmentTransaction? = null

    override val layoutId = R.layout.activity_quiz
    private var mBinding: ActivityQuizBinding? = null
    private var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = viewDataBinding

        mViewModel.initData(intent.extras.getLong(QUIZ_ID))
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        mViewModel.quizLiveData?.observe(this, Observer { t ->
            t?.let {
                mBinding?.quizProgressBar?.max = it.questionCount + 1
                mViewModel.quiz.set(it)
            }
        })

        mViewModel.initQuestionFragmentEvent.observe(this, Observer {
            it?.let {
                initQuestionFragment(it, mViewModel.currentQuestionOrder)
            }
        })

        mViewModel.initResultFragmentEvent.observe(this, Observer {
            it?.let {
                initResultFragment(it)
            }
        })

        mViewModel.errorEvent.observe(this, Observer {
            showErrorDialog()
        })
    }

    private fun showErrorDialog() {
        alertDialog = AlertDialog.Builder(this)
                .setTitle(R.string.error)
                .setMessage(R.string.loadning_error)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok) { dialog, which ->
                    dialog.dismiss()
                    finish()
                }.show()
    }

    private fun initResultFragment(quizId: String) {
        mFragmentTransaction = supportFragmentManager.beginTransaction()
        mFragmentTransaction?.replace(R.id.frame_container, ResultFragment.newInstance(quizId, mViewModel.questionsCount))
        mFragmentTransaction?.commit()
    }

    private fun initQuestionFragment(quizId: String, currentQuestionOrder: Int) {
        mFragmentTransaction = supportFragmentManager.beginTransaction()
        mFragmentTransaction?.replace(R.id.frame_container, QuestionFragment.newInstance(quizId, currentQuestionOrder))
        mFragmentTransaction?.commit()
    }

    @Subscribe(sticky = true)
    fun onEvent(questionAnsweredEvent: QuestionAnsweredEvent) {
        mViewModel.checkQuestion(questionAnsweredEvent.questionOrder)
        EventBus.getDefault().removeStickyEvent(questionAnsweredEvent)
    }

    @Subscribe(sticky = true)
    fun onEvent(finishEvent: FinishEvent) {
        EventBus.getDefault().removeStickyEvent(finishEvent)
        finish()
    }

    @Subscribe(sticky = true)
    fun onEvent(openQuizEvent: OpenQuizEvent) {
        initQuestionFragment(openQuizEvent.quizId, 1)
        mViewModel.reset()
        EventBus.getDefault().removeStickyEvent(openQuizEvent)
    }

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onStop() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }

        alertDialog?.dismiss()
        super.onStop()
    }


    override fun getViewModel(): QuizViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(QuizViewModel::class.java)
        return mViewModel
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }
}
