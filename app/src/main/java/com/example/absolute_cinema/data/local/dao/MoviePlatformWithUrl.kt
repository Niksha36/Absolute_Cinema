package com.example.absolute_cinema.data.local.dao

import androidx.room.DatabaseView
import androidx.room.Embedded
import com.example.absolute_cinema.data.local.entities.StreamingPlatformEntity

@DatabaseView(
    viewName = "movie_platform_with_url_view",
    value = """
        SELECT 
            sp.id AS id, 
            sp.logo AS logo, 
            sp.name AS name,
            ms.url AS url
        FROM streaming_platform sp
        INNER JOIN movie_streaming_platform_url ms ON sp.id = ms.platformId
    """
)
data class MoviePlatformWithUrl(
    @Embedded val platform: StreamingPlatformEntity,
    val url: String?
)

