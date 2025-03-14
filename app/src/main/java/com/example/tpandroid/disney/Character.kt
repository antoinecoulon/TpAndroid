package com.example.tpandroid.disney

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse(
    val info: Info,
    val data: List<Character>
)

@JsonClass(generateAdapter = true)
data class Info(
    val totalPages: Int,
    val count: Int,
    val previousPage: String?,
    val nextPage: String?
)

@JsonClass(generateAdapter = true)
class Character(
    @Json(name= "_id") val id: Int,
    val sourceUrl: String,
    val name: String,
    val imageUrl: String,
    val films: List<String>,
    val tvShows: List<String>
)