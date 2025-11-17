// Stub classes to allow compilation without the full Tachiyomi extension library
// In a real build, these would come from the extensions-lib dependency

package eu.kanade.tachiyomi.source.model

data class SManga(
    var url: String = "",
    var title: String = "",
    var artist: String? = null,
    var author: String? = null,
    var description: String? = null,
    var genre: String? = null,
    var status: Int = 0,
    var thumbnail_url: String? = null,
    var initialized: Boolean = false
) {
    companion object {
        const val UNKNOWN = 0
        const val ONGOING = 1
        const val COMPLETED = 2
        const val LICENSED = 3
        const val PUBLISHING_FINISHED = 4
        const val CANCELLED = 5
        const val ON_HIATUS = 6

        fun create(): SManga = SManga()
    }
}

data class SChapter(
    var url: String = "",
    var name: String = "",
    var date_upload: Long = 0,
    var chapter_number: Float = -1f,
    var scanlator: String? = null
) {
    companion object {
        fun create(): SChapter = SChapter()
    }
}

data class Page(
    val index: Int,
    val url: String = "",
    val imageUrl: String? = null
)

class MangasPage(
    val mangas: List<SManga>,
    val hasNextPage: Boolean
)

abstract class Filter<T>(val name: String) {
    class Header(name: String) : Filter<String>(name)
    class Separator : Filter<String>("───")
}

class FilterList(vararg filters: Filter<*>) : ArrayList<Filter<*>>(filters.toList())
