package com.example.tpandroid.articles

class ArticleRepository {
    fun getArticles(): List<Article> {
        return listOf(
            Article("Article1", "Un premier article", "img_24"),
            Article("Article2", "Un deuxième article", "img_27"),
            Article("Article3", "Un troisième article", "img_076"),
        )
    }
}