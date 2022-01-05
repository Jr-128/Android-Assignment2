package com.example.android_assignment2.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_assignment2.MusicApp
import com.example.android_assignment2.adpater.RockMusicAdapter
import com.example.android_assignment2.adpater.IPreviewSongClick
import com.example.android_assignment2.databinding.FragmentRockBinding
import com.example.android_assignment2.models.rock.RockMusic
import com.example.android_assignment2.presenters.RockMusicPresenter
import com.example.android_assignment2.presenters.IRockMusicView
import javax.inject.Inject


class RockFragment : Fragment(), IRockMusicView, IPreviewSongClick {

    @Inject
    lateinit var presenter: RockMusicPresenter

    private lateinit var binding: FragmentRockBinding

    private lateinit var rockMusicAdapter: RockMusicAdapter

    override fun onAttach(context: Context) {

        super.onAttach(context)

        MusicApp.musicAppComponent.inject(this)


    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        presenter.initRockPresenter(this)
        presenter.checkNetworkState()

        rockMusicAdapter = RockMusicAdapter(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRockBinding.inflate(inflater, container, false)

        binding.rockFragmentRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            adapter = rockMusicAdapter
        }

        binding.rockSwipe.setOnRefreshListener{
            Toast.makeText(context,"Refreshing rocks...", Toast.LENGTH_SHORT).show()
            presenter.getRockMusicFromServer()
            binding.rockSwipe.isRefreshing = false
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDetach() {
        super.onDetach()

        dataIsLoaded = false
    }

    override fun onResume() {
        super.onResume()

        // I am calling the get characters method from presenter
        // this method will allow us to retrieve data from server
        // and the result will be posted into the view contract by the methods
        // character updated and onError data
        if (!dataIsLoaded)
            presenter.getRockMusicFromServer()
    }

    //Method updates the Rocks into the recycler view
    //and saves the Rocks into the db
    override fun onSuccessMusicData(rockMusicList: List<RockMusic>) {
        // Here you add the logic to update the recycler view
        if (rockMusicList.isNotEmpty()) {
            rockMusicAdapter.updateRockMusic(rockMusicList)
            presenter.saveRocksToDb(rockMusicList)
            dataIsLoaded = true
            Log.d("success", "rock list not null saving data")
        }

    }

    override fun onErrorMusicData(error: Throwable) {
        //Here you add logic for showing the error to the user
        Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_LONG).show()
        Log.e("error", "onErrorMusicData error: " + error.stackTraceToString())
        presenter.getLocalRocks()

    }

    override fun onErrorNetwork() {
        Log.e("onErrorNetwork", "No network loading rocks")
        presenter.getLocalRocks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //This method will clear the disposable from the presenter
        presenter.destroyPresenter()
    }

    override fun previewSongClick(previewUrl: String?) {
        val previewSongIntent: Intent = Intent().apply {
            action = Intent.ACTION_VIEW
            setDataAndType(Uri.parse(previewUrl), "audio/*")
        }
        try {
            startActivity(previewSongIntent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not find app to play song", Toast.LENGTH_LONG).show()
            Log.d("error", "previewSongClick Exception: Could not play song")
        }
    }

    companion object {
        private var dataIsLoaded: Boolean = false

        @JvmStatic
        fun newInstance() = RockFragment()
    }
}