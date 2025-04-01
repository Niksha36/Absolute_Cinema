package com.example.absolute_cinema.data.local.dao

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
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
import com.example.absolute_cinema.data.local.entities.ProductionCompanyCrossRef
import com.example.absolute_cinema.data.local.entities.ProductionCompanyEntity
import com.example.absolute_cinema.data.local.entities.SeasonInfoEntity
import com.example.absolute_cinema.data.local.entities.SimilarMovieCrossRef

data class MovieWithRelations(
    @Embedded val movie: MovieEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "movieId",
    )
    val rating: MovieRatingEntity?,

    @Relation(
        parentColumn = "id",
        entityColumn = "movieId"
    )
    val boxOffice: List<BoxOfficeByLocationEntity>?,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = ProductionCompanyCrossRef::class,
            parentColumn = "movieId",
            entityColumn = "companyId"
        )
    )
    val productionCompanies: List<ProductionCompanyEntity>?,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = MovieEpisodeCrossRef::class,
            parentColumn = "movieId",
            entityColumn = "episodeId"
        )
    )
    val episodes: List<MovieEpisodeEntity>?,

    @Relation(
        parentColumn = "id",
        entityColumn = "movieId",
    )
    val facts: List<MovieFactEntity>?,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = MovieActorCrossRef::class,
            parentColumn = "movieId",
            entityColumn = "actorId"
        )
    )
    val persons: List<ActorEntity>?,

    @Relation(
        parentColumn = "id",
        entityColumn = "movieId",
    )
    val seasonsInfo: List<SeasonInfoEntity>?,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(value = MovieStreamingPlatformUrlEntity::class)
    )
    val platformsAvailableOn: List<MoviePlatformWithUrl>?,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(value = SimilarMovieCrossRef::class, parentColumn = "movieId", entityColumn = "similarMovieId")
    )
    val similarMovies: List<SimilarMovieWithRating>?,

    @Relation(
        parentColumn = "id",
        entityColumn = "movieId"
    )
    val budget: BudgetEntity?,

    @Relation(
        parentColumn = "id",
        entityColumn = "movieId"
    )
    val comments: List<CommentEntity>?
)
