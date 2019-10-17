package com.msf.shufflesongs.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.msf.shufflesongs.model.MusicResponse
import com.msf.shufflesongs.network.MusicRepository

class MusicViewModel(application: Application) : AndroidViewModel(application){

    private val mutableMusicResponse: MutableLiveData<MusicResponse> by lazy {
        MutableLiveData<MusicResponse>()
    }

    val liveDataMusicResponse: LiveData<MusicResponse>
        get() = mutableMusicResponse

    private val mutableMessageError: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    val liveDataMutableMessageError: LiveData<String>
        get() = mutableMessageError


    fun getMusic(listId: List<Int>) {
        MusicRepository.getMusics(getApplication(), listId,{mutableMusicResponse.postValue(it)}, {mutableMessageError.postValue(it)})
    }
}