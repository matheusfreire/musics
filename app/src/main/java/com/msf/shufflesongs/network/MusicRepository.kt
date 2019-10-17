package com.msf.shufflesongs.network

import android.content.Context
import com.msf.shufflesongs.model.MusicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MusicRepository {


    private fun getService(context: Context): Services {
        return WebServiceManager.getInstance(context)
    }

    fun getMusics(context: Context, listId: List<Int>, onSuccess: (musicResponse: MusicResponse?) -> Unit, onFailure: (message: String?) -> Unit){
        val ids = listId.joinToString().replace(" ", "")
        getService(context).getFeed(ids)
            .enqueue(object: Callback<MusicResponse> {
                override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                    onFailure(t.message)
                }

                override fun onResponse(call: Call<MusicResponse>,response: Response<MusicResponse>) {
                    if (response.isSuccessful){
                        onSuccess(response.body())
                    } else {
                        onFailure("It was not possible retrieve list of musics")
                    }
                }
            })
    }

}