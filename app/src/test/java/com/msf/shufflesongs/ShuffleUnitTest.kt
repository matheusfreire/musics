package com.msf.shufflesongs

import com.msf.shufflesongs.model.Music
import com.msf.shufflesongs.util.UtilList
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ShuffleUnitTest {

    private lateinit var myList: MutableList<Music>
    private lateinit var music_1: Music
    private lateinit var music_2: Music
    private lateinit var music_3: Music
    private lateinit var music_4: Music

    @Before
    fun createMusics(){
        music_1 = Music("John Doe", "https://firebasestorage.googleapis.com/v0/b/tw-exercicio-mobile.appspot.com/o/albums%2Fmc-arianne-boom.png?alt=media&token=37a45c0c-f441-4235-9f25-7889ca166553)",
            "Rock", "John Doe Music 1", "track")
        music_2 = Music("Mary", "https://firebasestorage.googleapis.com/v0/b/tw-exercicio-mobile.appspot.com/o/albums%2Fmc-arianne-boom.png?alt=media&token=37a45c0c-f441-4235-9f25-7889ca166553)",
            "Rock", "Mary 1", "track")
        music_3 = Music("John Doe", "https://firebasestorage.googleapis.com/v0/b/tw-exercicio-mobile.appspot.com/o/albums%2Fmc-arianne-boom.png?alt=media&token=37a45c0c-f441-4235-9f25-7889ca166553)",
            "Rock", "John Doe Music 3", "track")
        music_4 = Music("Mary", "https://firebasestorage.googleapis.com/v0/b/tw-exercicio-mobile.appspot.com/o/albums%2Fmc-arianne-boom.png?alt=media&token=37a45c0c-f441-4235-9f25-7889ca166553)",
            "Rock", "Mary Music", "track")

        myList = mutableListOf(music_1,music_2,music_3,music_4)
    }

    @Test
    fun listIsInitiated() {
        assertTrue(myList[0].equals(music_1))
    }

    @Test
    fun shuffleWorks(){
        UtilList.shuffle(myList)
        assertFalse(myList[0].equals(music_1))
    }
}
