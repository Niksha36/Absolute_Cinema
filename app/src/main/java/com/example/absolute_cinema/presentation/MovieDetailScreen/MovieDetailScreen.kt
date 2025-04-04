package com.example.absolute_cinema.presentation.MovieDetailScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.absolute_cinema.presentation.MovieDetailScreen.components.BoxOfficeCard
import com.example.absolute_cinema.presentation.MovieDetailScreen.components.DetailActions
import com.example.absolute_cinema.presentation.MovieDetailScreen.components.FactCard
import com.example.absolute_cinema.presentation.MovieDetailScreen.components.LittleRatingBox
import com.example.absolute_cinema.presentation.MovieDetailScreen.components.MovieCharacteristics
import com.example.absolute_cinema.presentation.MovieDetailScreen.components.ParallaxMoviePoster
import com.example.absolute_cinema.presentation.MovieDetailScreen.components.PersonCard
import com.example.absolute_cinema.presentation.MovieDetailScreen.components.RatingBox
import com.example.absolute_cinema.presentation.MovieDetailScreen.components.TrailerCard
import com.example.absolute_cinema.presentation.MovieDetailScreen.components.comment.CommentCard
import com.example.absolute_cinema.presentation.common.MovieCard
import com.example.absolute_cinema.util.UtilFunctions.avgRating
import com.example.absolute_cinema.util.UtilFunctions.ratingColor

