package com.example.data.model

import com.google.gson.annotations.SerializedName

//@Parcelize
data class News(
    @SerializedName("title")
    val title: String? = "",

    @SerializedName("description")
    val description: String? = "",

    @SerializedName("urlToImage")
    val imageUrl: String? = ""               //todo -- warning try to do it with backing field or backing property. Gson is breaking kotlin type safety
) //: Parcelable

data class NewsList(
    @SerializedName("status")
    val status: String = "",

    @SerializedName("totalResults")
    val resultCount: Int = 0,

    @SerializedName("articles")
    val articleList: List<News> = listOf()
)