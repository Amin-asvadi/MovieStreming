package com.stock.moviestreaming.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.stock.moviestreaming.R;
import com.stock.moviestreaming.adapters.AdapterAnimation;
import com.stock.moviestreaming.adapters.AdapterGenre;
import com.stock.moviestreaming.adapters.AdapterIraniMovies;
import com.stock.moviestreaming.adapters.AdapterNewMovie;
import com.stock.moviestreaming.adapters.AdapterSlider;
import com.stock.moviestreaming.adapters.AdapterTopMovies;
import com.stock.moviestreaming.models.ModelGenre;
import com.stock.moviestreaming.models.ModelMovies;
import com.stock.moviestreaming.models.ModelSlider;
import com.stock.moviestreaming.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Global
    private Repository repository;
    //REQUEST
    private RequestQueue requestQueue;
    //FOR Slider
    private List<ModelSlider> sliders_items = new ArrayList<>();
    private TabLayout slider_tab_layout;
    private ViewPager slider_home;
    private AdapterSlider adapterSlider;

    //For TopMovies
    private List<ModelMovies> top_movies_items = new ArrayList<>();
    private RecyclerView rc_top_movies;
    private AdapterTopMovies adapterTopMovies;

    //For IraniMovies
    private List<ModelMovies> top_irani_items = new ArrayList<>();
    private RecyclerView rc_irani_movies;
    private AdapterIraniMovies adapterIraniMovies;

    //For New Relesed
    private  List<ModelMovies> new_movie_items = new ArrayList<>();
    private RecyclerView rc_new_movies;
    private AdapterNewMovie adapterNewMovie;

    //For Animation Movies
    private List<ModelMovies> animation_items = new ArrayList<>();
    private RecyclerView rc_animation;
    private AdapterAnimation adapterAnimation;
    //For Genre
    private List<ModelGenre> genres_list = new ArrayList<>();
    private AdapterGenre adapterGenre;
    private RecyclerView rc_genre;

    //DrawerLayout
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView btn_menu;
    private RelativeLayout parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        repository = new Repository();

        Init();
        Drawer();
        GetGenre();
        GetSlider();
        GetTopMovies();
        GetIraniMovies();
        GetNewMovieRelased();
        GetAnimationMovies();
        TimerSlider timerSlider = new TimerSlider();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerSlider, 6000, 3000);


    }



    private void Init() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        btn_menu = findViewById(R.id.btn_menu);
        parent = findViewById(R.id.parent);

    }
    private void Drawer() {
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
       // drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                float changeScaleOfSet = slideOffset * (1 - 0.7f);
                float OffSetScale =  1- changeScaleOfSet;
                parent.setScaleX(OffSetScale);
                parent.setScaleY(OffSetScale);

                float X_OF_Set = drawerView.getWidth() * slideOffset;
                float X_Of_Set_Change = parent.getWidth() * changeScaleOfSet / 2;
                float X_Translation =X_OF_Set - X_Of_Set_Change;
                parent.setTranslationX(X_Translation);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    }

    private void GetGenre() {
        rc_genre = findViewById(R.id.rc_genre);
        rc_genre.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rc_genre.setHasFixedSize(true);
        adapterGenre = new AdapterGenre(this,genres_list);
        repository.GetGenre(this,requestQueue,"",rc_genre,genres_list);
        rc_genre.setAdapter(adapterGenre);
    }


    //ShowSliderToHomeActivity
    private void GetSlider() {
        slider_home = findViewById(R.id.slider_home);
        slider_tab_layout = findViewById(R.id.tabLayout);
        adapterSlider = new AdapterSlider(getApplicationContext(), sliders_items);
        repository.getSlider(MainActivity.this, requestQueue, "", slider_home, sliders_items);
        slider_home.setAdapter(adapterSlider);
        slider_tab_layout.setupWithViewPager(slider_home, true);
    }
    //GetTopMovies
    private void GetTopMovies() {
        rc_top_movies = findViewById(R.id.recycler_top_movies);
        rc_top_movies.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        rc_top_movies.setHasFixedSize(true);
        adapterTopMovies = new AdapterTopMovies(this, top_movies_items);
        repository.getTopMovies(MainActivity.this, requestQueue, "top_movies", rc_top_movies, top_movies_items);
        rc_top_movies.setAdapter(adapterTopMovies);
    }
    //GetIranMovies
    private void GetIraniMovies() {
        rc_irani_movies = findViewById(R.id.rc_irani_movie);
        rc_irani_movies.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rc_irani_movies.setHasFixedSize(true);
        adapterIraniMovies = new AdapterIraniMovies(this,top_irani_items,true);
        repository.getIraniMovies(this,requestQueue,"irani",rc_irani_movies,top_irani_items);
        rc_irani_movies.setAdapter(adapterIraniMovies);
    }
    //Get New Released
    private void GetNewMovieRelased() {
        rc_new_movies = findViewById(R.id.rc_new_movies);
        rc_new_movies.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rc_new_movies.setHasFixedSize(true);
        adapterNewMovie = new AdapterNewMovie(this,new_movie_items,true);
        repository.getNewMovies(this,requestQueue,"new",rc_new_movies,new_movie_items);
        rc_new_movies.setAdapter(adapterNewMovie);
    }
    //Get Animation Movies
    private void GetAnimationMovies() {
        rc_animation = findViewById(R.id.rc_animations);
        rc_animation.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rc_animation.setHasFixedSize(true);
        adapterAnimation = new AdapterAnimation(this,animation_items,true);
        repository.getAnimationsMovies(this,requestQueue,"animation",rc_animation,animation_items);
        rc_animation.setAdapter(adapterAnimation);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

    //Timer For Slider
    public class TimerSlider extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (slider_home.getCurrentItem() < sliders_items.size() - 1) {
                        slider_home.setCurrentItem(slider_home.getCurrentItem() + 1);
                    } else {
                        slider_home.setCurrentItem(0);
                    }
                }
            });
        }
    }
}