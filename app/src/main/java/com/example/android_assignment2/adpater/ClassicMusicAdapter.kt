package com.example.android_assignment2.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_assignment2.R
import com.example.android_assignment2.models.classic.ClassicMusic
import javax.inject.Inject


class ClassicMusicAdapter @Inject constructor(
    private val iPreviewSongClick: IPreviewSongClick,
    private val classicMusicList: MutableList<ClassicMusic> = mutableListOf()
) : RecyclerView.Adapter<ClassicMusicViewHolder>() {

    fun updateClassicMusic(classicMusicList2: List<ClassicMusic>){
        classicMusicList.clear()
        classicMusicList.addAll(classicMusicList2)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassicMusicViewHolder {
        //Creating a variable to inflate the layout
        val classicMusicView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.artist_fragment, parent, false)

        //Returning the view holder class
        return ClassicMusicViewHolder(classicMusicView)
    }

    override fun onBindViewHolder(holder: ClassicMusicViewHolder, position: Int) {
        //Creating a variable to hold reference to the classicMusicModelList object
        val classic = classicMusicList[position]

        //Setting values to the view holder
        holder.artistName.text = classic.artistName
        holder.collectionName.text = classic.collectionName
        holder.artworkUrl60.text = classic.artworkUrl60
        holder.trackPrice.text = classic.trackPrice.toString()

        holder.previewSongUrl.setOnClickListener{
            iPreviewSongClick.previewSongClick(classic.previewUrl)
        }

        holder.itemView.setOnClickListener {
            iPreviewSongClick.previewSongClick(classic.previewUrl)
        }
    }

    override fun getItemCount(): Int =  classicMusicList.size

}

class ClassicMusicViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    //Here we are creating the variables to hold the views
    //We take those views from the 'itemView' using 'findViewById'
    val artistName: TextView = itemView.findViewById(R.id.artist_name)
    val collectionName: TextView = itemView.findViewById(R.id.collection_name)
    val artworkUrl60: TextView = itemView.findViewById(R.id.artwork_url_60)
    val trackPrice: TextView = itemView.findViewById(R.id.track_price)
    val previewSongUrl: TextView = itemView.findViewById(R.id.preview_song)
}