package com.msf.shufflesongs.network

import com.msf.shufflesongs.model.MusicResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    @GET("/lookup")
    fun getFeed(@Query("id") id: List<Int>) : Call<MusicResponse>
}