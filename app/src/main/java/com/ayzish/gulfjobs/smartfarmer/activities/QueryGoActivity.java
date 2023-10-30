package com.ayzish.gulfjobs.smartfarmer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ayzish.gulfjobs.smartfarmer.R;
import com.ayzish.gulfjobs.smartfarmer.Urls;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class QueryGoActivity extends AppCompatActivity {
    TextView query;
    String question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_go);



    }

    public void bookNowMe(View view) {
        query = findViewById(R.id.detailsBook);
        question = query.getText().toString();
        addDataToDatabase(question);

    }

    private void addDataToDatabase(String question) {
        // url to post our data
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(QueryGoActivity.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, Urls.save_queries_api, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.

                    if (jsonObject.getString("message").equals("Done")){
                        Toast.makeText(QueryGoActivity.this, "Save Query Successful...", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(QueryGoActivity.this, QueriesActivity.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(QueryGoActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {

                // below line we are creating a map for storing
                // our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                params.put("query", question);

                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);

    }
}