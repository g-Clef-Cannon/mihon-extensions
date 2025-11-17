package eu.kanade.tachiyomi.extension.en.anotherpieceofcandy

import eu.kanade.tachiyomi.source.model.FilterList
import eu.kanade.tachiyomi.source.model.MangasPage
import eu.kanade.tachiyomi.source.model.Page
import eu.kanade.tachiyomi.source.model.SChapter
import eu.kanade.tachiyomi.source.model.SManga
import eu.kanade.tachiyomi.source.online.ParsedHttpSource
import eu.kanade.tachiyomi.source.online.GET
import eu.kanade.tachiyomi.network.cloudflareClient
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.text.SimpleDateFormat
import java.util.Locale

class AnotherPieceOfCandy : ParsedHttpSource() {

    override val name = "Another Piece of Candy"

    override val baseUrl = "https://another-piece-of-candy.thecomicseries.com"

    override val lang = "en"

    override val supportsLatest = false

    override val client = OkHttpClient().cloudflareClient
    
    override val headers: Headers = Headers.Builder()
        .add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
        .build()

    // ============================== Popular ===============================
    // This webcomic doesn't have a "popular" listing, so we'll just return the main series
    override fun popularMangaRequest(page: Int): Request = GET("$baseUrl/archive/", headers)

    override fun popularMangaSelector() = "html" // Dummy selector since there's only one manga

    override fun popularMangaFromElement(element: Element): SManga {
        return SManga.create().apply {
            title = "Another Piece of Candy"
            url = "/archive/"
            author = "NomnomNami"
            artist = "NomnomNami"
            description = "A cute webcomic about candy characters and their adventures."
            thumbnail_url = "https://img.comicfury.com/avatars/43854.png" // Comic avatar
            status = SManga.ONGOING
        }
    }

    override fun popularMangaNextPageSelector(): String? = null

    override fun popularMangaParse(response: Response): MangasPage {
        // Return a single manga entry
        val manga = SManga.create().apply {
            title = "Another Piece of Candy"
            url = "/archive/"
            author = "NomnomNami"
            artist = "NomnomNami"
            description = "A cute webcomic about candy characters and their adventures. " +
                "Follow the daily lives of Moxie, Gumdrop, and their friends in this charming slice-of-life comic."
            thumbnail_url = "https://img.comicfury.com/avatars/43854.png"
            status = SManga.ONGOING
        }
        return MangasPage(listOf(manga), false)
    }

    // =============================== Latest ===============================
    override fun latestUpdatesRequest(page: Int): Request = popularMangaRequest(page)
    override fun latestUpdatesSelector() = popularMangaSelector()
    override fun latestUpdatesFromElement(element: Element) = popularMangaFromElement(element)
    override fun latestUpdatesNextPageSelector() = popularMangaNextPageSelector()

    // =============================== Search ===============================
    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request {
        return popularMangaRequest(page)
    }

    override fun searchMangaSelector() = popularMangaSelector()
    override fun searchMangaFromElement(element: Element) = popularMangaFromElement(element)
    override fun searchMangaNextPageSelector() = popularMangaNextPageSelector()

    override fun searchMangaParse(response: Response): MangasPage {
        return popularMangaParse(response)
    }

    // =========================== Manga Details ============================
    override fun mangaDetailsParse(document: Document): SManga {
        return SManga.create().apply {
            title = "Another Piece of Candy"
            author = "NomnomNami"
            artist = "NomnomNami"
            description = "A cute webcomic about candy characters and their adventures. " +
                "Follow the daily lives of Moxie, Gumdrop, and their friends in this charming slice-of-life comic.\n\n" +
                "Created by NomnomNami and hosted on Comic Fury."
            thumbnail_url = "https://img.comicfury.com/avatars/43854.png"
            status = SManga.ONGOING
            genre = "Slice of Life, Comedy, Cute"
        }
    }

    // ============================== Chapters ==============================
    override fun chapterListSelector() = "ol li a"

    override fun chapterListRequest(manga: SManga): Request {
        return GET("$baseUrl/archive/", headers)
    }

    override fun chapterFromElement(element: Element): SChapter {
        return SChapter.create().apply {
            // The archive page has links like "/comics/pl/1060926/"
            url = element.attr("href")
            
            // Extract chapter title from the text
            val fullText = element.text().trim()
            // Format: "1.trip to the candy store 1 8/27/2016, 1:00 PM"
            // We want to extract the title and date
            
            val parts = fullText.split(" ")
            val chapterNumber = parts.firstOrNull()?.removeSuffix(".")
            
            // Extract title (everything between number and date)
            val titleStart = fullText.indexOf('.') + 1
            val datePattern = Regex("""\d{1,2}/\d{1,2}/\d{4}""")
            val dateMatch = datePattern.find(fullText)
            
            val chapterTitle = if (dateMatch != null) {
                fullText.substring(titleStart, dateMatch.range.first).trim()
            } else {
                fullText.substring(titleStart).trim()
            }
            
            name = if (chapterNumber != null) {
                "Chapter $chapterNumber - $chapterTitle"
            } else {
                chapterTitle
            }
            
            // Parse date if available
            if (dateMatch != null) {
                try {
                    val dateStr = dateMatch.value
                    val timeStr = fullText.substring(dateMatch.range.last + 1).trim()
                    val dateTimeStr = "$dateStr $timeStr"
                    val dateFormat = SimpleDateFormat("M/d/yyyy h:mm a", Locale.ENGLISH)
                    date_upload = dateFormat.parse(dateTimeStr)?.time ?: 0L
                } catch (e: Exception) {
                    date_upload = 0L
                }
            } else {
                date_upload = 0L
            }
            
            // Set chapter number for sorting
            chapterNumber?.toFloatOrNull()?.let { chapter_number = it }
        }
    }

    override fun chapterListParse(response: Response): List<SChapter> {
        val document = response.asJsoup()
        val chapters = document.select(chapterListSelector()).map { chapterFromElement(it) }
        // Reverse so newest chapters appear first
        return chapters.reversed()
    }

    // =============================== Pages ================================
    override fun pageListParse(document: Document): List<Page> {
        // The comic image is in an img tag within the content area
        // Looking at the HTML structure: <img src="https://img.comicfury.com/comics/415/...png">
        val imageElement = document.selectFirst("div#cc img, img[src*=comicfury.com/comics]")
            ?: throw Exception("Could not find comic image on page")
        
        val imageUrl = imageElement.absUrl("src")
        
        return listOf(Page(0, "", imageUrl))
    }

    override fun imageUrlParse(document: Document): String {
        throw UnsupportedOperationException("Not used")
    }

    // ============================= Utilities ==============================
    override fun getFilterList() = FilterList()
}
