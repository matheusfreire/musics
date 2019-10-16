package com.msf.shufflesongs.network

import com.msf.shufflesongs.model.MusicResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    @GET("/feed")
    fun getFeed(@Query("id") vararg id: Int) : Call<MusicResponse>
}