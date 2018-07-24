package com.example.user.weatherapp.Base_classes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.user.weatherapp.R;

public class BaseActivity extends AppCompatActivity {


    public void changefragment(BaseFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public void changefragment2(BaseFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main, fragment);
        fragmentTransaction.commit();
    }
    public void bundle(BaseFragment fragment, String tag) {
        Bundle bundle = new Bundle();
        bundle.putString("key", tag);
        fragment.setArguments(bundle);
    }

    /**
     * Common method used to switch the activity
     *
     * @param bundle
     * @param activity
     */
    public void switchActivity(Bundle bundle, Activity activity) {
        Intent intent = new Intent(this, activity.getClass());
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
    }

}

