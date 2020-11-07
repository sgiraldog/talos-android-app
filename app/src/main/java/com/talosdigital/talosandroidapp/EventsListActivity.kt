package com.talosdigital.talosAndroidApp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.VolleyError
import com.google.gson.Gson
import com.talosdigital.talosAndroidApp.models.getEventsResponse.Event
import com.talosdigital.talosAndroidApp.models.getEventsResponse.GetEventsResponse
import com.talosdigital.talosAndroidApp.utils.ApiResultListener
import com.talosdigital.talosAndroidApp.utils.ListOnClickListener
import com.talosdigital.talosAndroidApp.utils.VolleyService
import kotlinx.android.synthetic.main.activity_events_list.*
import org.json.JSONObject

class EventsListActivity : AppCompatActivity(), ApiResultListener, ListOnClickListener {
    val gson = Gson()
    val volleyService = VolleyService(this, this)
    lateinit var events: List<Event>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events_list)
        volleyService.getDataVolley("Get events", "https://talos-app.herokuapp.com/api/v1/events/get-all")
    }

    override fun notifySuccess(requestType: String?, response: JSONObject?) {
        val res = gson.fromJson(response.toString(), GetEventsResponse::class.java)
        Log.d("GSON",res.events[0].toString())
        this.events = res.events
        val listAdapter = EventsAdapter(res.events, this)
        val lm = LinearLayoutManager(this)
        lm.orientation = LinearLayoutManager.VERTICAL

        eventsRecyclerView.apply {
            layoutManager = lm
            adapter = listAdapter
        }

    }

    override fun notifyError(requestType: String?, error: VolleyError?) {
        Log.d(requestType, "ERROR: " + error.toString())
        Toast.makeText(applicationContext, "Oops! something went wrong", Toast.LENGTH_LONG).show()
    }

    override fun onViewClick(index: Int) {
        Log.d("index", index.toString())
        val intent = Intent(this, EventActivity::class.java)
        intent.putExtra("event", gson.toJson(this.events[index]))
        startActivity(intent)
    }


}