package com.kaleb.pokedex.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
    private ProgressBar progressBar;
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
        progressBar = findViewById(R.id.itemProgressBar);

        editText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String inputResult = editText.getText().toString();
                closeKeyboard();
                pokemonPresenter.getPokemonName(inputResult);
                return true;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputResult = editText.getText().toString();
                closeKeyboard();
                pokemonPresenter.getPokemonName(inputResult);
            }
        });

    }

    @Override
    public void showPokemon(String pokemonName, String pokemonImg, Boolean showLayout) {

    }

    @Override
    public void showLoading(Boolean showLoading) {
        progressBar.setVisibility(showLoading? View.VISIBLE: View.GONE);
    }

    @Override
    public void showToast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    public void closeKeyboard() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }
    }

}
