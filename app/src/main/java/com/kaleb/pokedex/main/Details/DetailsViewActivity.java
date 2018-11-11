package com.kaleb.pokedex.main.Details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kaleb.pokedex.R;

public class DetailsViewActivity extends AppCompatActivity implements DetailsViewContract{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }
}
