package com.talosdigital.talosAndroidApp.models.getEventsResponse

data class Event(
    val _id: String,
    val accessLink: String,
    val registerLink: String,
    val days: List<String>,
    val author: String,
    val createdAt: String,
    val startDate: String,
    val endDate: String,
    val description: String,
    val imageLink: String,
    val name: String,
    val updatedAt: String
)