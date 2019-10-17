package com.msf.shufflesongs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.msf.shufflesongs.R
import com.msf.shufflesongs.model.Music
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_musics.view.*

class MusicsRecyclerViewAdapter(private val mValues: List<Music>) : RecyclerView.Adapter<MusicsRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_musics, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        fun bind(music: Music){
            with(mView){
                Picasso.get().load(music.artworkUrl).into(imgMusicAlbum)
                txtMusicTitle.text = music.trackName
                txtMusicSinger.text = context.getString(R.string.music_item,music.artistName, music.primaryGenreName)
            }
        }
    }
}
