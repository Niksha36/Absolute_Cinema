package com.example.absolute_cinema.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
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
import com.example.absolute_cinema.data.local.entities.SimilarMovieEntity
import com.example.absolute_cinema.data.local.entities.StreamingPlatformEntity
import com.example.absolute_cinema.data.mapper.ActorEntityListToCrossRef
import com.example.absolute_cinema.data.mapper.MovieEpisodeEntityToCrossRef
import com.example.absolute_cinema.data.mapper.ProductionCompanyListToCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM MoviePosterEntity")
    fun getMoviePosters(): Flow<List<MoviePosterEntity>>

    @Transaction
    @Query("SELECT * FROM movies WHERE id = :movieId")
    suspend fun getMovieDetails(movieId: Int): MovieWithRelations?

    @Transaction
    @Upsert
    suspend fun insertMovie(movie: MovieWithRelations) {
        val movieId = movie.movie.id

        insertMovie(movie.movie)
        movie.rating?.let { insertRating(it) }
        movie.boxOffice?.let { insertBoxOffice(it) }
        movie.productionCompanies?.let { insertProductionCompany(it) }
        val productionCompanyNames = movie.productionCompanies?.map { it.name } ?: emptyList()
        val productionCompanyIds = getProductionCompanyIds(productionCompanyNames)
        movie.productionCompanies?.ProductionCompanyListToCrossRef(movieId, productionCompanyIds)
            ?.let { insertProductionCompanyCrossRef(it) }
        movie.episodes?.let { insertMovieEpisode(it) }
        movie.episodes?.MovieEpisodeEntityToCrossRef(movieId).let {
            it?.let { it1 ->
                insertMovieEpisodeCrossRef(
                    it1
                )
            }
        }
        movie.facts?.let { insertMovieFact(it) }
        movie.persons?.let { insertActor(it) }
        movie.persons?.ActorEntityListToCrossRef(movieId)?.let { insertMovieActorCrossRef(it) }
        movie.seasonsInfo?.let { insertSeasonInfoEntity(it) }
        movie.platformsAvailableOn?.forEach {
            insertStreamingPlatform(it.platform)
            insertMovieStreamingPlatformUrl(it.let {
                MovieStreamingPlatformUrlEntity(
                    movieId = movieId,
                    platformId = it.platform.id,
                    url = it.url
                )
            })
        }
        movie.similarMovies?.forEach {
            insertSimilarMovieEntity(it.similarMovie)
            insertSimilarMovieCrossRef(
                SimilarMovieCrossRef(
                    movieId = movieId,
                    similarMovieId = it.similarMovie.id
                )
            )
        }

        movie.budget?.let { insertBudgetEntity(it) }
        movie.comments?.let { insertCommentEntity(it) }

    }

    @Query("DELETE FROM movies WHERE id = :movieId")
    suspend fun deleteMovie(movieId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM movies WHERE id = :movieId)")
    fun isMovieSaved(movieId: Int): Flow<Boolean>

    // Запросы для сохранения одного фильма :)
    @Upsert
    suspend fun insertMovie(movie: MovieEntity)

    @Upsert
    suspend fun insertRating(rating: MovieRatingEntity)

    @Upsert
    suspend fun insertBoxOffice(boxOffice: List<BoxOfficeByLocationEntity>)

    //ProductionCompany
    @Upsert
    suspend fun insertProductionCompany(productionCompany: List<ProductionCompanyEntity>)

    @Upsert
    suspend fun insertProductionCompanyCrossRef(productionCompanyCrossRef: List<ProductionCompanyCrossRef>)

    //MovieEpisode
    @Upsert
    suspend fun insertMovieEpisode(movieEpisodeEntity: List<MovieEpisodeEntity>)

    @Upsert
    suspend fun insertMovieEpisodeCrossRef(movieEpisodeCrossRef: List<MovieEpisodeCrossRef>)

    @Upsert
    suspend fun insertMovieFact(movieFactEntity: List<MovieFactEntity>)

    //Actor
    @Upsert
    suspend fun insertActor(actor: List<ActorEntity>)
    @Upsert
    suspend fun insertMovieActorCrossRef(movieActorCrossRef: List<MovieActorCrossRef>)

    @Upsert
    suspend fun insertSeasonInfoEntity(seasonInfoEntity: List<SeasonInfoEntity>)

    //StreamingPlatform
    @Upsert
    suspend fun insertStreamingPlatform(streamingPlatformEntity: StreamingPlatformEntity)

    @Upsert
    suspend fun insertMovieStreamingPlatformUrl(movieStreamingPlatformUrlEntity: MovieStreamingPlatformUrlEntity)

    //SimilarMovie
    @Upsert
    suspend fun insertSimilarMovieEntity(similarMovieEntity: SimilarMovieEntity)

    @Upsert
    suspend fun insertSimilarMovieCrossRef(similarMovieCrossRef: SimilarMovieCrossRef)


    @Upsert
    suspend fun insertBudgetEntity(budgetEntity: BudgetEntity)

    @Upsert
    suspend fun insertCommentEntity(commentEntity: List<CommentEntity>)

    @Query("SELECT id FROM ProductionCompanyEntity WHERE name IN (:names)")
    suspend fun getProductionCompanyIds(names: List<String?>): List<Int>

}