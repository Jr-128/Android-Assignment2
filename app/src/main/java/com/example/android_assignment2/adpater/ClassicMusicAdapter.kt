package com.example.android_assignment2.adpater

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import javax.inject.Inject


class ClassicMusicAdapter @Inject constructor(
    val fragmentManager: FragmentManager,
    val lifecycle : Lifecycle
    ): FragmentStateAdapter(fragmentManager, lifecycle){

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        TODO("Not yet implemented")
    }
}
