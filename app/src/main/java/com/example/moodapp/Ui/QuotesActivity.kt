package com.example.moodapp.Ui

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moodapp.MainActivity
import com.example.moodapp.R
import com.example.moodapp.databinding.ActivityQuotesBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener

import com.google.android.material.card.MaterialCardView
import java.util.Locale
class QuotesActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var binding: ActivityQuotesBinding
    private val db = Firebase.firestore
    private lateinit var textToSpeech: TextToSpeech
    private var isPlaying = false
    private var currentText = ""
    private var currentMoodName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize TextToSpeech
        textToSpeech = TextToSpeech(this, this)

        // Retrieve mood name and card color from the Intent
        val moodName = intent.getStringExtra("MOOD_NAME")
        val backgroundColor = intent.getIntExtra("CARD_COLOR", ContextCompat.getColor(this, R.color.red))

        if (moodName != null) {
            currentMoodName = moodName
            binding.name.text = moodName
            binding.namedetail.text = "I'm Feeling $moodName"

            // Set the background color of the main layout based on the passed value
            binding.root.setBackgroundColor(backgroundColor)

            // Fetch the quote from Firestore
            getQuoteFromFirestore(moodName)
        }

        // Handle back button click
        binding.backarrow.setOnClickListener {
            startActivity(Intent(this@QuotesActivity, MainActivity::class.java))
        }

        // Handle play/pause button click
        binding.playPauseButton.setOnClickListener {
            if (isPlaying) {
                textToSpeech.stop()
                binding.playPauseButton.setImageResource(R.drawable.baseline_play_arrow_24)
                binding.playPauseButton.setColorFilter(ContextCompat.getColor(this, R.color.red))
            } else {
                speakText(currentText)
                binding.playPauseButton.setImageResource(R.drawable.baseline_pause_circle_filled_24)
                binding.playPauseButton.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }
            isPlaying = !isPlaying
        }
    }

    private fun getQuoteFromFirestore(moodName: String) {
        val moodCollectionRef = db.collection("Mood")
        moodCollectionRef.document(moodName).get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val ayat = document.getString("ayat")
                    val translation = document.getString("translation")
                    val emojiResId = getEmojiResourceId(moodName) // Get the emoji resource ID

                    binding.ayat.text = ayat ?: "No quote found for this mood."
                    binding.translation.text = translation ?: "No translation found for this mood."
                    binding.moodemoji.setImageResource(emojiResId) // Set the emoji

                    currentText = "${binding.ayat.text} ${binding.translation.text}"
                } else {
                    binding.ayat.text = "Document does not exist."
                    binding.translation.text = ""
                    binding.moodemoji.setImageResource(R.drawable.cartoon   ) // Set a default emoji if needed
                    currentText = ""
                }
            }
            .addOnFailureListener { exception ->
                binding.ayat.text = "Failed to retrieve quote: ${exception.message}"
                binding.translation.text = ""
                binding.moodemoji.setImageResource(R.drawable.cartoon) // Set a default emoji if needed
                currentText = ""
            }
    }

    private fun getEmojiResourceId(moodName: String): Int {
        return when (moodName.lowercase()) {
            "desire" -> R.drawable.desire
            "happy" -> R.drawable.happy
            "headache" -> R.drawable.headache
            "cool" -> R.drawable.cool
            // Add other mood cases here
            else -> R.drawable.cartoon // Default emoji
        }
    }

    private fun speakText(text: String) {
        if (text.isNotEmpty()) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale.getDefault())
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Handle language data not available or language not supported
            }
        } else {
            // Initialization failed
        }
    }

    override fun onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }
}

