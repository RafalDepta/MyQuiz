package pl.depta.rafal.myquiz.data.model

data class Quiz(
    val buttonStart: String,
    val shareTitle: String,
    val questions: Int,
    val createdAt: String,
    val sponsored: Boolean,
    val categories: List<Categories>,
    val id: Long,
    val title: String,
    val type: String,
    val content: String,
    val mainPhoto: MainPhoto,
    val tags: List<Tag>,
    val category: Category
)