package com.example.user.weatherapp.News;


import android.os.Bundle;
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
import com.example.user.weatherapp.MainActivity;
import com.example.user.weatherapp.R;
import com.example.user.weatherapp.Results.ApiClient;
import com.example.user.weatherapp.Results.ApiClient2;
import com.example.user.weatherapp.Results.ApiInterface;
import com.example.user.weatherapp.Weather.Custom_adaptor;
import com.example.user.weatherapp.model.MyPojo;
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
public class Home_page extends BaseFragment {

    MyPojo2 myPojo2;
    MyPojo myPojo;
    Bundle bundle;
    TextView tv1, tv2;
    private ShimmerRecyclerView recyclerView1;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public Home_page() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_page, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv1 = (TextView) view.findViewById(R.id.home_2_head);
        tv2 = (TextView) view.findViewById(R.id.home_3_head);
        ApiClient apiClient = new ApiClient();
        Retrofit retrofit = apiClient.getClient();
        String q = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"india, delhi\")";
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<MyPojo> call = api.getquery(q, "json", "store");
        call.enqueue(new Callback<MyPojo>() {
            @Override
            public void onResponse(Call<MyPojo> call, Response<MyPojo> response) {
                myPojo = response.body();

                try {

                } catch (Exception e) {

                }

                tv1.setText(myPojo.getQuery().getResults().getChannel().getItem().getCondition().getText());

                tv2.setText(myPojo.getQuery().getResults().getChannel().getItem().getCondition().getTemp() + " F");


                Bundle bundle = new Bundle();
                Home_page home_page = new Home_page();
                String[] value = {myPojo.getQuery().getResults().getChannel().getItem().getCondition().getTemp(), myPojo.getQuery().getResults().getChannel().getItem().getCondition().getText()};
                bundle.putStringArray("value", value);
                home_page.setArguments(bundle);

            }

            @Override
            public void onFailure(Call<MyPojo> call, Throwable t) {

            }
        });

        recyclerView1 = (ShimmerRecyclerView) view.findViewById(R.id.home_page_news);
        bundle = this.getArguments();

        ApiClient2 apiClient2 = new ApiClient2();
        Retrofit retrofit2 = apiClient2.getClient();
        ApiInterface api2 = retrofit2.create(ApiInterface.class);
        Call<MyPojo2> call2 = api2.getNews("in", "", "c1d8d2356a0c45b0b5ce4ea84a4f14da");
        call2.enqueue(new Callback<MyPojo2>() {
            @Override
            public void onResponse(Call<MyPojo2> call, Response<MyPojo2> response) {
                myPojo2 = response.body();
                Log.d("TAG", "response" + new Gson().toJson(myPojo2));
                Articles[] articles = myPojo2.getArticles();
                List<Articles> list = new ArrayList<>();

                list.addAll(Arrays.asList(articles));
                recyclerView1.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView1.setLayoutManager(layoutManager);
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
                    mAdapter = new Custom_adaptor(getContext().getApplicationContext(), Title, description, urls, images,Home_page.this);
                    recyclerView1.setAdapter(mAdapter);
                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<MyPojo2> call, Throwable t) {
                Log.d("E", "response failed" + t);
            }
        });
        TextView textView = (TextView) view.findViewById(R.id.firstLine);


    }

}
