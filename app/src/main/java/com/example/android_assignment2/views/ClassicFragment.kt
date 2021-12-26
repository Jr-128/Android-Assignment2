package com.example.android_assignment2.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.android_assignment2.MusicApp
import com.example.android_assignment2.R
import com.example.android_assignment2.models.classic.ClassicMusic
import com.example.android_assignment2.presenters.ClassicMusicPresenter
import com.example.android_assignment2.presenters.IClassicMusicPresenter
import com.example.android_assignment2.presenters.IClassicMusicView
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ClassicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClassicFragment : Fragment(), IClassicMusicView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //Here we are injecting our presenter
    @Inject
    lateinit var presenter: ClassicMusicPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        MusicApp.musicAppComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.initClassicPresenter(this)
        presenter.checkNetworkState()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_classic, container, false)
    }

    override fun onResume() {
        super.onResume()

        // I am calling the get characters method from presenter
        // this method will allow us to retrieve data from server
        // and the result will be posted into the view contract by the methods
        // character updated and onError data
        presenter.getClassicMusicFromServer()
    }


    override fun onSuccessMusicData(classicMusicList: List<ClassicMusic>) {
        // Here you add the logic to update the recycler view
        Toast.makeText(requireContext(), classicMusicList[0].artistName, Toast.LENGTH_LONG).show()
        Log.d("CharactersFragment", classicMusicList.toString())
    }

    override fun onErrorMusicData(error: Throwable) {
        // here you add logic for showing the error to the user
        Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_LONG).show()
        Log.e("ClassicFragment", error.stackTraceToString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // this method will clear the disposable from the presenter
        presenter.destroyPresenter()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ClassicFragment()
    }
}