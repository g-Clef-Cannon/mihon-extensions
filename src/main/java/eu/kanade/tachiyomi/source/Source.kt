package eu.kanade.tachiyomi.source

interface Source {
    val id: Long get() = name.hashCode().toLong()
    val name: String
    val lang: String
    val baseUrl: String
}
