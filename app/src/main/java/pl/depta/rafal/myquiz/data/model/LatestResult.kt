package pl.depta.rafal.myquiz.data.model

data class LatestResult(
        val city: Int,
        val end_date: String,
        val result: Float,
        val resolveTime: Int
)