package com.example.android_assignment2.adpater

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class PopMusicAdapter @Inject constructor(
    private val iPreviewSongClick: IPreviewSongClick

) : RecyclerView.Adapter<PopMusicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopMusicViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PopMusicViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}

class PopMusicViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

}
