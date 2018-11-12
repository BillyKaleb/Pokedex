package com.kaleb.pokedex.main.Pokemon.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kaleb.pokedex.main.Pokemon.EndlessRecyclerViewOnScrollListener;
import com.kaleb.pokedex.PokedexApplication;
import com.kaleb.pokedex.R;
import com.kaleb.pokedex.main.Pokemon.RecyclerAdapter;
import com.kaleb.pokedex.data.model.Result;
import com.kaleb.pokedex.main.Details.Activity.DetailsViewActivity;
import com.kaleb.pokedex.main.Pokemon.Presenter.PokemonPresenter;

import java.util.ArrayList;
import java.util.List;

public class PokemonViewActivity extends AppCompatActivity implements PokemonViewContract {

    private RelativeLayout relativeLayout;
    private EditText editText;
    private Button button, backListButton;
    private ImageView imageView;
    private TextView textView;
    private ProgressBar progressBar;
    private PokemonPresenter pokemonPresenter;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private List<Result> resultList;
    private EndlessRecyclerViewOnScrollListener endlessRecyclerViewOnScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        pokemonPresenter = new PokemonPresenter(this, PokedexApplication.getRemoteRepository());
        relativeLayout = findViewById(R.id.relativeLayoutClick);
        editText = findViewById(R.id.editTextPokemon);
        button = findViewById(R.id.buttonPokemon);
        backListButton = findViewById(R.id.backToList);
        imageView = findViewById(R.id.imageViewPokemon);
        textView = findViewById(R.id.pokemonName);
        progressBar = findViewById(R.id.itemProgressBar);
        recyclerView = findViewById(R.id.recyclerView);
        endlessRecyclerViewOnScrollListener = new EndlessRecyclerViewOnScrollListener() {
            @Override
            public void onLoadMore() {
                pokemonPresenter.showPokemonList();
            }
        };

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        resultList = new ArrayList<>();
        recyclerAdapter = new RecyclerAdapter(resultList);
        recyclerView.setAdapter(recyclerAdapter);
        pokemonPresenter.getFullPokemonList();

        recyclerView.addOnScrollListener(endlessRecyclerViewOnScrollListener);

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

        backListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultList.clear();
                recyclerAdapter.cleanList();
                endlessRecyclerViewOnScrollListener.resetListener();
                pokemonPresenter.getFullPokemonList();
            }
        });

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pokemonPresenter.onPokemonClick();
            }
        });
    }

    @Override
    public void addPokemonResults(List<Result> results) {
        recyclerAdapter.addList(results);
    }

    @Override
    public void showAllPokemonList(Boolean showLayout) {
        relativeLayout.setVisibility(showLayout ? View.GONE : View.VISIBLE);
        backListButton.setVisibility(showLayout ? View.GONE : View.VISIBLE);
        recyclerView.setVisibility(showLayout ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showPokemon(String pokemonName, String pokemonImg, Boolean showLayout) {
        relativeLayout.setVisibility(showLayout ? View.VISIBLE : View.GONE);
        backListButton.setVisibility(showLayout ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(showLayout ? View.GONE : View.VISIBLE);
        Glide
                .with(imageView.getContext())
                .load(pokemonImg)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_img_placeholders))
                .into(imageView);
        textView.setText(pokemonName);
    }

    @Override
    public void showLoading(Boolean showLoading) {
        progressBar.setVisibility(showLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showToast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openDetailsActivity(String name) {
        Intent myIntent = new Intent(PokemonViewActivity.this, DetailsViewActivity.class);
        myIntent.putExtra("url", name); //Optional parameters
        PokemonViewActivity.this.startActivity(myIntent);
    }

    public void closeKeyboard() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }
    }

}
