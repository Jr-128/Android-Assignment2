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
 * This is the fragments adapter that will be used to switch between them
 *
 * please refer to the example done in class
 */
class MusicGenreAdapter @Inject constructor(
    fragmentManager: FragmentManager,
    lifecycle : Lifecycle
): FragmentStateAdapter(fragmentManager, lifecycle){

//    override fun getItemCount() = 3
//
//    override fun createFragment(position: Int): Fragment {
//        if(position == 0){
//            return ClassicFragment.newInstance()
//        }
//        else
//            return ClassicFragment.newInstance()
//    }

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ClassicFragment.newInstance()
            1 -> RockFragment.newInstance()
            2 -> PopFragment.newInstance()
            else -> ClassicFragment.newInstance()
        }
    }
}
