package com.example.moodapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.moodapp.Model.Page
import com.example.moodapp.R

class IntroFragment(val pager: Page) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_intro, container, false)

        var image=view.findViewById<ImageView>(R.id.img)
        var title=view.findViewById<TextView>(R.id.title)
        var dis=view.findViewById<TextView>(R.id.discription)

        image.setImageResource(pager.image)
        title.text=pager.title
        dis.text=pager.dis
        return view
    }
}