package com.kaleb.pokedex.main.Details;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kaleb.pokedex.PokedexApplication;
import com.kaleb.pokedex.R;

public class DetailsViewActivity extends AppCompatActivity implements DetailsViewContract{

    private ImageView imageView;
    private TextView pokemonName, pokeType, pokeAbility, pokeFormNumber;
    private TextView types, abilities, formNumber, moves;
    private ListView moveList;
    private DetailsPreseter detailsPreseter;

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

        detailsPreseter.getPokemonDetails(value);

    }

    @Override
    public void setPokemonDetails(String name, String img, String type, String ability, int form) {
        pokemonName.setText(name);
        pokeType.setText(type);
        pokeAbility.setText(ability);
//        pokeFormNumber.setText(form);
    }

    @Override
    public void showToast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
}
