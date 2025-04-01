package com.example.absolute_cinema.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index

@Entity(
    tableName = "production_company_cross_ref",
    primaryKeys = ["movieId", "companyId"],
    foreignKeys = [
        ForeignKey(entity = MovieEntity::class, parentColumns = ["id"], childColumns = ["movieId"], onDelete = CASCADE),
        ForeignKey(entity = ProductionCompanyEntity::class, parentColumns = ["id"], childColumns = ["companyId"], onDelete = CASCADE)
    ],
    indices = [Index(value = ["companyId"]), Index(value = ["movieId"])]
)
data class ProductionCompanyCrossRef(
    val movieId: Int,
    val companyId: Int
)
