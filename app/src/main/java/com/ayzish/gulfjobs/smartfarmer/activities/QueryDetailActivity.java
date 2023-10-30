package com.ayzish.gulfjobs.smartfarmer.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ayzish.gulfjobs.smartfarmer.R;
import com.ayzish.gulfjobs.smartfarmer.Urls;
import com.ayzish.gulfjobs.smartfarmer.adapter.QueryAdapter;
import com.ayzish.gulfjobs.smartfarmer.adapter.VideoModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QueryDetailActivity extends AppCompatActivity {
    TextView tv1, tv2;
    String question, answer,id, ans;

    RecyclerView recyclerView;
    ArrayList<VideoModel> recyclerDataArrayList;
    LinearLayoutManager layoutManager;
    QueryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_detail);

        tv1 = findViewById(R.id.textViewQueryQuestion);
        tv2 = findViewById(R.id.textViewQueryAnswer);

        Bundle bundle = getIntent().getExtras();

        id = bundle.getString("id");
        question = bundle.getString("question");
        answer = bundle.getString("answer");

        tv1.setText(question);
        tv2.setText(answer);

        recyclerView=findViewById(R.id.query_ans_reviews_idServiceUsersRecyclerView);

        // created new array list..
        recyclerDataArrayList=new ArrayList<>();

        // added data to array list

        // added data from arraylist to adapter class.
        adapter=new QueryAdapter(recyclerDataArrayList,this);
        layoutManager=new LinearLayoutManager(this);

        GetData();


    }

    public void showDailogHereQuery(View view) {

        showInputDialog();
    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dailog_reviews, null);
        builder.setView(dialogView);

        final EditText editText = dialogView.findViewById(R.id.edit_text_input);

        builder.setPositiveButton("Add Answer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userInput = editText.getText().toString();

                addDataToDatabase(userInput);
            }
        });

        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void GetData() {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, Urls.answer_api+"?id="+id, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<=response.length();i++){
                    try {
                        JSONObject jsonObject =response.getJSONObject(i);

                        recyclerDataArrayList.add(new VideoModel("1",jsonObject.getString("answer"),jsonObject.getString("answer"),""));

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
    private void addDataToDatabase(String question1) {
        // url to post our data
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(QueryDetailActivity.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, Urls.save_answer_api, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.

                    if (jsonObject.getString("message").equals("Done")){
                        Toast.makeText(QueryDetailActivity.this, "Add Successfully Refresh the page..", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(QueryDetailActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
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
                params.put("query", id);
                params.put("answer", question1);

                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);

    }
}