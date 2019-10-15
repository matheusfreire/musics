package com.msf.shufflesongs.model


import com.google.gson.annotations.SerializedName

data class Music(
    @SerializedName("artistId")
    val artistId: Int?,
    @SerializedName("artistName")
    val artistName: String?,
    @SerializedName("artistType")
    val artistType: String?,
    @SerializedName("artworkUrl")
    val artworkUrl: String?,
    @SerializedName("collectionId")
    val collectionId: Int?,
    @SerializedName("collectionName")
    val collectionName: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("primaryGenreName")
    val primaryGenreName: String?,
    @SerializedName("releaseDate")
    val releaseDate: String?,
    @SerializedName("trackCensoredName")
    val trackCensoredName: String?,
    @SerializedName("trackExplicitness")
    val trackExplicitness: String?,
    @SerializedName("trackName")
    val trackName: String?,
    @SerializedName("trackTimeMillis")
    val trackTimeMillis: Int?,
    @SerializedName("wrapperType")
    val wrapperType: String?
)