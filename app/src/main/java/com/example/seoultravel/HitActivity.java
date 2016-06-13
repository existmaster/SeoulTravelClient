package com.example.seoultravel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HitActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String url = "http://172.16.38.19:9000/travel/information/top5";
    //String url = "http://192.168.10.16:9000/travel/information/top5";
    ArrayList<ListData> datas = new ArrayList<ListData>();
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_hit);

        listview = (ListView) findViewById(R.id.listView);
        sendRequest();
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
                        String packName = getPackageName(); // 패키지명
                        int imageResource = getResources().getIdentifier(resName, "drawable", packName);

                        datas.add( new ListData(subject, content, imageResource));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                ListDataAdapter adapter= new ListDataAdapter(getLayoutInflater(), datas);
                listview.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print("VolleyError : " + error.getMessage());
                VolleyLog.d("ERROR" + error.getMessage());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(socRequest);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.common, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            AlertDialog.Builder alert_confirm = new AlertDialog.Builder(HitActivity.this);
            alert_confirm.setMessage("프로그램을 종료 하시겠습니까?").setCancelable(false).setPositiveButton("확인",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 'YES'
                            moveTaskToBack(true);
                            finish();
                            android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    }).setNegativeButton("취소",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 'No'
                            return;
                        }
                    });
            AlertDialog alert = alert_confirm.create();
            alert.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_local) {
            // Handle the camera action
            startActivity(new Intent(HitActivity.this, LocalActivity.class));
            finish();
        } else if (id == R.id.nav_hit) {
            return true;
        } else if (id == R.id.nav_thema) {
            startActivity(new Intent(HitActivity.this, ThemaActivity.class));
            finish();
        } else if (id == R.id.nav_info) {
            startActivity(new Intent(HitActivity.this, InfoActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
