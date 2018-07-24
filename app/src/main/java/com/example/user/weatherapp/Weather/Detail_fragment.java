package com.example.user.weatherapp.Weather;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.weatherapp.News.Home_page;
import com.example.user.weatherapp.R;
import com.example.user.weatherapp.Results.ApiClient;
import com.example.user.weatherapp.Results.ApiInterface;
import com.example.user.weatherapp.model.MyPojo;
import com.example.user.weatherapp.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class Detail_fragment extends Fragment {

    //String countery;
    String cntry;
    MyPojo myPojo;
    int count = 0;
    User user = new User(count);
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7;
    ImageView imageView;
    Bundle bundle;
    String humidity;
    String q;
    Uri uri;

    public Detail_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // if (getArguments() != null) {

        //}

        return inflater.inflate(R.layout.fragment_detail_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv1 = (TextView) view.findViewById(R.id.country);
        tv2 = (TextView) view.findViewById(R.id.temp);
        tv3 = (TextView) view.findViewById(R.id.heat1);
        tv4 = (TextView) view.findViewById(R.id.wind1);
        tv5 = (TextView) view.findViewById(R.id.humi1);
        tv6 = (TextView) view.findViewById(R.id.condition);
        tv7 = (TextView) view.findViewById(R.id.temp1);

        //String text="nome,ak";
        bundle = this.getArguments();
        if (bundle != null) {
            cntry = bundle.getString("key", "india");
        }
        if (cntry.equals("United States"))
            q = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"nome,ak\")";
        if (cntry.equals("India,delhi"))
            q = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"india,delhi\")";
        if (cntry.equals("India,rajsthan"))
            q = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"india,rajasthan\")";
        if (cntry.equals("India,punjab"))
            q = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"india,punjab\")";
        if (cntry.equals("India,UP"))
            q = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"india,up\")";
        if (cntry.equals("India,jaipur"))
            q = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"india,jaipur\")";
        if (cntry.equals("Srilanka"))
            q = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"Srilanka,colombo\")";
        if (cntry.equals("Bangladesh"))
            q = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"Bangladesh,dhaka\")";
        if (cntry.equals("Nepal"))
            q = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"nepal,Kathmandu\")";
        ApiClient apiClient = new ApiClient();
        Retrofit retrofit = apiClient.getClient();
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<MyPojo> call = api.getquery(q, "json", "store");
        Log.d("TAG", "call  " + call.request());
        call.enqueue(new Callback<MyPojo>() {
            @Override
            public void onResponse(Call<MyPojo> call, Response<MyPojo> response) {
                myPojo = response.body();
                if (cntry.equals("")) {
                    tv1.setText("Please select region");
                }
                Log.d("TAG", "response" + myPojo);
//                Log.d("TAG",myPojo.getQuery().getResults().getChannel().getLocation().getCountry());
                try {
                    Toast.makeText(getContext(), "cntry" + cntry, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {

                }
                // uri=Uri.parse(myPojo.getQuery().getResults().getChannel().getImage().getUrl());
                try {
                    tv1.setText(myPojo.getQuery().getResults().getChannel().getLocation().getCountry() + ",  " + myPojo.getQuery().getResults().getChannel().getLocation().getCity());
                    tv4.setText("Wind Speed::" + myPojo.getQuery().getResults().getChannel().getWind().getSpeed() + " mph");
                    tv2.setText(myPojo.getQuery().getResults().getChannel().getItem().getCondition().getTemp() + " F");
                    tv7.setText("Temp::" + myPojo.getQuery().getResults().getChannel().getItem().getCondition().getTemp());
                    tv5.setText("Humidity::" + myPojo.getQuery().getResults().getChannel().getAtmosphere().getHumidity());
                    tv3.setText("Pressure:" + myPojo.getQuery().getResults().getChannel().getAtmosphere().getPressure());
                    tv6.setText(myPojo.getQuery().getResults().getChannel().getItem().getCondition().getText());
                    Bundle bundle = new Bundle();
                    Home_page home_page = new Home_page();
                    String[] value = {myPojo.getQuery().getResults().getChannel().getItem().getCondition().getTemp(), myPojo.getQuery().getResults().getChannel().getItem().getCondition().getText()};
                    bundle.putStringArray("value", value);
                    home_page.setArguments(bundle);
                    //Log.d("tag",query.getResults().getChannel().getLanguage());
                    // if(cntry.equals(myPojo.getQuery().getResults().getChannel().getLocation().getCountry()));
                    // {
                    humidity = myPojo.getQuery().getResults().getChannel().getAtmosphere().getHumidity();
                    Log.d("TAG", "humi" + humidity);
                } catch (Exception e) {

                }
                //}
            }

            @Override
            public void onFailure(Call<MyPojo> call, Throwable t) {
                Log.d("TAG", "failed");
            }
        });


    }

}
