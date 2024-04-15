package com.example.movieapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MoviePageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        val fragment = HomeTabFragment()
        val recommendationType = RecommendationType.entries[position].toString()
        fragment.arguments = Bundle().apply {
            putString(ARG_RECOMMENDATION_TYPE, recommendationType)
        }
        return fragment
    }
}