package com.example.user.weatherapp.Base_classes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.user.weatherapp.R;

public class BaseActivity extends AppCompatActivity {

    /**
     * Common method used to fragment transaction while adding fragment to back stack
     * @param fragment
     */
    public void changefragment(BaseFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * Common method used to fragment transaction without adding fragment to back stack
     * @param fragment
     */
    public void changefragment2(BaseFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main, fragment);
        fragmentTransaction.commit();
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

