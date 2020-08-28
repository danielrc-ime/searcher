package com.test.liverpoolsearcher.Model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;


public class RequestingJSON {

    private String TAG = this.getClass().getSimpleName();
    private Context context;
    private RequestAuthListener requestAuthListener;
    private String url = "https://shoppapp.liverpool.com.mx/appclienteservices/services/v3/plp?force-plp=true&search-string=";
    private String producto;
    private String url2 = "&page-number=";
    private String numPagina;
    private String url3 = "&number-of-items-per-page=";
    private String numItems;

    public RequestingJSON(final RequestAuthListener requestAuthListener){
        this.requestAuthListener = requestAuthListener;
    }

    public  void setRequestingJSON(String producto, String numPagina, String numItems, Context context){
        this.producto = producto;
        this.numPagina = numPagina;
        this.numItems = numItems;
        this.context = context;

    }

    public void doRequest(){
        url += producto + url2 + numPagina +url3 + numItems;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, String.valueOf(response));
                        jsonFilter(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.e(TAG, error.toString());
                        requestAuthListener.onFailureRequest(error);

                    }
                });
        jsonObjectRequest.setTag("My");
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(context.getApplicationContext()).addToRequestque(jsonObjectRequest);
    }

    private void jsonFilter (JSONObject response){
        try {
            JSONArray records = response.getJSONObject("plpResults").getJSONArray("records");
            for (int i = 0; i < records.length(); i++) {
                JSONObject rec1 = records.getJSONObject(i);
                requestAuthListener.onSuccesRequest(rec1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            requestAuthListener.onFailureRequest(e);
        }
    }

}
