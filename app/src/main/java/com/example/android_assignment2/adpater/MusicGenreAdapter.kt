package com.example.android_assignment2.adpater

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android_assignment2.views.ClassicFragment
import javax.inject.Inject


class MusicGenreAdapter @Inject constructor(
    val fragmentManager: FragmentManager,
    val lifecycle : Lifecycle
    ): FragmentStateAdapter(fragmentManager, lifecycle){

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        if(position == 0){
            return ClassicFragment.newInstance()
        }
        else
            return ClassicFragment.newInstance()
    }
}
