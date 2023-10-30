package com.ayzish.gulfjobs.smartfarmer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ayzish.gulfjobs.smartfarmer.R;
import com.ayzish.gulfjobs.smartfarmer.Urls;
import com.ayzish.gulfjobs.smartfarmer.adapter.QueryAdapter;
import com.ayzish.gulfjobs.smartfarmer.adapter.VideoAdapter;
import com.ayzish.gulfjobs.smartfarmer.adapter.VideoModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QueriesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<VideoModel> recyclerDataArrayList;

    LinearLayoutManager layoutManager;
    QueryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queries);

        Urls.anynum = 3;
        recyclerView=findViewById(R.id.queries_idServiceUsersRecyclerView);

        // created new array list..
        recyclerDataArrayList=new ArrayList<>();

        // added data to array list

        // added data from arraylist to adapter class.
        adapter=new QueryAdapter(recyclerDataArrayList,this);
        layoutManager=new LinearLayoutManager(this);

        GetData();

    }

    private void GetData() {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, Urls.queries_api, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<=response.length();i++){
                    try {
                        JSONObject jsonObject =response.getJSONObject(i);

                        recyclerDataArrayList.add(new VideoModel(jsonObject.getString("id"),jsonObject.getString("question"),jsonObject.getString("answer"),""));


                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                // at last set adapter to recycler view.
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                //Toast.makeText(QueriesActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void addNewService(View view) {
        Intent intent = new Intent(QueriesActivity.this, QueryGoActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recyclerDataArrayList.clear();
                GetData();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.getFilter().filter(newText);
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}