package com.msf.shufflesongs.network

import android.content.Context
import com.google.gson.GsonBuilder
import com.msf.shufflesongs.BuildConfig
import com.msf.shufflesongs.util.NetworkUtil
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object WebServiceManager {

    private var baseUrl = BuildConfig.BASE_URL

    fun getinstance(context: Context): Services {
        return retrofit(context).create(Services::class.java)
    }

    private fun retrofit(context: Context): Retrofit {

        val client = OkHttpClient.Builder()
        client.readTimeout(60, TimeUnit.SECONDS)
        client.connectTimeout(15, TimeUnit.SECONDS)

        client.addInterceptor { chain ->

            if (!NetworkUtil.isOnline(context)) {
                throw Exception("No network detected")
            }

            return@addInterceptor chain.proceed(chain.request())
        }

        return Retrofit.Builder()
                .baseUrl(this.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .client(client.build())
                .build()
    }
}