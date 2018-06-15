package pl.depta.rafal.myquiz.data.model

data class Question(
        val image: Image,
        val answers: List<Answer>,
        val text: String,
        val answer: String,
        val type: String,
        val order: Int
)