package com.stock.moviestreaming.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.stock.moviestreaming.R;
import com.stock.moviestreaming.global.Global;
import com.stock.moviestreaming.models.ModelMovies;

import java.util.List;

public class AdapterTopMovies extends RecyclerView.Adapter<AdapterTopMovies.myViewHolder> {
    private Context context;
    private List<ModelMovies> data;
    private boolean is_limited;

    public AdapterTopMovies(Context context, List<ModelMovies> data) {
        this.context = context;
        this.data = data;

    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_top_movies, parent, false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.top_movies_name.setText(data.get(position).getName());
        holder.top_movies_time.setText(data.get(position).getTime());
       Picasso.get().load(data.get(position).getLink_img()).into(holder.img_top_movies);

        ViewGroup.LayoutParams params =holder.cons_top_movies.getLayoutParams();
        params.height = (int) ((Global.getSizeScreen(context)) / 3);
        params.width = (int) ((Global.getSizeScreen(context)) / 3.5);
		holder.cons_top_movies.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {


            return data.size();

    }

    public class myViewHolder extends RecyclerView.ViewHolder {

         ConstraintLayout cons_top_movies;
         ImageView img_top_movies;
         TextView top_movies_name;
         TextView top_movies_time;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            cons_top_movies = itemView.findViewById(R.id.cons_top_movies);
            img_top_movies = itemView.findViewById(R.id.img_top_movies);
            top_movies_name = itemView.findViewById(R.id.top_movie_name);
            top_movies_time = itemView.findViewById(R.id.top_movie_time);
        }
    }
}
