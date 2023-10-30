package com.ayzish.gulfjobs.smartfarmer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ayzish.gulfjobs.smartfarmer.R;
import com.ayzish.gulfjobs.smartfarmer.Urls;
import com.ayzish.gulfjobs.smartfarmer.activities.BlogDetailActivity;
import com.ayzish.gulfjobs.smartfarmer.activities.QueryDetailActivity;
import com.ayzish.gulfjobs.smartfarmer.activities.VideoDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class QueryAdapter extends RecyclerView.Adapter<QueryAdapter.RecyclerViewHolder> {

    private ArrayList<VideoModel> courseDataArrayList;
    private Context mcontext;

    public QueryAdapter(ArrayList<VideoModel> recyclerDataArrayList, Context mcontext) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.query_item, parent, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        //Toast.makeText(mcontext, ""+ Urls.anynum, Toast.LENGTH_SHORT).show();
        VideoModel recyclerData = courseDataArrayList.get(position);
        //holder.imageView.setText(recyclerData.getTitle());
        holder.title.setText(recyclerData.getTitle());

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(mcontext, QueryDetailActivity.class);
                    intent.putExtra("id", recyclerData.getId());
                    intent.putExtra("question", recyclerData.getTitle());
                    intent.putExtra("answer", recyclerData.getVideo());
                    mcontext.startActivity(intent);


            }
        });


    }



    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return courseDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewQuery);
        }
    }

    public Filter getFilter() {
        return exampleFilter;
    }


    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<VideoModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(courseDataArrayList);
            } else {
                String filterPattern = constraint.toString().toLowerCase();
                for (VideoModel item : courseDataArrayList) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            courseDataArrayList.clear();
            courseDataArrayList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}
