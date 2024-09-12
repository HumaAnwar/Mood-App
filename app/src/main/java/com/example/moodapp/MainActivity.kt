

package com.example.moodapp

import android.content.Intent


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moodapp.Adapter.MoodAdapter
import com.example.moodapp.Model.MoodModel
import com.example.moodapp.Ui.QuotesActivity
import com.example.moodapp.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity(), MoodAdapter.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    val list = ArrayList<MoodModel>()
    var db=Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val whiteColor = ContextCompat.getColor(this, R.color.black) // Use your color resource ID
        list.add(MoodModel(R.drawable.desire, "Desire", whiteColor))

        val redColor = ContextCompat.getColor(this, R.color.red)
        list.add(MoodModel(R.drawable.worry, "Worried", redColor))

        val orange = ContextCompat.getColor(this, R.color.orange)
        list.add(MoodModel(R.drawable.cool, "Cool", orange))

        val lgreenColor = ContextCompat.getColor(this, R.color.lightgreen)
        list.add(MoodModel(R.drawable.happy, "Happy",lgreenColor))

        val pinkColor = ContextCompat.getColor(this, R.color.pink)
        list.add(MoodModel(R.drawable.excited, "Excited",pinkColor))



        val yellowColor = ContextCompat.getColor(this, R.color.yeloow)
        list.add(MoodModel(R.drawable.amazed, "Amazed",yellowColor))

        val dpurple = ContextCompat.getColor(this, R.color.dpurple)
        list.add(MoodModel(R.drawable.realx, "Relaxed",dpurple))

        val lred = ContextCompat.getColor(this, R.color.lred)
        list.add(MoodModel(R.drawable.joyful, "Joyful",lred))

        val parrotColor = ContextCompat.getColor(this, R.color.parrot)
        list.add(MoodModel(R.drawable.headache, "Headache",parrotColor))


        val brown = ContextCompat.getColor(this, R.color.brown)
        list.add(MoodModel(R.drawable.adventure, "Adventurous",brown))

        val purple = ContextCompat.getColor(this, R.color.lpurple)
        list.add(MoodModel(R.drawable.enthusiastic, "Enthusiastic",purple))

        val dblue = ContextCompat.getColor(this, R.color.dblue)
        list.add(MoodModel(R.drawable.anxious, "Anxious",dblue))

        list.add(MoodModel(R.drawable.in_love, "Calm"))
        list.add(MoodModel(R.drawable.in_love, "Energized"))
        list.add(MoodModel(R.drawable.in_love, "Content"))
        list.add(MoodModel(R.drawable.in_love, "Hopeful"))
        list.add(MoodModel(R.drawable.in_love, "Inspired"))
        list.add(MoodModel(R.drawable.in_love, "Cheerful"))
        list.add(MoodModel(R.drawable.in_love, "Playful"))
        list.add(MoodModel(R.drawable.in_love, "Motivated"))
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



        binding.rv.layoutManager=GridLayoutManager(this,3)
       binding.rv.adapter= MoodAdapter(this, list,this@MainActivity)


        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterCourses(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }




private fun filterCourses(query: String) {
    val filteredList = list.filter { mood ->
        mood.name?.contains(query, ignoreCase = true) ?: false
    }
    binding.rv.adapter= MoodAdapter(this, filteredList,this@MainActivity)
}

    override fun OnItemClick(moodName: String, cardColor: Int) {
            val intent = Intent(this, QuotesActivity::class.java)
            intent.putExtra("MOOD_NAME", moodName)
            intent.putExtra("CARD_COLOR", cardColor)
            startActivity(intent)
        }

    }
