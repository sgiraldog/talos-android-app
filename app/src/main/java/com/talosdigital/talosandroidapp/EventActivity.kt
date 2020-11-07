package com.talosdigital.talosAndroidApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.talosdigital.talosAndroidApp.models.getEventsResponse.Event
import kotlinx.android.synthetic.main.event.*
import java.text.SimpleDateFormat

class EventActivity: AppCompatActivity() {
    lateinit var event: Event


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event)
        getData()
        bindView()
    }

    fun getData() {
        val gson = Gson()
        val strObj = intent.getStringExtra("event")
        this.event = gson.fromJson(strObj, Event::class.java)

    }

    fun bindView() {
        var days = ""
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val formatter = SimpleDateFormat("E, dd MMM yyyy hh:mm a")

        if (event.days.isEmpty()) {
            days = formatter.format(parser.parse(event.startDate))
        } else {
            for (day in event.days) {
                days += "$day "
            }
        }

        if(event.accessLink.isNotEmpty()){
            linkTextView.text = event.accessLink
        }

        if(event.registerLink.isNotEmpty()){
            registerTextView.text = event.registerLink
        }

        if(event.imageLink.isNotEmpty()) Picasso.get().load(event.imageLink).into(eventImageView);
        eventNameTextView.text = event.name
        scheduleTextView.text = days
        descriptionTextView.text = event.description



    }

}