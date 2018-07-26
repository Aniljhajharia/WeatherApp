package com.example.user.weatherapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.user.weatherapp.Base_classes.BaseActivity;
import com.example.user.weatherapp.News.Country;
import com.example.user.weatherapp.News.Home_page;
import com.example.user.weatherapp.News.News;
import com.example.user.weatherapp.Weather.Srearch;
import com.example.user.weatherapp.model2.MyPojo2;

public class MainActivity extends BaseActivity {
    DrawerLayout drawerLayout;
    News news = new News();
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Country country = new Country();
    String coo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        news = new News();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
/**
 * used to select the items from navigation view and perform action on click
 */
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.business:
                        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
                        String country = sharedPref.getString("country", null);
                        if (country != null) {
                            coo = country.substring(country.length() - 2, country.length());
                        }

                        changefragment2(news);

                        news.setdata("business", coo);
                        break;

                    case R.id.entertainment:
                        SharedPreferences sharedPref2 = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
                        String country2 = sharedPref2.getString("country", null);
                        if (country2 != null)
                            coo = country2.substring(country2.length() - 2, country2.length());
                        changefragment2(news);

                        news.setdata("entertainment", coo);
                        break;

                    case R.id.health:

                        SharedPreferences sharedPref3 = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
                        String country3 = sharedPref3.getString("country", null);
                        if (country3 != null)
                            coo = country3.substring(country3.length() - 2, country3.length());

                        changefragment2(news);
                        news.setdata("health", coo);
                        break;

                    case R.id.science:
                        SharedPreferences sharedPref4 = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
                        String country4 = sharedPref4.getString("country", null);
                        if (country4 != null)
                            coo = country4.substring(country4.length() - 2, country4.length());

                        changefragment2(news);
                        news.setdata("science", coo);
                        break;
                    case R.id.sports:
                        SharedPreferences sharedPref5 = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
                        String country5 = sharedPref5.getString("country", null);
                        if (country5 != null)
                            coo = country5.substring(country5.length() - 2, country5.length());
                        changefragment2(news);
                        news.setdata("sports", coo);
                        break;
                    case R.id.technology:

                        SharedPreferences sharedPref6 = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
                        String country6 = sharedPref6.getString("country", null);
                        if (country6 != null)
                            coo = country6.substring(country6.length() - 2, country6.length());

                        changefragment2(news);
                        news.setdata("technology", coo);
                        break;
                    case R.id.weather:
                        changefragment2(new Srearch());
                        break;
                    case R.id.about:
                        changefragment2(new About());
                        break;
                    case R.id.country:
                        changefragment2(new Select());
                        break;
                }

                item.setChecked(true);
                drawerLayout.closeDrawers();
                return false;
            }
        });
        changefragment(new Home_page());
    }

    /**
     * on home click open navigation drawer
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() == 0)
            finish();
    }
}
