package com.talosdigital.talosAndroidApp

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.talosdigital.talosAndroidApp.models.getEventsResponse.Event
import com.talosdigital.talosAndroidApp.utils.ListOnClickListener
import com.talosdigital.talosAndroidApp.utils.inflate


class EventsAdapter(private val events: List<Event>, private val listener: ListOnClickListener): RecyclerView.Adapter<EventsAdapter.EventItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsAdapter.EventItemHolder {
        val inflatedView = parent.inflate(R.layout.event_list_item, false)
        return EventItemHolder(inflatedView, listener)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: EventsAdapter.EventItemHolder, position: Int) {
        holder.titleText.text = events[position].name
        holder.scheduleText.text = events[position].author
        if(events[position].imageLink.isNotEmpty()) Picasso.get().load(events[position].imageLink).into(holder.eventImage);
        holder.index = position
    }

    class EventItemHolder(v: View, val listener: ListOnClickListener) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        var index: Int = 0
        val eventImage = view.findViewById<ImageView>(R.id.eventImageView)
        val titleText = view.findViewById<TextView>(R.id.titleTextView)
        val scheduleText = view.findViewById<TextView>(R.id.scheduleTextView)

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            listener.onViewClick(index)
        }



    }
}