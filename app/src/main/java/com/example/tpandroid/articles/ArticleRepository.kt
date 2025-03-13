package com.example.tpandroid.articles

class ArticleRepository {
    private val articles = mutableListOf(
        Article("Article1", "Un premier article", "img_24"),
        Article("Article2", "Un deuxième article", "img_27"),
        Article("Article3", "Un troisième article", "img_076")
    )

    fun getArticles(): List<Article> {
        return articles.toList()
    }

    fun addArticle(title: String, desc: String, imgPath: String? = null) {
        // Créer une instance d'Article
        val newArticle = Article(
            title = title,
            desc = desc,
            imgPath = imgPath ?: "default_image"
        )
        articles.add(newArticle)
    }
}