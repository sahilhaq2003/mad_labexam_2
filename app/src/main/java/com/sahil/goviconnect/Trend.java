package com.sahil.goviconnect;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

public class Trend extends AppCompatActivity {

    private Button btnHome;
    private Button btnTrends;
    private Button btnInsights;
    private Button btnSearch;
    private Button btnProfile;


    @ColorInt private static final int COLOR_SELECTED = 0xFFB5A38F; // #B5A38F
    @ColorInt private static final int COLOR_NORMAL   = 0xFFFFFFFF; // #FFFFFF

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main7);

        btnHome     = findViewById(R.id.button8);
        btnTrends   = findViewById(R.id.button6);
        btnInsights = findViewById(R.id.button9);
        btnSearch   = findViewById(R.id.button10);
        btnProfile  = findViewById(R.id.button11);

        View.OnClickListener navClick = v -> {
            clearSelection();
            if (v instanceof Button) setButtonTint((Button) v, COLOR_SELECTED);

            int id = v.getId();
            if (id == R.id.button8) {
                toast("Home");

            } else if (id == R.id.button6) {
                toast("Trends");
            } else if (id == R.id.button9) {
                toast("Insights");
            } else if (id == R.id.button10) {
                toast("Search");
            } else if (id == R.id.button11) {
                toast("Profile");
            }
        };

        btnHome.setOnClickListener(navClick);
        btnTrends.setOnClickListener(navClick);
        btnInsights.setOnClickListener(navClick);
        btnSearch.setOnClickListener(navClick);
        btnProfile.setOnClickListener(navClick);


        setButtonTint(btnInsights, COLOR_SELECTED);
    }

    private void clearSelection() {
        setButtonTint(btnHome, COLOR_NORMAL);
        setButtonTint(btnTrends, COLOR_NORMAL);
        setButtonTint(btnInsights, COLOR_NORMAL);
        setButtonTint(btnSearch, COLOR_NORMAL);
        setButtonTint(btnProfile, COLOR_NORMAL);
    }

    private void setButtonTint(Button b, @ColorInt int color) {
        b.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
