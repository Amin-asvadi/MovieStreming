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
import com.stock.moviestreaming.models.ModelSlider;

import java.util.List;

public class AdapterNewMovie extends RecyclerView.Adapter<AdapterNewMovie.myViewHolder>{
    private Context context;
    private List<ModelMovies> data;
    private boolean is_limited;

    public AdapterNewMovie(Context context, List<ModelMovies> data, boolean is_limited) {
        this.context = context;
        this.data = data;
        this.is_limited = is_limited;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_new_movie,parent,false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.new_movie_name.setText(data.get(position).getName());
        holder.new_movie_time.setText(data.get(position).getTime());
        Picasso.get().load(data.get(position).getLink_img()).into(holder.img_new_movie);
        ViewGroup.LayoutParams params =holder.constraintLayout.getLayoutParams();
        params.height = (int) ((Global.getSizeScreen(context)) / 3);
        params.width = (int) ((Global.getSizeScreen(context)) / 1.8);
        holder.constraintLayout.setLayoutParams(params);
        Log.d("getsize", "getItemCount: " + String.valueOf(data.size()));
    }

    @Override
    public int getItemCount() {
        return data.size();

    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintLayout;
        private ImageView img_new_movie;
        private TextView new_movie_name;
        private TextView new_movie_time;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.cons_new_movie_movies);
            img_new_movie = itemView.findViewById(R.id.img_new_movie);
            new_movie_name = itemView.findViewById(R.id.movie_new_name);
            new_movie_time = itemView.findViewById(R.id.movie_new_time);


        }
    }
}
