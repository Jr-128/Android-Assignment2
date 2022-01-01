package com.example.android_assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import com.example.android_assignment2.adpater.MusicGenreAdapter
import com.example.android_assignment2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.myContainerViewPager2.adapter = MusicGenreAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.myTabLayout, binding.myContainerViewPager2)
        { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Classic"
                    tab.icon = getDrawable(R.drawable.ic_launcher_foreground)
                }
                1 -> {
                    tab.text = "Rock"
                    tab.icon = getDrawable(R.drawable.ic_launcher_foreground)
                }
                else -> {
                    tab.text = "Pop"
                    tab.icon = getDrawable(R.drawable.ic_launcher_foreground)
                }
            }
        }.attach()
    }
}