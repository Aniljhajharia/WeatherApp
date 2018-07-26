package com.example.user.weatherapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.weatherapp.Base_classes.BaseFragment;
import com.example.user.weatherapp.News.News;


/**
 * A simple {@link Fragment} subclass.
 */
public class Select extends BaseFragment {
News news=new News();
    public Select() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ListView listView=(ListView)view.findViewById(R.id.coun);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);

                // get menu from navigationView
                Menu menu = navigationView.getMenu();

                // find MenuItem you want to change
                MenuItem nav_camara = menu.findItem(R.id.country_select);

                // set new title to the MenuItem
                nav_camara.setTitle(((TextView) view).getText().toString());
                String con=((TextView) view).getText().toString();
                // do the same for other MenuItems
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("country", con);
                editor.commit();
                SharedPreferences sharedPref6 = getActivity().getPreferences(Context.MODE_PRIVATE);
                String country6 = sharedPref6.getString("country", null);
                con = country6.substring(country6.length() - 2, country6.length());
                ((MainActivity) getActivity()).changefragment(news);
                 news.setdata("sports",con);

            }
        });
    }
}
