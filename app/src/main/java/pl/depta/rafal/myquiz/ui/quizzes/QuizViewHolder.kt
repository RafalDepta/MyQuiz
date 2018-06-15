package pl.depta.rafal.myquiz.ui.quizzes

import pl.depta.rafal.myquiz.data.local.entity.QuizEntity
import pl.depta.rafal.myquiz.databinding.ItemQuizViewBinding
import pl.depta.rafal.myquiz.ui.base.BaseViewHolder

class QuizViewHolder(val mBinding: ItemQuizViewBinding) : BaseViewHolder(mBinding.root) {


    fun onBind(quizEntity: QuizEntity, itemClickListener: ItemClickListener?) {
        val quizItemViewModel = QuizItemViewModel(quizEntity,itemClickListener)
        mBinding.viewModel = quizItemViewModel
    }


    interface ItemClickListener {
        fun onClick(id: Long)
    }
}