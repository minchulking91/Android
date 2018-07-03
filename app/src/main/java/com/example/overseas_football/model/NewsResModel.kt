package com.example.overseas_football.model

import android.os.Parcel
import android.os.Parcelable


data class NewsResModel(
        val status: String,
        val totalResults: Int,
        val articles: List<Articles>
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            listOf<Articles>().apply {
                parcel.readList(this, Articles::class.java.classLoader)
            }
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(status)
        parcel.writeInt(totalResults)
        parcel.writeList(articles)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsResModel> {
        override fun createFromParcel(parcel: Parcel): NewsResModel {
            return NewsResModel(parcel)
        }

        override fun newArray(size: Int): Array<NewsResModel?> {
            return arrayOfNulls(size)
        }
    }
}

data class Articles(
        val author: Any,
        val title: String,
        val description: String,
        val url: String,
        val urlToImage: String,
        val publishedAt: String,
        val source: Source? = null
)

data class Source(
        val id: Any,
        val name: String
)