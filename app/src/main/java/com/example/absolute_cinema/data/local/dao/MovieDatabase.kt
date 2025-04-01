package com.example.absolute_cinema.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.absolute_cinema.data.local.Converters
import com.example.absolute_cinema.data.local.entities.ActorEntity
import com.example.absolute_cinema.data.local.entities.BoxOfficeByLocationEntity
import com.example.absolute_cinema.data.local.entities.BudgetEntity
import com.example.absolute_cinema.data.local.entities.CommentEntity
import com.example.absolute_cinema.data.local.entities.MovieActorCrossRef
import com.example.absolute_cinema.data.local.entities.MovieEntity
import com.example.absolute_cinema.data.local.entities.MovieEpisodeCrossRef
import com.example.absolute_cinema.data.local.entities.MovieEpisodeEntity
import com.example.absolute_cinema.data.local.entities.MovieFactEntity
import com.example.absolute_cinema.data.local.entities.MovieRatingEntity
import com.example.absolute_cinema.data.local.entities.MovieStreamingPlatformUrlEntity
import com.example.absolute_cinema.data.local.entities.MovieTrailerEntity
import com.example.absolute_cinema.data.local.entities.ProductionCompanyCrossRef
import com.example.absolute_cinema.data.local.entities.ProductionCompanyEntity
import com.example.absolute_cinema.data.local.entities.SeasonInfoEntity
import com.example.absolute_cinema.data.local.entities.SimilarMovieCrossRef
import com.example.absolute_cinema.data.local.entities.SimilarMovieEntity
import com.example.absolute_cinema.data.local.entities.StreamingPlatformEntity

@Database(
    entities = [ActorEntity::class,
        BoxOfficeByLocationEntity::class,
        BudgetEntity::class,
        MovieActorCrossRef::class,
        MovieEntity::class,
        MovieEpisodeCrossRef::class,
        MovieEpisodeEntity::class,
        MovieFactEntity::class,
        MovieRatingEntity::class,
        MovieTrailerEntity::class,
        ProductionCompanyCrossRef::class,
        ProductionCompanyEntity::class,
        SeasonInfoEntity::class,
        SimilarMovieCrossRef::class,
        SimilarMovieEntity::class,
        StreamingPlatformEntity::class,
        MovieStreamingPlatformUrlEntity::class,
        CommentEntity::class],
    views = [MoviePosterEntity::class, SimilarMovieWithRating::class, MoviePlatformWithUrl::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract val dao: MoviesDao
}