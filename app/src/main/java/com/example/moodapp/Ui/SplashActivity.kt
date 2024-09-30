package com.example.moodapp.Ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moodapp.R
// SplashActivity.kt
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.os.Handler
import android.os.Looper

import android.view.animation.LinearInterpolator
import android.widget.ImageView
import com.example.moodapp.MainActivity
import com.example.moodapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
   private lateinit var binding: ActivitySplashBinding
    private val SPLASH_DELAY: Long = 3000 // 3-second delay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sunImage=binding.imglogo


        // Create zoom-in animation for the sun petals
        val zoomInAnimation = ObjectAnimator.ofPropertyValuesHolder(
            sunImage,
            PropertyValuesHolder.ofFloat("scaleX", 1f, 1.2f), // Scale from 1 to 1.2 (20% larger)
            PropertyValuesHolder.ofFloat("scaleY", 1f, 1.2f)  // Scale from 1 to 1.2 (20% larger)
        )
        zoomInAnimation.duration = 1000 // 1 second for zoom-in effect
        zoomInAnimation.repeatCount = ObjectAnimator.INFINITE
        zoomInAnimation.repeatMode = ObjectAnimator.REVERSE // Reverse the animation back to original size
        zoomInAnimation.start()

        // Use Handler to delay the splash screen
        Handler(Looper.getMainLooper()).postDelayed({
            // Navigate to the main activity after the delay
            val intent = Intent(this@SplashActivity, OnBoardingActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_DELAY)
    }
}
