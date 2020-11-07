package com.talosdigital.talosAndroidApp.utils;

import android.content.Context;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class VolleyService {

    ApiResultListener mResultCallback = null;
    Context mContext;
    String resourceToken = "";
    String accessToken = "";
    public VolleyService(ApiResultListener resultCallback, Context context){
        mResultCallback = resultCallback;
        mContext = context;
    }

    public void addResourceToken(String token){
        resourceToken = token;
    }

    public void addAccessToken(String token){
        accessToken = token;
    }

    public String getResourceToken(){
        return resourceToken;
    }

    public void postDataVolley(final String requestType, String url, JSONObject sendObj){
        System.out.println(requestType);
        System.out.println(url);
        System.out.println(sendObj.toString());
        try {
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.POST, url, sendObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    if(mResultCallback != null)
                        mResultCallback.notifySuccess(requestType,response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    if(mResultCallback != null)
                        mResultCallback.notifyError(requestType,error);
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("Authorization", resourceToken);
                    params.put("client_access_token", accessToken);

                    return params;
                }
            };

            queue.add(jsonObj);

        }catch(Exception e){

        }
    }

    public void getDataVolley(final String requestType, String url){
        try {
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if(mResultCallback != null)
                        mResultCallback.notifySuccess(requestType, response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(mResultCallback != null)
                        mResultCallback.notifyError(requestType, error);
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("Authorization", resourceToken);
                    params.put("client_access_token", accessToken);

                    return params;
                }
            };

            queue.add(jsonObj);

        }catch(Exception e){

        }
    }


    public void putDataVolley(final String requestType, String url, JSONObject sendObj){
        try {
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.PUT, url,sendObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    if(mResultCallback != null)
                        mResultCallback.notifySuccess(requestType,response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    if(mResultCallback != null)
                        mResultCallback.notifyError(requestType,error);
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Authorization", resourceToken);
                    params.put("client_access_token", accessToken);

                    return params;
                }
            };

            queue.add(jsonObj);

        }catch(Exception e){

        }
    }

    public void deleteDataVolley(final String requestType, String url, JSONObject sendObj){
        try {
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.DELETE, url,sendObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    if(mResultCallback != null)
                        mResultCallback.notifySuccess(requestType,response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    if(mResultCallback != null)
                        mResultCallback.notifyError(requestType,error);
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("Authorization", resourceToken);
                    params.put("client_access_token", accessToken);

                    return params;
                }
            };

            queue.add(jsonObj);

        }catch(Exception e){

        }
    }


}
