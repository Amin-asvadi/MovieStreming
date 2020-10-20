package com.stock.moviestreaming.adapters;

import android.content.Context;
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

public class AdapterAnimation extends RecyclerView.Adapter<AdapterAnimation.myViewHolder> {
    private Context context;
    private List<ModelMovies> data;
    private boolean is_limited;

    public AdapterAnimation(Context context, List<ModelMovies> data, boolean is_limited) {
        this.context = context;
        this.data = data;
        this.is_limited = is_limited;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_animation_movies, parent, false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.animation_name.setText(data.get(position).getName());
        holder.animation_time.setText(data.get(position).getTime());
        Picasso.get().load(data.get(position).getLink_img()).into(holder.img_animation);
        ViewGroup.LayoutParams params = holder.constraintLayout.getLayoutParams();
        params.height = (int) ((Global.getSizeScreen(context)) / 3);
        params.width = (int) ((Global.getSizeScreen(context)) / 3.5);
        holder.constraintLayout.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintLayout;
        private TextView animation_name;
        private TextView animation_time;
        private ImageView img_animation;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.cons_animation_movies);
            animation_name = itemView.findViewById(R.id.animation_name);
            animation_time = itemView.findViewById(R.id.animation_time);
            img_animation = itemView.findViewById(R.id.animation_img);
        }
    }
}
