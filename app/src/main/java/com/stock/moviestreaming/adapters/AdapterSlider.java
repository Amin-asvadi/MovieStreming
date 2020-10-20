package com.stock.moviestreaming.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;
import com.stock.moviestreaming.R;
import com.stock.moviestreaming.models.ModelSlider;

import java.util.List;

public class AdapterSlider extends PagerAdapter {
    private Context context;
    private List<ModelSlider> data;

    public AdapterSlider(Context context, List<ModelSlider> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_slider, container, false);
        ImageView img_slider = view.findViewById(R.id.img_slider);
        TextView slider_name = view.findViewById(R.id.slider_name);
        TextView slider_time = view.findViewById(R.id.slider_time);
        TextView slider_published = view.findViewById(R.id.slider_published);

        slider_name.setText(data.get(position).getName());
        slider_time.setText(data.get(position).getTime());
        slider_published.setText(data.get(position).getPublished());
        Picasso.get().load(data.get(position).getLink_img()).into(img_slider);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
