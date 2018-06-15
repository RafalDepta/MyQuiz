package pl.depta.rafal.myquiz.data.model

data class Answer(
        val image: Image,
        val order: Int,
        val text: String,
        val isCorrect: Int
)