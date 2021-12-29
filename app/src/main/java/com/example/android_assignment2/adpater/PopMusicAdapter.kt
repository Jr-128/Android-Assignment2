package com.example.android_assignment2.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_assignment2.R

class PopMusicAdapter : RecyclerView.Adapter<PopMusicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopMusicViewHolder {
        // here you inflate each cardview for the songs from your xml file
        // and then create your view holder passing that view you have inflated
        val view = LayoutInflater.from(parent.context).inflate(R.layout.your_items_song, parent, false)
        return PopMusicViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopMusicViewHolder, position: Int) {
        // here you will bind the data to the view holder
    }

    override fun getItemCount(): Int {
        // here is the size of you data set
        // most likely you will be passing a list of music into the adapter
        return 0
    }
}

class PopMusicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // here you are going to access your views (textviews, buttons, images, etc)
}