package com.kaleb.pokedex.main.Details;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
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
import com.kaleb.pokedex.data.model.Move_;

import java.util.ArrayList;
import java.util.List;

public class DetailsViewActivity extends AppCompatActivity implements DetailsViewContract {

    private ImageView imageView;
    private TextView pokemonName, pokeType, pokeAbility, pokeFormNumber;
    private TextView types, abilities, formNumber, moves;
    private ListView moveList;
    private DetailsPreseter detailsPreseter;
    private RelativeLayout relativeLayout;
    private ProgressBar progressBar;
    private MoveListAdapter moveListAdapter;
    private List<Move_> arrayMove;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        String value = intent.getStringExtra("url");

        detailsPreseter = new DetailsPreseter(this, PokedexApplication.getRemoteRepository());
        arrayMove = new ArrayList<>();
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
        listView = findViewById(R.id.moveList);

        listView.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        detailsPreseter.getPokemonDetails(value);

    }

    @Override
    public void setPokemonDetails(String name, String img, String type, String ability, int form, List<Move_> moveList) {
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
        arrayMove = moveList;
        moveListAdapter = new MoveListAdapter(this, arrayMove);
        Log.d("TAG", "setPokemonDetails: " + arrayMove.size());
        listView.setAdapter(moveListAdapter);
    }

    @Override
    public void showToast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLayout(Boolean showLayout) {
        relativeLayout.setVisibility(showLayout ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showProgressBar(Boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
