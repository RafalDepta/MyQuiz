package pl.depta.rafal.myquiz.ui.quizzes

import android.databinding.DataBindingUtil
import android.support.v7.recyclerview.extensions.AsyncListDiffer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import pl.depta.rafal.myquiz.R
import pl.depta.rafal.myquiz.data.local.entity.QuizEntity
import pl.depta.rafal.myquiz.databinding.ItemQuizViewBinding


class QuizzesAdapter : RecyclerView.Adapter<QuizViewHolder>() {
    private val mDiffer = AsyncListDiffer(this, DiffCallback())
    var itemClickListener: QuizViewHolder.ItemClickListener? = null

    fun setQuizList(quizList: List<QuizEntity>) {
        mDiffer.submitList(quizList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val itemQuizViewBinding = DataBindingUtil.inflate<ItemQuizViewBinding>(LayoutInflater.from(parent.context), R.layout.item_quiz_view, parent, false)
        return QuizViewHolder(itemQuizViewBinding)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.onBind(mDiffer.currentList[position], itemClickListener)
    }

    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }
}