@Composable
fun MovieDetailScreen(
    state: MovieDetailScreenState,
    onNavigateToMovieDetails: (Int) -> Unit,
    navigateToMovieComment: (String) -> Unit,
    onEvent: (MovieDetailsEvent) -> Unit
) {
    val scrollState = rememberScrollState()
    val details = state.movieDetails
    if (state.error != null) {
        Log.e("MovieDetailScreen", state.error)
    }

    when (state.showExpandedContent) {

        ExpandedListContentTypes.COMMENTS -> {
            details?.let {
                ExpandedListScreen(
                    purpose = ExpandedListContentTypes.COMMENTS,
                    goBack = { onEvent(MovieDetailsEvent.hideExpandedContent) },
                    listOfItems = it.comments,
                    onItemClick = {
                        navigateToMovieComment(it)
                    }
                )
            }
        }

        ExpandedListContentTypes.Facts -> {
            ExpandedListScreen(
                purpose = ExpandedListContentTypes.Facts,
                goBack = { onEvent(MovieDetailsEvent.hideExpandedContent) },
                listOfItems = state.movieDetails?.facts ?: emptyList()
            )
        }

        else -> {
            Box(Modifier.fillMaxSize()) {
                if(state.isLoadingDetails){
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
            ) {

                details?.let { movieDetails ->
                    val imageUrl =
                        if (!movieDetails.previewImage.isNullOrEmpty()) movieDetails.previewImage else movieDetails.poster
                    ParallaxMoviePoster(
                        poster = imageUrl,
                        name = movieDetails.name,
                        scrollState = scrollState
                    )
                    Box(
                        Modifier
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(top = 5.dp, bottom = 10.dp)
                    ) {
                        Column(Modifier.padding(horizontal = 10.dp)) {

                            // Список жанров
                            LazyRow(
                                horizontalArrangement = spacedBy(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp)
                            ) {
                                val genres = movieDetails.genres ?: emptyList()
                                items(genres) {
                                    Card() {
                                        Text(
                                            "#$it",
                                            style = MaterialTheme.typography.titleMedium,
                                            modifier = Modifier.padding(
                                                horizontal = 8.dp,
                                                vertical = 4.dp
                                            )
                                        )
                                    }
                                }
                            }
                            // Actions
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 18.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                DetailActions(
                                    saveAction = {
                                        onEvent(
                                            MovieDetailsEvent.saveMovie(
                                                movieDetails,
                                                details.comments
                                            )
                                        )
                                    },
                                    deleteAction = {
                                        onEvent(
                                            MovieDetailsEvent.deleteMovie(
                                                movieDetails.id
                                            )
                                        )
                                    },
                                    state = state
                                )
                            }

                            // Характеристики
                            MovieCharacteristics(
                                countries = movieDetails.countries,
                                year = movieDetails.year,
                                languages = movieDetails.spokenLanguages,
                                ageRating = movieDetails.ageRating,
                                duration = movieDetails.movieLength,
                                budget = movieDetails.budget
                            )
                            // Описание

                            movieDetails.shortDescription?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                    color = Color.White,
                                    modifier = Modifier.padding(vertical = 10.dp)
                                )
                            }
                            movieDetails.description?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            //Трейлеры
                            val trailers = movieDetails.videos
                            if (!trailers.isNullOrEmpty()) {
                                Text(
                                    "Трейлеры",
                                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                    color = Color.White,
                                    modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
                                )
                                LazyRow(
                                    Modifier.fillMaxWidth(),
                                    horizontalArrangement = spacedBy(15.dp),
                                ) {
                                    items(trailers) { trailer ->
                                        trailer.url?.let { TrailerCard(it, trailer.name) }
                                    }
                                }
                            }
                            // Рейтинг
                            Text(
                                "Рейтинг Absolute Cinema",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                color = Color.White,
                                modifier = Modifier.padding(top = 20.dp)
                            )
                            val rating =
                                avgRating(movieDetails.rating?.kinopoisk, movieDetails.rating?.imdb)
                            RatingBox(
                                rating = rating,
                                color = ratingColor(rating)
                            )

                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .height(70.dp),
                                horizontalArrangement = spacedBy(10.dp)
                            ) {
                                movieDetails.rating?.imdb?.let {
                                    Box(Modifier.weight(1f)) {
                                        LittleRatingBox(
                                            rating = it,
                                            ratingName = "IMDb"
                                        )
                                    }

                                }
                                movieDetails.rating?.kinopoisk?.let {
                                    Box(Modifier.weight(1f)) {
                                        LittleRatingBox(
                                            rating = it,
                                            ratingName = "Kinopoisk"
                                        )
                                    }
                                }

                            }
                            //Прокат
                            Text(
                                "Прокат",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                color = Color.White,
                                modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
                            )
                            LazyRow(
                                Modifier
                                    .fillMaxWidth()
                                    .height(100.dp),
                                horizontalArrangement = spacedBy(10.dp),

                                ) {
                                val boxOfficeList = listOf(
                                    movieDetails.boxOffice?.usa,
                                    movieDetails.boxOffice?.russia,
                                    movieDetails.boxOffice?.world
                                )

                                items(boxOfficeList) { boxOffice ->

                                    Box(
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        BoxOfficeCard(
                                            place = boxOffice?.region?.let { stringResource(id = it.stringResId) },
                                            value = boxOffice?.value,
                                            currency = boxOffice?.currency
                                        )
                                    }
                                }
                            }

                            // Команда
                            Text(
                                "Команда",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                color = Color.White,
                                modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
                            )
                            movieDetails.persons?.let { persons ->
                                LazyRow(
                                    Modifier.fillMaxWidth(),
                                    horizontalArrangement = spacedBy(15.dp),
                                ) {
                                    items(persons.chunked(3)) { personChunk ->

                                        Column(verticalArrangement = spacedBy(10.dp)) {
                                            personChunk.forEach { person ->
                                                PersonCard(
                                                    name = person.name,
                                                    role = person.profession,
                                                    imageUrl = person.photo
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                            // Факты
                            Row(
                                modifier = Modifier
                                    .padding(top = 20.dp, bottom = 10.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    "Удивительные факты",
                                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                    color = Color.White,
                                )
                                Text(
                                    "Всё",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.tertiaryContainer,
                                    modifier = Modifier
                                        .padding(end = 10.dp)
                                        .clickable {
                                            onEvent(
                                                MovieDetailsEvent.showExpandedContent(
                                                    ExpandedListContentTypes.Facts
                                                )
                                            )
                                        }
                                )
                            }

                            LazyRow(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = spacedBy(15.dp),
                            ) {
                                movieDetails.facts?.let { facts ->
                                    items(facts) { fact ->
                                        FactCard(
                                            fact = fact,
                                            modifier = Modifier
                                                .width(200.dp)
                                                .height(150.dp)
                                                .weight(1f)
                                        )
                                    }
                                }

                            }
                            //Сиквелы и приквелы
                            if (!movieDetails.sequelsAndPrequels.isNullOrEmpty()) {
                                Text(
                                    "Сиквелы и приквелы",
                                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                    color = Color.White,
                                    modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
                                )

                                LazyRow(
                                    Modifier.fillMaxWidth(),
                                    horizontalArrangement = spacedBy(15.dp),
                                ) {
                                    movieDetails.sequelsAndPrequels.let { sequels ->
                                        items(sequels) { sequel ->
                                            MovieCard(
                                                poster = sequel.poster,
                                                name = sequel.name ?: "-",
                                                rating = avgRating(
                                                    sequel.rating?.kinopoisk,
                                                    sequel.rating?.imdb
                                                ),
                                            ) {
                                                onNavigateToMovieDetails(sequel.id)
                                            }
                                        }
                                    }
                                }
                            }
                            // Для сериалов - сезоны
                            if (!movieDetails.seasonsInfo.isNullOrEmpty()) {
                                movieDetails.seasonsInfo.let { seasons ->
                                    Text(
                                        "Информация о сезонах",
                                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                        color = Color.White,
                                        modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
                                    )
                                    LazyRow {
                                        items(seasons.chunked(3)) { chunkedSeasons ->
                                            Column {
                                                chunkedSeasons.forEach { season ->
                                                    Column {
                                                        Text(
                                                            "Сезон ${season.number}",
                                                            style = MaterialTheme.typography.titleMedium.copy(
                                                                fontWeight = FontWeight.Bold
                                                            ),
                                                            color = Color.White,
                                                            modifier = Modifier.padding(top = 10.dp)
                                                        )
                                                        Text(
                                                            "Серий: ${season.episodesCount}",
                                                            style = MaterialTheme.typography.bodyMedium,
                                                            modifier = Modifier.padding(bottom = 10.dp)
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                            //Отзывы
                            val comments = details.comments
                            Row(
                                modifier = Modifier
                                    .padding(top = 20.dp, bottom = 10.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    "Отзывы",
                                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                    color = Color.White
                                )
                                Text(
                                    "Всё",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.tertiaryContainer,
                                    modifier = Modifier
                                        .padding(end = 10.dp)
                                        .clickable {
                                            onEvent(
                                                MovieDetailsEvent.showExpandedContent(
                                                    ExpandedListContentTypes.COMMENTS
                                                )
                                            )
                                        }
                                )
                            }

                            if (comments.isEmpty()) {
                                Text(
                                    "Отзывов пока нет" + " 😢",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            } else {
                                LazyRow(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(200.dp),
                                    horizontalArrangement = spacedBy(10.dp)
                                ) {
                                    items(comments) {
                                        CommentCard(
                                            comment = it,
                                            Modifier.width(270.dp),
                                            { comment ->
                                                navigateToMovieComment(comment)
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
