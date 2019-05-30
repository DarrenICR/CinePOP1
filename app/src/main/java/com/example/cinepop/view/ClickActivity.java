package com.example.cinepop.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cinepop.R;

public class ClickActivity extends AppCompatActivity {

    TextView tv;
    String strOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);

        tv = findViewById(R.id.movie_overview);
        strOverview=getIntent().getExtras().getString("Overview");
        tv.setText(strOverview);
    }
}
