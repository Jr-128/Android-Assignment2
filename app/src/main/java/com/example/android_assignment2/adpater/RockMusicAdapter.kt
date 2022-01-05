package com.example.android_assignment2.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_assignment2.R
import com.example.android_assignment2.models.rock.RockMusic
import javax.inject.Inject


class RockMusicAdapter @Inject constructor(
    private val iPreviewSongClick: IPreviewSongClick,
    private val rockMusicList: MutableList<RockMusic> = mutableListOf()
) : RecyclerView.Adapter<RockMusicViewHolder>() {

    fun updateRockMusic(rockMusicList2: List<RockMusic>){
        rockMusicList.clear()
        rockMusicList.addAll(rockMusicList2)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RockMusicViewHolder {
        //Creating a variable to inflate the layout
        val rockMusicView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.artist_fragment, parent, false)

        //Returning the view holder class
        return RockMusicViewHolder(rockMusicView)
    }

    override fun onBindViewHolder(holder: RockMusicViewHolder, position: Int) {
        //Creating a variable to hold reference to the rockMusicModelList object
        val rock = rockMusicList[position]

        //Setting values to the view holder
        holder.artistName.text = rock.artistName
        holder.collectionName.text = rock.collectionName
        holder.artworkUrl60.text = rock.artworkUrl60
        holder.trackPrice.text = rock.trackPrice.toString()

        holder.previewSongUrl.setOnClickListener{
            iPreviewSongClick.previewSongClick(rock.previewUrl)
        }

        holder.itemView.setOnClickListener {
            iPreviewSongClick.previewSongClick(rock.previewUrl)
        }
    }

    override fun getItemCount(): Int =  rockMusicList.size

}

class RockMusicViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    //Here we are creating the variables to hold the views
    //We take those views from the 'itemView' using 'findViewById'
    val artistName: TextView = itemView.findViewById(R.id.artist_name)
    val collectionName: TextView = itemView.findViewById(R.id.collection_name)
    val artworkUrl60: TextView = itemView.findViewById(R.id.artwork_url_60)
    val trackPrice: TextView = itemView.findViewById(R.id.track_price)
    val previewSongUrl: TextView = itemView.findViewById(R.id.preview_song)
}