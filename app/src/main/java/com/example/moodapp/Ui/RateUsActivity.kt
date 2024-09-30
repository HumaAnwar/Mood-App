package com.example.moodapp.Ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import com.example.moodapp.R

class RateUsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_us)

        val rateButton = findViewById<Button>(R.id.rateButton)

        // Set up button click listener
        rateButton.setOnClickListener {
            // Always open Play Store with the specific app link
            openPlayStore()
        }
    }

    private fun openPlayStore() {
        val uri = Uri.parse("market://details?id=com.tencent.ig")
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        try {
            startActivity(goToMarket)
        } catch (e: Exception) {
            // If Play Store is unavailable, open in browser
            val browserUri = Uri.parse("https://play.google.com/store/apps/details?id=com.tencent.ig&pcampaignid=web_share")
            startActivity(Intent(Intent.ACTION_VIEW, browserUri))
        }
    }
}
