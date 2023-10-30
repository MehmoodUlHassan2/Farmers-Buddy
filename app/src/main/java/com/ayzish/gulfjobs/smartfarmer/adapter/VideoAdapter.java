package com.ayzish.gulfjobs.smartfarmer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.ayzish.gulfjobs.smartfarmer.activities.BlogDetailActivity;
import com.ayzish.gulfjobs.smartfarmer.R;
import com.ayzish.gulfjobs.smartfarmer.Urls;
import com.ayzish.gulfjobs.smartfarmer.activities.VideoDetailActivity;
import com.squareup.picasso.Picasso;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.RecyclerViewHolder> implements Filterable {

    private ArrayList<VideoModel> courseDataArrayList;
    private ArrayList<VideoModel> courseDataArrayListFull;
    private Context mcontext;
    public VideoAdapter(ArrayList<VideoModel> recyclerDataArrayList, Context mcontext) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
        courseDataArrayListFull = new ArrayList<>(courseDataArrayList);
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_card_item, parent, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        VideoModel recyclerData = courseDataArrayList.get(position);
        //holder.imageView.setText(recyclerData.getTitle());
        holder.title.setText(recyclerData.getTitle());


        Picasso.get().load("https://kitabpedia.com/formerbuddy/"+recyclerData.getImage()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Urls.anynum == 0){
                    Intent intent = new Intent(mcontext, BlogDetailActivity.class);
                    intent.putExtra("id", recyclerData.getId());
                    intent.putExtra("video", recyclerData.getVideo());
                    intent.putExtra("title", recyclerData.getTitle());
                    intent.putExtra("image", recyclerData.getImage());
                    mcontext.startActivity(intent);
                }else if(Urls.anynum == 3){

                    Intent intent = new Intent(mcontext, VideoDetailActivity.class);
                    intent.putExtra("id", recyclerData.getId());
                    intent.putExtra("question", recyclerData.getTitle());
                    intent.putExtra("answer", recyclerData.getVideo());
                    mcontext.startActivity(intent);
                }
                else if(Urls.anynum == 4){

                }
                else{
                    Intent intent = new Intent(mcontext, VideoDetailActivity.class);
                    intent.putExtra("id", recyclerData.getId());
                    intent.putExtra("video", recyclerData.getVideo());
                    mcontext.startActivity(intent);
                }

            }
        });


    }



    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return courseDataArrayList.size();
    }

    @Override
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

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewVideo);
            title = itemView.findViewById(R.id.textViewVideo);
        }
    }




}