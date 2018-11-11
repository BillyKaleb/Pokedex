package com.kaleb.pokedex.main.Details;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.kaleb.pokedex.R;

public class DetailsViewActivity extends AppCompatActivity implements DetailsViewContract{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        String value = intent.getStringExtra("url");
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
    }
}
