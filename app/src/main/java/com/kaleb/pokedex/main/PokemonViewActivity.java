package com.kaleb.pokedex.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaleb.pokedex.PokedexApplication;
import com.kaleb.pokedex.R;

public class PokemonViewActivity extends AppCompatActivity implements PokemonViewContract {

    private RelativeLayout relativeLayout;
    private EditText editText;
    private Button button;
    private ImageView imageView;
    private TextView textView;
    private PokemonPresenter pokemonPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        pokemonPresenter = new PokemonPresenter(this, PokedexApplication.getRemoteRepository());
        relativeLayout = findViewById(R.id.relativeLayoutClick);
        editText = findViewById(R.id.editTextPokemon);
        button = findViewById(R.id.buttonPokemon);
        imageView = findViewById(R.id.imageViewPokemon);
        textView = findViewById(R.id.pokemonName);
    }

    @Override
    public void showPokemon(String pokemonName, String pokemonImg, Boolean showLayout) {

    }

    @Override
    public void showLoading(Boolean showLoading) {

    }

    @Override
    public void showToast(String toast) {

    }
}
