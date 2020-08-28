package com.test.liverpoolsearcher.Model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {

    private Context context;
    private RequestQueue requestQueue;
    private static MySingleton mySingleton;

    private MySingleton(Context context ) {
        //this.url = url;
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MySingleton getInstance(Context context){
        if(mySingleton == null){
            mySingleton = new MySingleton(context);
        }
        return mySingleton;
    }

    public<T> void addToRequestque (Request<T> request){
        requestQueue.add(request);
    }

    public void onStop (String tag){
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }

}
