package com.msf.shufflesongs.network

import android.content.Context
import com.msf.shufflesongs.model.MusicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MusicRepository {

    private val JOHN_DOLLAR_ID = 909253
    private val CHARLINE_CHEWIE_ID = 1171421960
    private val BLOCO_TOTI_ID = 358714030
    private val MC_ARIANNE_ID = 1419227
    private val DECIMAIS_ID = 264111789

    private fun getService(context: Context): Services {
        return WebServiceManager.getinstance(context)
    }

    fun getMusics(context: Context, onSuccess: (musicResponse: MusicResponse?) -> Unit, onFailure: (message: String?) -> Unit){
        getService(context).getFeed(listOf(JOHN_DOLLAR_ID, CHARLINE_CHEWIE_ID, BLOCO_TOTI_ID, MC_ARIANNE_ID, DECIMAIS_ID))
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