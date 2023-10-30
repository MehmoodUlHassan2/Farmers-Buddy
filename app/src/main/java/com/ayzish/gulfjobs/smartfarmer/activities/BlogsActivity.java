package com.ayzish.gulfjobs.smartfarmer.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.ayzish.gulfjobs.smartfarmer.adapter.VideoAdapter;
import com.ayzish.gulfjobs.smartfarmer.adapter.VideoModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.channels.Channel;
import java.util.ArrayList;

public class BlogsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<VideoModel> recyclerDataArrayList;

    LinearLayoutManager layoutManager;
    VideoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Blogs");
        setContentView(R.layout.activity_blogs);
        Urls.anynum = 0;
        recyclerView=findViewById(R.id.blogsidServiceUsersRecyclerView);
        // created new array list..
        recyclerDataArrayList=new ArrayList<>();

        // added data to array list


        // added data from arraylist to adapter class.
        adapter=new VideoAdapter(recyclerDataArrayList,this);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setEnabled(false);
        GetData();




    }


    private void GetData() {

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, Urls.blogs, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<=response.length();i++){
                    try {
                        JSONObject jsonObject =response.getJSONObject(i);

                        recyclerDataArrayList.add(new VideoModel(jsonObject.getString("id"),jsonObject.getString("title"),jsonObject.getString("detail"),jsonObject.getString("image")));


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
            }
        });
        requestQueue.add(jsonArrayRequest);
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
                adapter.getFilter().filter(newText);

                return false;
            }
        });
        return true;
    }
}