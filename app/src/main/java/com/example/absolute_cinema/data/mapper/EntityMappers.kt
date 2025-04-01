package com.example.absolute_cinema.data.mapper

import com.example.absolute_cinema.data.local.dao.MoviePlatformWithUrl
import com.example.absolute_cinema.data.local.entities.ActorEntity
import com.example.absolute_cinema.data.local.entities.MovieActorCrossRef
import com.example.absolute_cinema.data.local.entities.MovieEpisodeCrossRef
import com.example.absolute_cinema.data.local.entities.MovieEpisodeEntity
import com.example.absolute_cinema.data.local.entities.MovieStreamingPlatformUrlEntity
import com.example.absolute_cinema.data.local.entities.ProductionCompanyCrossRef
import com.example.absolute_cinema.data.local.entities.ProductionCompanyEntity
import com.example.absolute_cinema.data.local.entities.SimilarMovieCrossRef
import com.example.absolute_cinema.data.local.entities.SimilarMovieEntity
import com.example.absolute_cinema.data.local.entities.StreamingPlatformEntity

fun List<ActorEntity>.ActorEntityListToCrossRef(movieId: Int): List<MovieActorCrossRef> {
    return this.map{   actorEntity->
        MovieActorCrossRef(
            movieId = movieId,
            actorId = actorEntity.id
        )
    }
}

fun List<ProductionCompanyEntity>.ProductionCompanyListToCrossRef(movieId: Int, productionCompanyIds: List<Int>): List<ProductionCompanyCrossRef> {
    return this.mapIndexed { index, productionCompanyEntity ->
        ProductionCompanyCrossRef(
            movieId = movieId,
            companyId = productionCompanyIds[index].toInt()
        )
    }
}

fun List<MovieEpisodeEntity>.MovieEpisodeEntityToCrossRef(movieId: Int): List<MovieEpisodeCrossRef> {
    return this.map{   movieEpisodeEntity->
        MovieEpisodeCrossRef(
            movieId = movieId,
            episodeId = movieEpisodeEntity.id
        )
    }
}

fun List<SimilarMovieEntity>.SimilarMovieToCrossRef(movieId: Int): List<SimilarMovieCrossRef> {
    return this.map{   similarMovieEntity->
        SimilarMovieCrossRef(
            movieId = movieId,
            similarMovieId = similarMovieEntity.id
        )
    }
}