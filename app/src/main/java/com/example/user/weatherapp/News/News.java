package com.example.user.weatherapp.News;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.user.weatherapp.Base_classes.BaseFragment;
import com.example.user.weatherapp.R;
import com.example.user.weatherapp.Results.ApiClient2;
import com.example.user.weatherapp.Results.ApiInterface;
import com.example.user.weatherapp.Weather.Custom_adaptor;
import com.example.user.weatherapp.model2.Articles;
import com.example.user.weatherapp.model2.MyPojo2;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class News extends BaseFragment {

    private ShimmerRecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    MyPojo2 myPojo2;
    String category, coo;

    public News() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (ShimmerRecyclerView) view.findViewById(R.id.my_recycler_view);
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String country = sharedPref.getString("country", null);
        if (country != null)
            coo = country.substring(country.length() - 2, country.length());
    }

    /**
     * function to display news according category and country
     * @param cat
     * @param cc
     */
    public void setdata(String cat, String cc) {

        ApiClient2 apiClient2 = new ApiClient2();
        Retrofit retrofit = apiClient2.getClient();
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<MyPojo2> call = api.getNews(cc, cat, "c1d8d2356a0c45b0b5ce4ea84a4f14da");
        call.enqueue(new Callback<MyPojo2>() {
            @Override
            public void onResponse(Call<MyPojo2> call, Response<MyPojo2> response) {
                myPojo2 = response.body();
                Log.d("TAG", "response" + new Gson().toJson(myPojo2));
                Articles[] articles = myPojo2.getArticles();
                List<Articles> list = new ArrayList<>();

                list.addAll(Arrays.asList(articles));

                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                List<String> Title = new ArrayList<>();
                List<String> description = new ArrayList<>();
                List<String> urls = new ArrayList<>();
                List<String> images = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    Title.add(list.get(i).getTitle());
                    description.add(list.get(i).getDescription());
                    urls.add(list.get(i).getUrl());
                    images.add(list.get(i).getUrlToImage());
                }

                try {
                    mAdapter = new Custom_adaptor(getContext().getApplicationContext(), Title, description, urls, images, News.this);
                    recyclerView.setAdapter(mAdapter);
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<MyPojo2> call, Throwable t) {
                Log.d("E", "response failed" + t);
            }
        });
    }

}
