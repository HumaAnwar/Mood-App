

package com.example.moodapp

import com.example.moodapp.R


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.moodapp.Adapter.MoodAdapter
import com.example.moodapp.Model.MoodModel
import com.example.moodapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var list=ArrayList<MoodModel>()
        val whiteColor = ContextCompat.getColor(this, R.color.black) // Use your color resource ID
        list.add(MoodModel(R.drawable.in_love, "Desire", whiteColor))
        val redColor = ContextCompat.getColor(this, R.color.red)
        list.add(MoodModel(R.drawable.img_2,"Worried",redColor))
        val purpleColor = ContextCompat.getColor(this, R.color.purple)
        list.add(MoodModel(R.drawable.cartoon,"Cool",purpleColor))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))
        list.add(MoodModel(R.drawable.in_love,"Desire"))

        binding.rv.layoutManager=GridLayoutManager(this,3)
       binding.rv.adapter= MoodAdapter(this, list)

    }}