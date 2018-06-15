package pl.depta.rafal.myquiz.ui.quiz.result

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import pl.depta.rafal.myquiz.BR

import pl.depta.rafal.myquiz.R
import pl.depta.rafal.myquiz.databinding.FragmentResultBinding
import pl.depta.rafal.myquiz.ui.base.BaseFragment
import javax.inject.Inject

class ResultFragment : BaseFragment<FragmentResultBinding, ResultViewModel>() {

    @Inject
    lateinit var mViewModel: ResultViewModel
    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mBinding: FragmentResultBinding? = null

    override val layoutId: Int = R.layout.fragment_result

    private var quizId: String? = null
    private var questionsCount: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            quizId = it.getString(QUIZ_ID)
            questionsCount = it.getInt(QUESTIONS_COUNT)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding
        setUp()
        subscribeLiveData()
    }

    private fun setUp() {
        arguments?.let {
            mViewModel.initData(it.getString(QUIZ_ID), it.getInt(QUESTIONS_COUNT))
        }
    }

    private fun subscribeLiveData() {

    }

    override fun getViewModel(): ResultViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ResultViewModel::class.java)
        return mViewModel
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    companion object {
        private const val QUIZ_ID = "quiz_id"
        private const val QUESTIONS_COUNT = "questions_count"

        fun newInstance(quizId: String, questionsCount: Int) =
                ResultFragment().apply {
                    arguments = Bundle().apply {
                        putString(QUIZ_ID, quizId)
                        putInt(QUESTIONS_COUNT, questionsCount)
                    }
                }
    }
}
