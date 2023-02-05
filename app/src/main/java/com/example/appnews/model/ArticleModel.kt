import android.os.Parcelable
import com.example.appnews.model.Source
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleModel (

    val source : Source,
    val author : String?,
    val title : String,
    val description : String,
    val url : String,
    val urlToImage : String?,
    val publishedAt : String,
    val content : String?,
    private var noticiasFavoritas: List<ArticleModel>? = null
): Parcelable {
    constructor(): this(
        source = Source(),
        author = null,
        title = "",
        description = "",
        url = "",
        urlToImage = null,
        publishedAt = "",
        content = null
    )

    fun setNoticiasFavoritas(noticiasFavoritas: List<ArticleModel>?) {
        this.noticiasFavoritas = noticiasFavoritas
    }

    fun getNoticiasFavoritas(): List<ArticleModel>? {
        return noticiasFavoritas
    }
}