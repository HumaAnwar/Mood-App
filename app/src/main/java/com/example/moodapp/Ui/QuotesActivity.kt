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

    // List to hold ayats and translations
    private var ayatList = listOf<String>()
    private var translationList = listOf<String>()
    private var currentIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize TextToSpeech
        textToSpeech = TextToSpeech(this, this)

        // Retrieve mood name and card color from the Intent
        val moodName = intent.getStringExtra("MOOD_NAME")
        val backgroundColor =
            intent.getIntExtra("CARD_COLOR", ContextCompat.getColor(this, R.color.red))

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
        // Handle forward button click
        binding.frwrd.setOnClickListener {
            if (currentIndex < ayatList.size - 1) {
                currentIndex++
                updateUI()
            }
        }

        // Handle back button click for previous ayat
        binding.back.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                updateUI()
            }
        }
    }

    private fun getQuoteFromFirestore(moodName: String) {
        val moodCollectionRef = db.collection("Mood")
        moodCollectionRef.document(moodName).get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    // Retrieve ayats and translations as lists
                    ayatList = document.get("ayats") as List<String>? ?: listOf()
                    translationList = document.get("translations") as List<String>? ?: listOf()

                    // Check if the lists are not empty
                    if (ayatList.isNotEmpty() && translationList.isNotEmpty()) {
                        updateUI() // Update the UI with the first ayat and translation
                    } else {
                        binding.ayat.text = "No ayats found for this mood."
                        binding.translation.text = "No translations found for this mood."
                        binding.moodemoji.setImageResource(R.drawable.cartoon) // Set a default emoji
                    }

                } else {
                    // Document does not exist
                    binding.ayat.text = "Document does not exist."
                    binding.translation.text = ""
                    binding.moodemoji.setImageResource(R.drawable.cartoon) // Set a default emoji
                }
            }
            .addOnFailureListener { exception ->
                // Handle the error if Firestore retrieval fails
                binding.ayat.text = "Failed to retrieve ayats: ${exception.message}"
                binding.translation.text = ""
                binding.moodemoji.setImageResource(R.drawable.cartoon) // Set a default emoji
            }
    }

    // Updates the UI to display the current ayat and translation
    private fun updateUI() {
        binding.ayat.text = ayatList[currentIndex]
        binding.translation.text = translationList[currentIndex]

        // Update the current text to be spoken (i.e., the current translation)
        currentText = translationList[currentIndex]
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