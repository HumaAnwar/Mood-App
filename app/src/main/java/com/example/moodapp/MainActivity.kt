

package com.example.moodapp

import android.net.Uri


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moodapp.Adapter.MoodAdapter
import com.example.moodapp.Model.MoodModel
import com.example.moodapp.Ui.OnBoardingActivity
import com.example.moodapp.Ui.QuotesActivity
import com.example.moodapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MoodAdapter.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<MoodModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if onboarding is shown before using SharedPreferences
        val sharedPref = getSharedPreferences("OnboardingPrefs", MODE_PRIVATE)
        val isFirstTime = sharedPref.getBoolean("isFirstTime", true)

        if (isFirstTime) {
            // Launch OnBoardingActivity if it's the first time the app is opened
            startActivity(Intent(this, OnBoardingActivity::class.java))

            // Set the flag to false so onboarding isn't shown again
            val editor = sharedPref.edit()
            editor.putBoolean("isFirstTime", false)
            editor.apply()

            // Close MainActivity
            finish()
        } else {
            // Proceed with MainActivity content if not first time
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)



            // Initialize the mood list
            initializeMoodList()

            // Set up RecyclerView
            binding.rv.layoutManager = GridLayoutManager(this, 3)
            binding.rv.adapter = MoodAdapter(this, list, this@MainActivity)

            // Set up search functionality
            binding.search.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    filterCourses(s.toString())
                }

                override fun afterTextChanged(s: Editable?) {}
            })
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


    private fun initializeMoodList() {

        val whiteColor = ContextCompat.getColor(this, R.color.black) // Use your color resource ID
        list.add(MoodModel(R.drawable.desire, "Desire", whiteColor))

        val redColor = ContextCompat.getColor(this, R.color.red)
        list.add(MoodModel(R.drawable.worry, "Worried", redColor))

        val orange = ContextCompat.getColor(this, R.color.orange)
        list.add(MoodModel(R.drawable.cool, "Cool", orange))

                val lgreenColor = ContextCompat.getColor(this, R.color.lightgreen)
                list.add(MoodModel(R.drawable.happy, "Happy", lgreenColor))

                val pinkColor = ContextCompat.getColor(this, R.color.pink)
                list.add(MoodModel(R.drawable.excited, "Excited", pinkColor))


                val yellowColor = ContextCompat.getColor(this, R.color.yeloow)
                list.add(MoodModel(R.drawable.amazed, "Amazed", yellowColor))

                val dpurple = ContextCompat.getColor(this, R.color.dpurple)
                list.add(MoodModel(R.drawable.realx, "Relaxed", dpurple))

                val lred = ContextCompat.getColor(this, R.color.lred)
                list.add(MoodModel(R.drawable.joyful, "Joyful", lred))


                val dark = ContextCompat.getColor(this, R.color.dark)
                list.add(MoodModel(R.drawable.calm, "Calm", dark))

                val parrotColor = ContextCompat.getColor(this, R.color.parrot)
                list.add(MoodModel(R.drawable.headache, "Headache", parrotColor))


                val brown = ContextCompat.getColor(this, R.color.brown)
                list.add(MoodModel(R.drawable.adventure, "Adventurous", brown))

                val purple = ContextCompat.getColor(this, R.color.lpurple)
                list.add(MoodModel(R.drawable.enthusiastic, "Enthusiastic", purple))

                val dblue = ContextCompat.getColor(this, R.color.dblue)
                list.add(MoodModel(R.drawable.anxious, "Anxious", dblue))

                val light = ContextCompat.getColor(this, R.color.lighred)
                list.add(MoodModel(R.drawable.energized, "Energized", light))


                val darkk = ContextCompat.getColor(this, R.color.darkgr)
                list.add(MoodModel(R.drawable.hope, "Hopeful", darkk))


                val blue = ContextCompat.getColor(this, R.color.darkb)
                list.add(MoodModel(R.drawable.inspired, "Inspired"))


                val pur = ContextCompat.getColor(this, R.color.lightp)
                list.add(MoodModel(R.drawable.cheerful, "Cheerful", pur))


                val dar = ContextCompat.getColor(this, R.color.darred)
                list.add(MoodModel(R.drawable.play, "Playful", dar))


                val pup = ContextCompat.getColor(this, R.color.pup)
                list.add(MoodModel(R.drawable.motive, "Motivated", pup))



                list.add(MoodModel(R.drawable.in_love, "Grateful"))
                list.add(MoodModel(R.drawable.in_love, "Proud"))
                list.add(MoodModel(R.drawable.in_love, "Serene"))
                list.add(MoodModel(R.drawable.in_love, "Blissful"))
                list.add(MoodModel(R.drawable.in_love, "Curious"))
                list.add(MoodModel(R.drawable.in_love, "Optimistic"))
                list.add(MoodModel(R.drawable.in_love, "Confident"))
                list.add(MoodModel(R.drawable.in_love, "Elated"))
                list.add(MoodModel(R.drawable.in_love, "Affectionate"))
                list.add(MoodModel(R.drawable.in_love, "Relaxed"))
                list.add(MoodModel(R.drawable.in_love, "Peaceful"))
                list.add(MoodModel(R.drawable.in_love, "Friendly"))


                list.add(MoodModel(R.drawable.in_love, "Loving"))
                list.add(MoodModel(R.drawable.in_love, "Amused"))
                list.add(MoodModel(R.drawable.in_love, "Appreciative"))
                list.add(MoodModel(R.drawable.in_love, "Satisfied"))
                list.add(MoodModel(R.drawable.in_love, "Ecstatic"))



                list.add(MoodModel(R.drawable.in_love, "Empowered"))
                list.add(MoodModel(R.drawable.in_love, "Determined"))
                list.add(MoodModel(R.drawable.in_love, "Comforted"))
                list.add(MoodModel(R.drawable.in_love, "Nostalgic"))
                list.add(MoodModel(R.drawable.in_love, "Ambitious"))
                list.add(MoodModel(R.drawable.in_love, "Encouraged"))
                list.add(MoodModel(R.drawable.in_love, "Resilient"))
                list.add(MoodModel(R.drawable.in_love, "Hopeful"))
                list.add(MoodModel(R.drawable.in_love, "Reflective"))
                list.add(MoodModel(R.drawable.in_love, "Sympathetic"))
                list.add(MoodModel(R.drawable.in_love, "Surprised"))
                list.add(MoodModel(R.drawable.in_love, "Charmed"))
                list.add(MoodModel(R.drawable.in_love, "Enlightened"))
                list.add(MoodModel(R.drawable.in_love, "Serendipitous"))
                list.add(MoodModel(R.drawable.in_love, "Zealous"))
                list.add(MoodModel(R.drawable.in_love, "Overjoyed"))
                list.add(MoodModel(R.drawable.in_love, "Fascinated"))
                list.add(MoodModel(R.drawable.in_love, "Empathetic"))
                list.add(MoodModel(R.drawable.in_love, "Fulfilled"))
                list.add(MoodModel(R.drawable.in_love, "Gracious"))
                list.add(MoodModel(R.drawable.in_love, "Vibrant"))



                binding.rv.layoutManager = GridLayoutManager(this, 3)
                binding.rv.adapter = MoodAdapter(this, list, this@MainActivity)


                binding.search.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        filterCourses(s.toString())
                    }

                    override fun afterTextChanged(s: Editable?) {}
                })
            }
    private fun filterCourses(query: String) {
        val filteredList = list.filter { mood ->
            mood.name?.contains(query, ignoreCase = true) ?: false
        }
        binding.rv.adapter = MoodAdapter(this, filteredList, this@MainActivity)
    }
    override fun onBackPressed() {
        // Only show the exit dialog, don't call super.onBackPressed() so that the activity doesn't finish automatically
        showCustomExitDialog()
    }

    private fun showCustomExitDialog() {
        val dialogView: View = LayoutInflater.from(this).inflate(R.layout.exitdialoge, null)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        // Handle buttons inside the dialog
        val btnRate: Button = dialogView.findViewById(R.id.rateButton)
        val btnExit: Button = dialogView.findViewById(R.id.exitbtn)

        btnRate.setOnClickListener {
            openPlayStore()
            dialog.dismiss()
        }

        btnExit.setOnClickListener {
            finishAffinity() // Closes the app
            dialog.dismiss()
        }

        // Show the dialog first
        dialog.show()

        // Then set the layout parameters to make it full screen
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }


    override fun OnItemClick(moodName: String, cardColor: Int) {
        val intent = Intent(this, QuotesActivity::class.java)
        intent.putExtra("MOOD_NAME", moodName)
        intent.putExtra("CARD_COLOR", cardColor)
        startActivity(intent)
    }

}











