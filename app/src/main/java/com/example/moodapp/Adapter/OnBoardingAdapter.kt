package com.example.moodapp.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moodapp.Fragment.IntroFragment
import com.example.moodapp.Model.Page


class OnBoardingAdapter(activity: FragmentActivity, private val pagerlist:ArrayList<Page>):
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return pagerlist.size
    }

    override fun createFragment(position: Int): Fragment {
        return IntroFragment(pagerlist[position])
    }
}