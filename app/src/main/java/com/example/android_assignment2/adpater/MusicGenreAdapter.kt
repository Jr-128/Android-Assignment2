package com.example.android_assignment2.adpater

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android_assignment2.views.ClassicFragment
import com.example.android_assignment2.views.PopFragment
import com.example.android_assignment2.views.RockFragment
import javax.inject.Inject

/**
 * This is the adapter to switch between fragment in the view pager
 *
 * @param fragmentManager We need to pass supportFragmentManager from the activity
 * @param lifecycle We need to pass the activity lifecycle to create the fragments
 */
class MusicGenreAdapter @Inject constructor(
    private val fragmentManager: FragmentManager,
    private val lifecycle : Lifecycle
    ): FragmentStateAdapter(fragmentManager, lifecycle){

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> ClassicFragment.newInstance()
            1-> RockFragment.newInstance()
            2 -> PopFragment.newInstance()
            else -> ClassicFragment.newInstance()
        }
    }
}
