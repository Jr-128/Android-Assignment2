package com.example.android_assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_assignment2.adpater.FragmentsAdapter
import com.example.android_assignment2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MusicApp.musicAppComponent.inject(this)

        binding.myContainerViewPager2.adapter = FragmentsAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.myTabLayout, binding.myContainerViewPager2) { tab, position ->
            when(position){
                0 -> {
                    tab.text = "Classic"
                }
                1 -> {
                    tab.text = "Pop"
                }
                else -> {
                    tab.text = "Rock"
                }
            }
        }.attach()
    }
}