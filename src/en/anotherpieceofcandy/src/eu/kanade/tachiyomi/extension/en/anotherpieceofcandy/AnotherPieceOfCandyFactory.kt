package eu.kanade.tachiyomi.extension.en.anotherpieceofcandy

import eu.kanade.tachiyomi.source.Source
import eu.kanade.tachiyomi.source.SourceFactory

class AnotherPieceOfCandyFactory : SourceFactory {
    override fun createSources(): List<Source> = listOf(
        AnotherPieceOfCandy(),
    )
}
