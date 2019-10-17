package com.msf.shufflesongs.util

import kotlin.random.Random

object UtilList {

    fun <T> shuffle(list: MutableList<T>){
        list.shuffle(Random(128))
    }

}