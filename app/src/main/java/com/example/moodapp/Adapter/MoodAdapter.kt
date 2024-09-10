package com.example.moodapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.moodapp.Model.MoodModel
import com.example.moodapp.databinding.MoodItemBinding

class MoodAdapter(var context: Context, var list: ArrayList<MoodModel>) : RecyclerView.Adapter<MoodAdapter.ViewHolder>() {
  inner class ViewHolder(var binding: MoodItemBinding):RecyclerView.ViewHolder(binding.root){

  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MoodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var mood = list[position]
        holder.binding.text.text=mood.name
        holder.binding.emoji.setImageResource(mood.emoji)
        holder.binding.cv.setCardBackgroundColor(mood.cardColor)
    }

    override fun getItemCount(): Int {
     return list.size
    }



}
