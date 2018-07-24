package com.example.user.weatherapp.News;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.user.weatherapp.Base_classes.BaseFragment;
import com.example.user.weatherapp.MainActivity;
import com.example.user.weatherapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Country extends BaseFragment {

    View v;
    String code, code1;
    AutoCompleteTextView textView;
    Button button;
    Bundle bundle;
    String category;
    News news;

    public Country() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_country, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (AutoCompleteTextView) v.findViewById(R.id.cont);
        button = (Button) v.findViewById(R.id.getnews);
        bundle = this.getArguments();
        category = bundle.getString("key");

        String[] countries = getResources().getStringArray(R.array.countrie);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, countries);
        textView.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                news = new News();

                code1 = textView.getText().toString();
                code = textView.getText().toString().substring(code1.length() - 2, code1.length());

                FragmentManager fragmentManager9 = getFragmentManager();
                android.support.v4.app.FragmentTransaction ft9 = fragmentManager9.beginTransaction();
                ft9.replace(R.id.main, news);
                ft9.addToBackStack(null);
                ft9.commit();
                String[] value = {code, category};
                Bundle bundle2 = new Bundle();
                bundle2.putStringArray("code", value);
                news.setArguments(bundle2);
            }
        });


    }
}
