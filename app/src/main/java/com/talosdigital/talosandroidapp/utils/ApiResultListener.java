package com.talosdigital.talosAndroidApp.utils;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface ApiResultListener {
    public void notifySuccess(String requestType, JSONObject response);
    public void notifyError(String requestType, VolleyError error);

}
