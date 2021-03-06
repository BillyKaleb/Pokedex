package com.kaleb.pokedex.main.Details.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.kaleb.pokedex.main.Details.Presenter.DetailsPresenter;
import com.kaleb.pokedex.main.Details.FormListAdapter;
import com.kaleb.pokedex.main.Details.MoveListAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailsViewActivity extends AppCompatActivity implements DetailsViewContract {

    private ImageView imageView;
    private TextView pokemonName, pokeType, pokeAbility, pokeFormNumber;
    private TextView types, abilities, formNumber, moves;
    private ListView moveList;
    private DetailsPresenter detailsPresenter;
    private RelativeLayout relativeLayout;
    private ProgressBar progressBar;
    private MoveListAdapter moveListAdapter;
    private FormListAdapter formListAdapter;
    private List<Move_> arrayMove;
    private List<String> arrayForm;
    private ListView moveListView, formListView;
    private LinearLayout formLayout, moveLayout;
    private ViewGroup.LayoutParams formParams, moveParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        String value = intent.getStringExtra("url");

        detailsPresenter = new DetailsPresenter(this, PokedexApplication.getRemoteRepository());
        arrayMove = new ArrayList<>();
        arrayForm = new ArrayList<>();
        imageView = findViewById(R.id.imageDetail);
        pokemonName = findViewById(R.id.pokemonNameDetails);
        pokeType = findViewById(R.id.typesDetail);
        pokeAbility = findViewById(R.id.abilityDetail);
        types = findViewById(R.id.types);
        abilities = findViewById(R.id.ability);
        formNumber = findViewById(R.id.form);
        moves = findViewById(R.id.move);
        relativeLayout = findViewById(R.id.detailsLayout);
        progressBar = findViewById(R.id.itemProgressBar);
        moveListView = findViewById(R.id.moveList);
        formListView = findViewById(R.id.formList);
        formLayout = findViewById(R.id.formLinearLayout);
        formParams = formLayout.getLayoutParams();
        moveLayout = findViewById(R.id.moveLinearLayout);
        moveParams = moveLayout.getLayoutParams();

        moveListView.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        formListView.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        detailsPresenter.getPokemonDetails(value);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLoading();
    }

    @Override
    public void setPokemonDetails(String name, String img, String type, String ability, int form, List<Move_> moveList, List<String> formList) {
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
        arrayForm = formList;
        moveListAdapter = new MoveListAdapter(this, arrayMove);
        moveListView.setAdapter(moveListAdapter);
        formListAdapter = new FormListAdapter(this, arrayForm);
        formListView.setAdapter(formListAdapter);
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

    @Override
    public void setListHeight(int form, int move) {
        formParams.height = form;
        formLayout.setLayoutParams(formParams);
        moveParams.height = move;
        moveLayout.setLayoutParams(moveParams);
    }

    @Override
    public void stopLoading() {
        detailsPresenter.stopAPILoading();
    }
}
