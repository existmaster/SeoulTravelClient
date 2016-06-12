package com.example.tabfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.seoultravel.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TabFrag1 extends Fragment {

    String url = "http://192.168.10.16:9000/travel/information";
    TextView textview = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_fragment_1, container, false);
        final Button test_btn = (Button) view.findViewById(R.id.buttonTest1);
        textview = (TextView) view.findViewById(R.id.textView);
        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();

            }
        });

        return view;
    }

    private void sendRequest() {
        JsonArrayRequest socRequest = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jo = response.getJSONObject(i);
                        String likeCount = jo.getString("likeCount");
                        sb.append(likeCount);
                        sb.append(" ");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                textview.setText(sb.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print("VolleyError : " + error.getMessage());
                VolleyLog.d("ERROR" + error.getMessage());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(socRequest);

    }
}