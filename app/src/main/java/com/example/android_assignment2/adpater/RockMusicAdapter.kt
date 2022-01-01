package com.example.android_assignment2.adpater

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class RockMusicAdapter @Inject constructor(
    private val iPreviewSongClick: IPreviewSongClick
) : RecyclerView.Adapter<RockMusicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RockMusicViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RockMusicViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}

class RockMusicViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

}
