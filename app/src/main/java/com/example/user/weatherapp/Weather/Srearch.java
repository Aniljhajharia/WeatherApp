package com.example.user.weatherapp.Weather;


import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.weatherapp.Base_classes.BaseFragment;
import com.example.user.weatherapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Srearch extends BaseFragment {
    AutoCompleteTextView textView;
    String ctry;
    TextView tv;
    int animationDuration=2000;
    int startSize,endSize;
    public Srearch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_srearch, container, false);
        textView = (AutoCompleteTextView) v.findViewById(R.id.Search);

        String[] countries = getResources().getStringArray(R.array.countries_array);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, countries);
        textView.setAdapter(adapter);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button search = (Button) view.findViewById(R.id.details);
        tv=(TextView) view.findViewById(R.id.head);
        startSize=35;
        endSize=34;
        ValueAnimator animator = ValueAnimator.ofFloat(startSize,endSize);
        animator.setDuration(animationDuration);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                tv.setTextSize(animatedValue);
            }
        });
        animator.start();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Detail_fragment detail_fragment = new Detail_fragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.setCustomAnimations(R.anim.fad_in, R.anim.fad_out);
                ft.replace(R.id.main, detail_fragment);
                ft.addToBackStack(null);
                ft.commit();
                ctry = textView.getText().toString();
                Log.d("TAG","country"+ctry);
                Bundle bundle = new Bundle();
                bundle.putString("key", ctry);
                detail_fragment.setArguments(bundle);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}
