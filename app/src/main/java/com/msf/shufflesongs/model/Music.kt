package com.msf.shufflesongs.model


import com.google.gson.annotations.SerializedName

data class Music(

    @SerializedName("artistName")
    val artistName: String?,

    @SerializedName("artworkUrl")
    val artworkUrl: String?,

    @SerializedName("primaryGenreName")
    val primaryGenreName: String?,

    @SerializedName("trackName")
    val trackName: String?,

    @SerializedName("wrapperType")
    val wrapperType: String?
)