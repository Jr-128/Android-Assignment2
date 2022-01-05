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
import com.example.android_assignment2.adpater.ClassicMusicAdapter
import com.example.android_assignment2.adpater.IPreviewSongClick
import com.example.android_assignment2.databinding.FragmentClassicBinding
import com.example.android_assignment2.models.classic.ClassicMusic
import com.example.android_assignment2.presenters.ClassicMusicPresenter
import com.example.android_assignment2.presenters.IClassicMusicView
import javax.inject.Inject


class ClassicFragment : Fragment(), IClassicMusicView, IPreviewSongClick {

    @Inject
    lateinit var presenter: ClassicMusicPresenter

    private lateinit var binding: FragmentClassicBinding

    private lateinit var classicMusicAdapter: ClassicMusicAdapter

    override fun onAttach(context: Context) {

        super.onAttach(context)

        MusicApp.musicAppComponent.inject(this)


    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        presenter.initClassicPresenter(this)
        presenter.checkNetworkState()

        classicMusicAdapter = ClassicMusicAdapter(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentClassicBinding.inflate(inflater, container, false)

        binding.classicFragmentRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            adapter = classicMusicAdapter
        }

        binding.classicSwipe.setOnRefreshListener{
            Toast.makeText(context,"Refreshing classics...", Toast.LENGTH_SHORT).show()
            presenter.getClassicMusicFromServer()
            binding.classicSwipe.isRefreshing = false
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
            presenter.getClassicMusicFromServer()
    }

    //Method updates the classics into the recycler view
    //and saves the classics into the db
    override fun onSuccessMusicData(classicMusicList: List<ClassicMusic>) {
        // Here you add the logic to update the recycler view
        if (classicMusicList.isNotEmpty()) {
            classicMusicAdapter.updateClassicMusic(classicMusicList)
            presenter.saveClassicsToDb(classicMusicList)
            dataIsLoaded = true
            Log.d("success", "classic list not null saving data")
        }

    }

    override fun onErrorMusicData(error: Throwable) {
        //Here you add logic for showing the error to the user
        Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_LONG).show()
        Log.e("error", "onErrorMusicData error: " + error.stackTraceToString())
        presenter.getLocalClassics()

    }

    override fun onErrorNetwork() {
        Log.e("onErrorNetwork", "No network loading classics")
        presenter.getLocalClassics()
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
        fun newInstance() = ClassicFragment()
    }
}