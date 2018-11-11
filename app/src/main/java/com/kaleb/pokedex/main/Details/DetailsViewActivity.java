package com.kaleb.pokedex.main.Details;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kaleb.pokedex.PokedexApplication;
import com.kaleb.pokedex.R;

public class DetailsViewActivity extends AppCompatActivity implements DetailsViewContract{

    private ImageView imageView;
    private TextView pokemonName, pokeType, pokeAbility, pokeFormNumber;
    private TextView types, abilities, formNumber, moves;
    private ListView moveList;
    private DetailsPreseter detailsPreseter;
    private RelativeLayout relativeLayout;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        String value = intent.getStringExtra("url");
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();

        detailsPreseter = new DetailsPreseter(this, PokedexApplication.getRemoteRepository());
        imageView = findViewById(R.id.imageDetail);
        pokemonName = findViewById(R.id.pokemonNameDetails);
        pokeType = findViewById(R.id.typesDetail);
        pokeAbility = findViewById(R.id.abilityDetail);
        pokeFormNumber = findViewById(R.id.formNumber);
        types = findViewById(R.id.types);
        abilities = findViewById(R.id.ability);
        formNumber = findViewById(R.id.form);
        moves = findViewById(R.id.move);
        moveList = findViewById(R.id.moveList);
        relativeLayout = findViewById(R.id.detailsLayout);
        progressBar = findViewById(R.id.itemProgressBar);

        detailsPreseter.getPokemonDetails(value);

    }

    @Override
    public void setPokemonDetails(String name, String img, String type, String ability, int form) {
        Glide
                .with(imageView.getContext())
                .load(img)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_img_placeholders))
                .into(imageView);
        pokemonName.setText(name);
        pokeType.setText(type);
        pokeAbility.setText(ability);
//        pokeFormNumber.setText(form);
    }

    @Override
    public void showToast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLayout(Boolean showLayout){
        relativeLayout.setVisibility(showLayout ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showProgressBar(Boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
