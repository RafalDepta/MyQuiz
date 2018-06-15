package pl.depta.rafal.myquiz.data.model

import com.google.gson.annotations.SerializedName

data class Quizzes(
        val count:Int,
        @SerializedName("items") val quizzes: List<Quiz>
)