package com.talosdigital.talosAndroidApp.models.getEventsResponse

data class GetEventsResponse(
    val events: List<Event>,
    val message: String,
    val status: String
)