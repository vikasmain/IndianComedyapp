package com.ramya.vik.manya;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dell on 1/5/2017.
 */
public class Twofragment2 extends Fragment {
    private RecyclerView recyclerView;
    String string[]={"Latest Funny Comedy Videos","Rajpal Yadav's Rocks","Funny Politics","Mr Bean Episodes","Whats up Funny Videos"};
    String string2[]={"kapilsharma","yes we got this in the last","yo you can","ya what is this","yrfhednj","jdnjsnd sd s d sd sn ds","sghdbshdbhsd dh sdn","hdbsnsandd  dn ahd"};
    SearchView sv;
    // private static final String url = "https://app-1486707345.000webhostapp.com/rajpal.json";

    private static final String TAG = Twofragment2.class.getSimpleName();
    RequestQueue queue;
    String com[]={"Comment here","Comment here","Comment here","Comment here","Comment here","Comment here","Comment here","Comment here"};
    private ArrayList<Player2> list=new ArrayList<>();
    private MyAdapter6 adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ci, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycler);
        //SET ITS PROPETRIES
        list=new ArrayList<>();
        adapter = new MyAdapter6(getActivity(), list);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setItemAnimator(new DefaultItemAnimator());


        rv.setAdapter(adapter);
        queue=AppController.getInstance().getRequestQueue();
        prepa();



        return view;
    }

    //ADD Data TO ARRAYLIST
    public void prepa(){
       final String url = "https://felicific-hooks.000webhostapp.com/fives.json";

        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());


                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Player2 movie = new Player2(obj.getString("name"),obj.getString("imag"),obj.getString("title"));
                                list.add(movie);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());


            }
        });

        // Adding request to request queue


        queue.add(movieReq);

    }


}

