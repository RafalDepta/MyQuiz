package pl.depta.rafal.myquiz.ui.quizzes

import android.databinding.ObservableField
import pl.depta.rafal.myquiz.data.local.entity.QuizEntity

class QuizItemViewModel(val quiz: QuizEntity, private val itemClickListener: QuizViewHolder.ItemClickListener?) {
    val title = ObservableField<String>()
    val result = ObservableField<String>()
    val image = ObservableField<String>()

    init {
        this.title.set(quiz.title)
        this.image.set(quiz.imageUrl)
    }

    fun onClick(){
        itemClickListener?.onClick(quiz.id)
    }
}