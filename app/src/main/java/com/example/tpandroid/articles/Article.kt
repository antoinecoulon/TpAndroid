package com.example.tpandroid.articles

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleResponse(
    val code: Int,
    val message: String,
    val data: List<Article>
)

data class Article(
    val id: Int,
    val title: String,
    val desc: String,
    val author: String,
    val imgPath : String?
)