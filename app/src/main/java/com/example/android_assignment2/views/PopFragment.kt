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
import com.example.android_assignment2.adpater.PopMusicAdapter
import com.example.android_assignment2.adpater.IPreviewSongClick
import com.example.android_assignment2.databinding.FragmentPopBinding
import com.example.android_assignment2.models.pop.PopMusic
import com.example.android_assignment2.presenters.PopMusicPresenter
import com.example.android_assignment2.presenters.IPopMusicView
import javax.inject.Inject


class PopFragment : Fragment(), IPopMusicView, IPreviewSongClick {

    @Inject
    lateinit var presenter: PopMusicPresenter

    private lateinit var binding: FragmentPopBinding

    private lateinit var popMusicAdapter: PopMusicAdapter

    override fun onAttach(context: Context) {

        super.onAttach(context)

        MusicApp.musicAppComponent.inject(this)


    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        presenter.initPopPresenter(this)
        presenter.checkNetworkState()

        popMusicAdapter = PopMusicAdapter(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPopBinding.inflate(inflater, container, false)

        binding.popFragmentRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            adapter = popMusicAdapter
        }

        binding.popSwipe.setOnRefreshListener{
            Toast.makeText(context,"Refreshing pops...", Toast.LENGTH_SHORT).show()
            presenter.getPopMusicFromServer()
            binding.popSwipe.isRefreshing = false
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
            presenter.getPopMusicFromServer()
    }

    //Method updates the pops into the recycler view
    //and saves the pops into the db
    override fun onSuccessMusicData(popMusicList: List<PopMusic>) {
        // Here you add the logic to update the recycler view
        if (popMusicList.isNotEmpty()) {
            popMusicAdapter.updatePopMusic(popMusicList)
            presenter.savePopsToDb(popMusicList)
            dataIsLoaded = true
            Log.d("success", "pop list not null saving data")
        }

    }

    override fun onErrorMusicData(error: Throwable) {
        //Here you add logic for showing the error to the user
        Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_LONG).show()
        Log.e("error", "onErrorMusicData error: " + error.stackTraceToString())
        presenter.getLocalPops()

    }

    override fun onErrorNetwork() {
        Log.e("onErrorNetwork", "No network loading Pops")
        presenter.getLocalPops()
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
        fun newInstance() = PopFragment()
    }
}