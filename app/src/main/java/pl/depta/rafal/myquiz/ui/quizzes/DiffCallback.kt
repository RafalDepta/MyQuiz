package pl.depta.rafal.myquiz.ui.quizzes

import android.support.v7.util.DiffUtil
import pl.depta.rafal.myquiz.data.local.entity.QuizEntity

class DiffCallback() : DiffUtil.ItemCallback<QuizEntity>() {
    override fun areItemsTheSame(oldItem: QuizEntity?, newItem: QuizEntity?): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: QuizEntity?, newItem: QuizEntity?): Boolean {
        return newItem?.id == oldItem?.id
                && newItem?.progress == oldItem?.progress
                && newItem?.lastResult == oldItem?.lastResult


    }

}