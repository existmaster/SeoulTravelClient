package com.example.tabfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.seoultravel.ListData;
import com.example.seoultravel.ListDataAdapter;
import com.example.seoultravel.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TabFrag3 extends Fragment {

    String url = "http://172.16.38.19:9000/travel/information/search?locationCode=3";
    //String url = "http://192.168.10.16:9000/travel/information/search?locationCode=3";
    ArrayList<ListData> datas = new ArrayList<ListData>();
    ListView listview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_fragment_3, container, false);
        //final Button test_btn = (Button) view.findViewById(R.id.buttonTest1);
        listview = (ListView) view.findViewById(R.id.listView);

        sendRequest();

        return view;
    }

    private void sendRequest() {
        JsonArrayRequest socRequest = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jo = response.getJSONObject(i);
                        String subject = jo.getString("subject");
                        String content = jo.getString("contents");
                        String imageName = jo.getString("imageName");

                        String resName = "@drawable/" + imageName;
                        String packName = getContext().getPackageName(); // 패키지명
                        int imageResource = getResources().getIdentifier(resName, "drawable", packName);

                        datas.add( new ListData(subject, content, imageResource));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                ListDataAdapter adapter= new ListDataAdapter(getActivity().getLayoutInflater(), datas);
                listview.setAdapter(adapter);
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