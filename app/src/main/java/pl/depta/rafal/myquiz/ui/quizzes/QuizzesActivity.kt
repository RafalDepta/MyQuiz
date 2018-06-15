package pl.depta.rafal.myquiz.ui.quizzes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import com.amitshekhar.DebugDB
import pl.depta.rafal.myquiz.BR
import pl.depta.rafal.myquiz.R
import pl.depta.rafal.myquiz.databinding.ActivityQuizzesBinding
import pl.depta.rafal.myquiz.ui.base.BaseActivity
import pl.depta.rafal.myquiz.ui.quiz.QuizActivity
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Named

class QuizzesActivity : BaseActivity<ActivityQuizzesBinding, QuizzesViewModel>(), QuizViewHolder.ItemClickListener {

    @Inject
    lateinit var mViewModel: QuizzesViewModel
    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var mQuizzesAdapter: QuizzesAdapter
    @Inject
    lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager

    override val layoutId = R.layout.activity_quizzes

    private var mBinding: ActivityQuizzesBinding? = null

    companion object {
        const val QUIZ_ID = "QUIZ_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = viewDataBinding
        setUp()
        subscribeLiveData()
        Log.d("DB", DebugDB.getAddressLog())
    }

    private fun setUp() {
        setSupportActionBar(mBinding?.toolbar)
        mQuizzesAdapter.itemClickListener = this
        mBinding?.rvQuizList?.layoutManager = staggeredGridLayoutManager
        mBinding?.rvQuizList?.adapter = mQuizzesAdapter
    }

    private fun subscribeLiveData() {
        mViewModel.mQuizList.observe(this, Observer {
            it?.let {
                mQuizzesAdapter.setQuizList(it)
            }
        })
    }

    override fun onClick(id: Long) {
        Log.d("QuizzesActivity", "Clicked!!! ID: $id")
        val intent = Intent(this, QuizActivity::class.java)
        intent.putExtra(QUIZ_ID, id)
        startActivity(intent)
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): QuizzesViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(QuizzesViewModel::class.java)
        return mViewModel
    }


}
