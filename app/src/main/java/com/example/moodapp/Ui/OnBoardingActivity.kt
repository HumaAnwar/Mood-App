package com.example.moodapp.Ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.moodapp.Adapter.OnBoardingAdapter
import com.example.moodapp.MainActivity
import com.example.moodapp.Model.Page
import com.example.moodapp.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingActivity : AppCompatActivity() {
        private val onboardingpagechange = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0, 1, 2 -> {
                        nextbtn.visible()
                        skipbtn.visible()
                    }
                }
            }
        }

        private val pagerlist = arrayListOf(
            Page("Order Your Tasty Meals", R.drawable.in_love, "Explore a variety of delicious meals \n and order your favorites with ease \n from our app"),
            Page("Swift Delivery", R.drawable.inspired, "Swift Delivery ensures your meals \n arrive quickly and fresh, straight \n to your doorstep."),
            Page("To your Doorstep", R.drawable.energized, "Enjoy quick and reliable delivery \n service bringing your meals right \n to your home.")
        )

        lateinit var onviewpager: ViewPager2
        lateinit var skipbtn: Button
        lateinit var nextbtn: Button

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            // Check if the user has already seen the onboarding screens
            val sharedPref = getSharedPreferences("OnboardingPrefs", MODE_PRIVATE)
            val isFirstTime = sharedPref.getBoolean("isFirstTime", true)

            if (!isFirstTime) {
                // Skip the onboarding if already seen
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

            setContentView(R.layout.activity_on_boarding)

            // Initialize views
            onviewpager = findViewById(R.id.viewpager)
            nextbtn = findViewById(R.id.next)
            skipbtn = findViewById(R.id.skip)

            // Set up ViewPager2 and adapter
            onviewpager.apply {
                adapter = OnBoardingAdapter(this@OnBoardingActivity, pagerlist)
                registerOnPageChangeCallback(onboardingpagechange)
                (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            }

            // Set up TabLayout
            val tablayout = findViewById<TabLayout>(R.id.tablayout)
            TabLayoutMediator(tablayout, onviewpager) { _, _ -> }.attach()

            // Next button click listener
            nextbtn.setOnClickListener {
                if (onviewpager.currentItem < onviewpager.adapter!!.itemCount - 1) {
                    onviewpager.currentItem += 1
                } else {
                    completeOnboarding()
                }
            }

            // Skip button click listener
            skipbtn.setOnClickListener {
                completeOnboarding()
            }
        }

        // Function to complete the onboarding and update SharedPreferences
        private fun completeOnboarding() {
            val sharedPref = getSharedPreferences("OnboardingPrefs", MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putBoolean("isFirstTime", false)
            editor.apply()  // Save the value

            // Go to MainActivity after onboarding is completed or skipped
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        override fun onDestroy() {
            onviewpager.unregisterOnPageChangeCallback(onboardingpagechange)
            super.onDestroy()
        }

        // Extension function to make a view visible
        fun View.visible() {
            visibility = View.VISIBLE
        }
    }
