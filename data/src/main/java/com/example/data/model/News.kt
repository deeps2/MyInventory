package com.example.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    @SerializedName("title")
    val title: String = "",

    @SerializedName("description")
    val description: String = "",

    @SerializedName("urlToImage")
    val imageUrl: String = "",
) : Parcelable

data class NewsList(
    @SerializedName("status")
    val status: String = "",

    @SerializedName("totalResults")
    val resultCount: Int = 0,

    @SerializedName("articles")
    val articleList: List<News> = listOf()
)