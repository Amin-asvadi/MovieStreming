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
import com.stock.moviestreaming.models.ModelGenre;

import java.util.List;

public class AdapterGenre extends RecyclerView.Adapter<AdapterGenre.myViewHolder>{
    private Context context;
    private List<ModelGenre> data;

    public AdapterGenre(Context context, List<ModelGenre> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_genre,parent,false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.genre_name.setText(data.get(position).getName());
        Picasso.get().load(data.get(position).getLink_img()).into(holder.img_genre);

        ViewGroup.LayoutParams params = holder.constraintLayout.getLayoutParams();
        params.height = (int) ((Global.getSizeScreen(context)) / 5);
        params.width = (int) ((Global.getSizeScreen(context)) / 5);
        holder.constraintLayout.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_genre;
        private ConstraintLayout constraintLayout;
        private TextView genre_name;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img_genre = itemView.findViewById(R.id.img_genre);
            constraintLayout = itemView.findViewById(R.id.cons_genre);
            genre_name = itemView.findViewById(R.id.genre_name);
        }
    }
}
