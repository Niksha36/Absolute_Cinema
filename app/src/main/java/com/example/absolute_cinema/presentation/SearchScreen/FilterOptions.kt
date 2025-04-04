package com.example.absolute_cinema.presentation.SearchScreen

import com.example.absolute_cinema.R

data class FilterOptions(
    val type: TypeFilter = TypeFilter.ALL,
    val genres: List<String> = emptyList(),
    val yearRange: YearRange? = null,
    val rating: IntRange = 0..10,
    val sortBy: SortBy = SortBy.POPULARITY,
    val isGenresDialogOpened: Boolean = false,
    val isYearRangeDialogOpened: Boolean = false
)

data class YearRange(val from: Int, val to: Int)

enum class TypeFilter(val resId: Int) {
    ALL(R.string.type_filter_all),
    MOVIES(R.string.type_filter_movies),
    SERIES(R.string.type_filter_series)
}
enum class SortBy(val resId: Int) {
    POPULARITY(R.string.sort_by_popularity),
    RATING(R.string.sort_by_rating),
    DATE(R.string.sort_by_date)
}

enum class Genre(val resId: Int) {
    Anime(R.string.genre_anime),
    Biografiya(R.string.genre_biografiya),
    Boevik(R.string.genre_boevik),
    Vestern(R.string.genre_vestern),
    Voennyy(R.string.genre_voennyy),
    Detektiv(R.string.genre_detektiv),
    Detskiy(R.string.genre_detskiy),
    DlyaVzroslyh(R.string.genre_dlya_vzroslyh),
    Dokumentalnyy(R.string.genre_dokumentalnyy),
    Drama(R.string.genre_drama),
    Igra(R.string.genre_igra),
    Istoriya(R.string.genre_istoriya),
    Komediya(R.string.genre_komediya),
    Koncert(R.string.genre_koncert),
    Korotkometrazhka(R.string.genre_korotkometrazhka),
    Kriminal(R.string.genre_kriminal),
    Melodrama(R.string.genre_melodrama),
    Muzyka(R.string.genre_muzyka),
    Multfilm(R.string.genre_multfilm),
    Myuzikl(R.string.genre_myuzikl),
    Novosti(R.string.genre_novosti),
    Priklyucheniya(R.string.genre_priklyucheniya),
    RealnoeTV(R.string.genre_realnoe_TV),
    Semeynyy(R.string.genre_semeynyy),
    Sport(R.string.genre_sport),
    TokShou(R.string.genre_tok_shou),
    Triller(R.string.genre_triller),
    Uzhasy(R.string.genre_uzhasy),
    Fantastika(R.string.genre_fantastika),
    FilmNuar(R.string.genre_film_nuar),
    Fentezi(R.string.genre_fentezi),
    Ceremoniya(R.string.genre_ceremoniya)
}