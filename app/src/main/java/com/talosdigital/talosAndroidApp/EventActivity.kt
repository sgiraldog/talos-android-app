package com.talosdigital.talosAndroidApp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.talosdigital.talosAndroidApp.models.getEventsResponse.Event
import kotlinx.android.synthetic.main.activity_event.*
import java.text.SimpleDateFormat

class EventActivity: AppCompatActivity() {
    lateinit var event: Event


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
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
            joinMeetingBtn.visibility = View.VISIBLE
        }

        if(event.registerLink.isNotEmpty()){
            registerBtn.visibility = View.VISIBLE
        }
        if(event.imageLink.isNotEmpty()) Picasso.get().load(event.imageLink).into(eventImageView);
        eventNameTextView.text = event.name
        scheduleTextView.text = days
        descriptionTextView.text = event.description
    }

    fun onRegister(view: View) {
        val uri = Uri.parse(event.registerLink)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    fun onJoin(view: View) {
        val uri = Uri.parse(event.accessLink)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

}