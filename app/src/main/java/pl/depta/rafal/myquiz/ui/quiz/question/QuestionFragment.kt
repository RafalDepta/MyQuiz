package pl.depta.rafal.myquiz.ui.quiz.question

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutCompat
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import pl.depta.rafal.myquiz.BR
import pl.depta.rafal.myquiz.R
import pl.depta.rafal.myquiz.data.local.entity.AnswerEntity
import pl.depta.rafal.myquiz.databinding.FragmentQuestionBinding
import pl.depta.rafal.myquiz.ui.base.BaseFragment
import pl.depta.rafal.myquiz.utils.AppUtils
import java.lang.ref.WeakReference
import javax.inject.Inject

class QuestionFragment : BaseFragment<FragmentQuestionBinding, QuestionViewModel>() {

    @Inject
    lateinit var mViewModel: QuestionViewModel
    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mBinding: FragmentQuestionBinding? = null

    override val layoutId: Int = R.layout.fragment_question


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding
        setUp()
        subscribeLiveData()
    }


    private fun setUp() {
        arguments?.let {
            mViewModel.initData(it.getString(QUIZ_ID), it.getInt(QUESTION_ORDER))
        }

        mBinding?.questionAnswerGroup?.setOnCheckedChangeListener { group, checkedId ->
            run {
                mViewModel.makeAnswer(checkedId)
            }
        }
    }

    private fun subscribeLiveData() {
        mViewModel.questionAndAnswers?.observe(this, Observer {
            it?.let {
                mViewModel.question.set(it.questionEntity)
                if (mViewModel.radioButtonMap.isEmpty())
                    generateAnswersView(it.answers)
            }
        })
    }

    private fun generateAnswersView(answers: List<AnswerEntity>) {
        answers.sortedBy { it.order }
                .forEach {
                    val radioButton = RadioButton(context)
                    radioButton.id = AppUtils.generateViewId()
                    radioButton.text = it.text
                    mViewModel.radioButtonMap[radioButton.id] = it.id
                    mBinding?.questionAnswerGroup?.addView(radioButton)

                    val layoutParams: LinearLayout.LayoutParams
                    layoutParams = radioButton.layoutParams as LinearLayout.LayoutParams
                    layoutParams.setMargins(0, 20, 0, 0)
                    layoutParams.width = LinearLayoutCompat.LayoutParams.MATCH_PARENT
                    radioButton.layoutParams = layoutParams
                }
    }


    companion object {
        private const val QUIZ_ID = "quiz_id"
        private const val QUESTION_ORDER = "question_order"

        fun newInstance(quizId: String, questionOrder: Int): QuestionFragment =
                QuestionFragment().apply {
                    arguments = Bundle().apply {
                        putString(QUIZ_ID, quizId)
                        putInt(QUESTION_ORDER, questionOrder)
                    }
                }

    }

    override fun getViewModel(): QuestionViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(QuestionViewModel::class.java)
        return mViewModel
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

}
