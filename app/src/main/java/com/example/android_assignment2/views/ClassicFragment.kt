package com.example.android_assignment2.views

import android.content.Context
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
import com.example.android_assignment2.models.classic.ClassicMusicModel
import com.example.android_assignment2.presenters.ClassicMusicPresenter
import com.example.android_assignment2.presenters.IClassicMusicView
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [ClassicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClassicFragment : Fragment(), IClassicMusicView, IPreviewSongClick {

    @Inject
    lateinit var presenter: ClassicMusicPresenter

    lateinit var binding: FragmentClassicBinding

    lateinit var classicMusicAdapter: ClassicMusicAdapter

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
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        // I am calling the get characters method from presenter
        // this method will allow us to retrieve data from server
        // and the result will be posted into the view contract by the methods
        // character updated and onError data
        presenter.getClassicMusicFromServer()
    }


    override fun onSuccessMusicData(classicMusicList: ClassicMusicModel) {
        // Here you add the logic to update the recycler view
        classicMusicAdapter.updateClassicMusic(classicMusicList)

    }

    override fun onErrorMusicData(error: Throwable) {
        //Here you add logic for showing the error to the user
        Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_LONG).show()
        Log.e("error", error.stackTraceToString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //This method will clear the disposable from the presenter
        presenter.destroyPresenter()
    }

    override fun previewSongClick(previewUrl: String) {
        TODO("Not yet implemented")
    }

    companion object {
        @JvmStatic
        fun newInstance() = ClassicFragment()
    }
}