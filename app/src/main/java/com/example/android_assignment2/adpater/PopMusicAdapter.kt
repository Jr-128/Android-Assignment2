package com.example.android_assignment2.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_assignment2.R
import com.example.android_assignment2.models.pop.PopMusic
import javax.inject.Inject


class PopMusicAdapter @Inject constructor(
    private val iPreviewSongClick: IPreviewSongClick,
    private val popMusicList: MutableList<PopMusic> = mutableListOf()
) : RecyclerView.Adapter<PopMusicViewHolder>() {

    fun updatePopMusic(popMusicList2: List<PopMusic>){
        popMusicList.clear()
        popMusicList.addAll(popMusicList2)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopMusicViewHolder {
        //Creating a variable to inflate the layout
        val popMusicView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.artist_fragment, parent, false)

        //Returning the view holder class
        return PopMusicViewHolder(popMusicView)
    }

    override fun onBindViewHolder(holder: PopMusicViewHolder, position: Int) {
        //Creating a variable to hold reference to the popMusicModelList object
        val pop = popMusicList[position]

        //Setting values to the view holder
        holder.artistName.text = pop.artistName
        holder.collectionName.text = pop.collectionName
        holder.artworkUrl60.text = pop.artworkUrl60
        holder.trackPrice.text = pop.trackPrice.toString()

        holder.previewSongUrl.setOnClickListener{
            iPreviewSongClick.previewSongClick(pop.previewUrl)
        }

        holder.itemView.setOnClickListener {
            iPreviewSongClick.previewSongClick(pop.previewUrl)
        }
    }

    override fun getItemCount(): Int =  popMusicList.size

}

class PopMusicViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    //Here we are creating the variables to hold the views
    //We take those views from the 'itemView' using 'findViewById'
    val artistName: TextView = itemView.findViewById(R.id.artist_name)
    val collectionName: TextView = itemView.findViewById(R.id.collection_name)
    val artworkUrl60: TextView = itemView.findViewById(R.id.artwork_url_60)
    val trackPrice: TextView = itemView.findViewById(R.id.track_price)
    val previewSongUrl: TextView = itemView.findViewById(R.id.preview_song)
}