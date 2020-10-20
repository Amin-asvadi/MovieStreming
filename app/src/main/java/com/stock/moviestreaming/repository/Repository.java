package com.stock.moviestreaming.repository;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.stock.moviestreaming.adapters.AdapterAnimation;
import com.stock.moviestreaming.adapters.AdapterGenre;
import com.stock.moviestreaming.adapters.AdapterIraniMovies;
import com.stock.moviestreaming.adapters.AdapterNewMovie;
import com.stock.moviestreaming.adapters.AdapterTopMovies;
import com.stock.moviestreaming.adapters.AdapterSlider;
import com.stock.moviestreaming.models.ModelGenre;
import com.stock.moviestreaming.models.ModelMovies;
import com.stock.moviestreaming.models.ModelSlider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private String BaseUrl ="http://mashhadburse.ir/burse/";
    private String link_slider = "slider.php";
    private String link_genre="genre.php";
    private String link_top_movies = "getmovies.php?category_name=";
    RequestQueue requestQueue;
    //Slider
    private List<ModelSlider> listSlider = new ArrayList<>();
    private AdapterSlider adapterSlider;
    //TopMovies
    private List<ModelMovies> listMovies = new ArrayList<>();
    private AdapterTopMovies adapterTopMovies;
    //IraniTopMovies
    private List<ModelMovies> listIraniMovies = new ArrayList<>();
    private AdapterIraniMovies adapterIraniMovies;
    //New Released
    private List<ModelMovies> list_new_movies = new ArrayList<>();
    private AdapterNewMovie adapterNewMovie;
    //Animation Movies
    private List<ModelMovies> list_animation = new ArrayList<>();
    private AdapterAnimation adapterAnimation;
    //Genre
    private List<ModelGenre> list_genre = new ArrayList<>();
    private AdapterGenre adapterGenre;


    //GetSlider
    public void getSlider(final Context context, RequestQueue requestQueue, String url, ViewPager viewPager, List<ModelSlider> list) {
        this.requestQueue = requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        String LINK = BaseUrl + link_slider + url;
        this.listSlider = list;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LINK
                , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("slider");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String link_img = jsonObject.getString("link_img");
                        String time = jsonObject.getString("time");
                        String published = jsonObject.getString("published");

                        ModelSlider slider = new ModelSlider();

                        // Toast.makeText(MainActivity.this, id + "", Toast.LENGTH_SHORT).show();

                        slider.setId(id);
                        slider.setName(name);
                        slider.setLink_img(link_img);
                        slider.setTime(time);
                        slider.setPublished(published);

                        listSlider.add(slider);
                        adapterSlider = new AdapterSlider(context, list);
                        viewPager.setAdapter(adapterSlider);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }
    //GetMovies
    public void getTopMovies(final Context context, RequestQueue requestQueue, String url, RecyclerView recyclerView, List<ModelMovies> list) {
        this.requestQueue = requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        String LINK = BaseUrl + link_top_movies + url;
        this.listMovies = list;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LINK
                , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("movie");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String link_img = jsonObject.getString("link_img");
                        String director = jsonObject.getString("director");
                        String rate_imdb = jsonObject.getString("rate_imdb");
                        String published = jsonObject.getString("published");
                        String time = jsonObject.getString("time");
                        String category_name = jsonObject.getString("category_name");
                        String rank = jsonObject.getString("rank");


                        ModelMovies movies = new ModelMovies();

                        // Toast.makeText(MainActivity.this, id + "", Toast.LENGTH_SHORT).show();

                        movies.setId(id);
                        movies.setName(name);
                        movies.setLink_img(link_img);
                        movies.setDirector(director);
                        movies.setRate_imdb(rate_imdb);
                        movies.setPublished(published);
                        movies.setTime(time);
                        movies.setCategory_name(category_name);
                        movies.setRank(rank);



                        listMovies.add(movies);
                        adapterTopMovies = new AdapterTopMovies(context, list);
                        recyclerView.setAdapter(adapterTopMovies);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }
    //GetIraniMovies
    public void getIraniMovies(final Context context, RequestQueue requestQueue, String url, RecyclerView recyclerView, List<ModelMovies> list) {
        this.requestQueue = requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        String LINK = BaseUrl + link_top_movies + url;
        this.listIraniMovies = list;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LINK
                , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("movie");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String link_img = jsonObject.getString("link_img");
                        String director = jsonObject.getString("director");
                        String rate_imdb = jsonObject.getString("rate_imdb");
                        String published = jsonObject.getString("published");
                        String time = jsonObject.getString("time");
                        String category_name = jsonObject.getString("category_name");
                        String rank = jsonObject.getString("rank");

                        ModelMovies movies = new ModelMovies();

                        movies.setId(id);
                        movies.setName(name);
                        movies.setLink_img(link_img);
                        movies.setDirector(director);
                        movies.setRate_imdb(rate_imdb);
                        movies.setPublished(published);
                        movies.setTime(time);
                        movies.setCategory_name(category_name);
                        movies.setRank(rank);
                        listIraniMovies.add(movies);
                        adapterIraniMovies = new AdapterIraniMovies(context, list,true);
                        recyclerView.setAdapter(adapterIraniMovies);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    public void getNewMovies(final Context context, RequestQueue requestQueue, String url, RecyclerView recyclerView, List<ModelMovies> list) {
        this.requestQueue = requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        String LINK = BaseUrl + link_top_movies + url;
        this.list_new_movies = list;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LINK
                , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("movie");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String link_img = jsonObject.getString("link_img");
                        String director = jsonObject.getString("director");
                        String rate_imdb = jsonObject.getString("rate_imdb");
                        String published = jsonObject.getString("published");
                        String time = jsonObject.getString("time");
                        String category_name = jsonObject.getString("category_name");
                        String rank = jsonObject.getString("rank");

                        ModelMovies movies = new ModelMovies();

                        movies.setId(id);
                        movies.setName(name);
                        movies.setLink_img(link_img);
                        movies.setDirector(director);
                        movies.setRate_imdb(rate_imdb);
                        movies.setPublished(published);
                        movies.setTime(time);
                        movies.setCategory_name(category_name);
                        movies.setRank(rank);
                        list_new_movies.add(movies);
                        adapterNewMovie = new AdapterNewMovie(context, list,true);
                        recyclerView.setAdapter(adapterNewMovie);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }
    public void getAnimationsMovies(final Context context, RequestQueue requestQueue, String url, RecyclerView recyclerView, List<ModelMovies> list) {
        this.requestQueue = requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        String LINK = BaseUrl + link_top_movies + url;
        this.list_animation = list;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LINK
                , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("movie");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String link_img = jsonObject.getString("link_img");
                        String director = jsonObject.getString("director");
                        String rate_imdb = jsonObject.getString("rate_imdb");
                        String published = jsonObject.getString("published");
                        String time = jsonObject.getString("time");
                        String category_name = jsonObject.getString("category_name");
                        String rank = jsonObject.getString("rank");

                        ModelMovies movies = new ModelMovies();

                        movies.setId(id);
                        movies.setName(name);
                        movies.setLink_img(link_img);
                        movies.setDirector(director);
                        movies.setRate_imdb(rate_imdb);
                        movies.setPublished(published);
                        movies.setTime(time);
                        movies.setCategory_name(category_name);
                        movies.setRank(rank);
                        list_animation.add(movies);
                        adapterAnimation = new AdapterAnimation(context, list,true);
                        recyclerView.setAdapter(adapterAnimation);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }
    public void GetGenre(final Context context, RequestQueue requestQueue, String url, RecyclerView recyclerView, List<ModelGenre> list) {
        this.requestQueue = requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        String LINK = BaseUrl + link_genre + url;
        this.list_genre = list;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LINK
                , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("genre");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String link_img = jsonObject.getString("link_img");


                        ModelGenre genre = new ModelGenre();

                        genre.setId(id);
                        genre.setName(name);
                        genre.setLink_img(link_img);
                        Log.d("getListGenre", "onBindViewHolder: " +name);
                        list_genre.add(genre);
                        adapterGenre = new AdapterGenre(context, list);
                        recyclerView.setAdapter(adapterGenre);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }

}
