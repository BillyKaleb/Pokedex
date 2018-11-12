package com.kaleb.pokedex.main.Details.Presenter;

import com.kaleb.pokedex.data.RemoteRepository;
import com.kaleb.pokedex.data.model.Move_;
import com.kaleb.pokedex.data.response.PokemonDetailsResponse;
import com.kaleb.pokedex.main.Details.Activity.DetailsViewContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsPresenter implements DetailsPresenterContract {

    private RemoteRepository remoteRepository;
    private DetailsViewContract view;
    private String caps;
    private String types;
    private String abilities;
    private List<Move_> move = new ArrayList<>();
    private List<String> form = new ArrayList<>();


    public DetailsPresenter(DetailsViewContract view, RemoteRepository remoteRepository) {
        this.view = view;
        this.remoteRepository = remoteRepository;

    }

    @Override
    public void getPokemonDetails(String name) {
        view.showProgressBar(true);
        remoteRepository.getPokemonString(name).enqueue(new Callback<PokemonDetailsResponse>() {
            @Override
            public void onResponse(Call<PokemonDetailsResponse> call, Response<PokemonDetailsResponse> response) {
                if (response.code() == 200) {
                    caps = capitalizeText(response.body().getName());
                    if (response.body().getTypes().size() > 1) {
                        types = typeBuilder(response);
                    } else {
                        types = response.body().getTypes().get(0).getType().getName();
                    }

                    if (response.body().getAbilities().size() > 1) {
                        abilities = abilityBuilder(response);
                    } else {
                        abilities = response.body().getAbilities().get(0).getAbility().getName();
                    }

                    for (int i = 0; i < response.body().getMoves().size(); i++) {
                        move.add(response.body().getMoves().get(i).getMove());
                    }

                    for (int i = 0; i < response.body().getForms().size(); i++) {
                        form.add(response.body().getForms().get(i).getName());
                    }

                    getListHeight(response);
                    view.setPokemonDetails(caps, response.body().getSprites().getFrontDefault(), types, abilities, response.body().getForms().size(), move, form);
                } else {
                    view.showToast(String.valueOf(response.code()) + " error!");
                }
                view.showProgressBar(false);
                view.showLayout(true);
            }

            @Override
            public void onFailure(Call<PokemonDetailsResponse> call, Throwable t) {
                view.showToast("There's no Pokemon with that ID");
                view.showProgressBar(false);
            }
        });
    }

    @Override
    public void getListHeight(Response<PokemonDetailsResponse> response) {
        int formHeight, moveHeight;
        if (response.body().getForms().size() < 5){
            formHeight = response.body().getForms().size() * 100;
        } else {
            formHeight = 500;
        }

        if (response.body().getMoves().size() < 5){
            moveHeight = response.body().getMoves().size() * 100;
        } else {
            moveHeight = 500;
        }
        view.setListHeight(formHeight, moveHeight);
    }

    String capitalizeText(String text) {
        text = text.substring(0, 1).toUpperCase() + text.substring(1);
        return text;
    }

    String typeBuilder(Response<PokemonDetailsResponse> response) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < response.body().getTypes().size(); i++) {
            String str = response.body().getTypes().get(i).getType().getName();
            sb.append(str.toString());
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        return types = sb.toString();
    }

    String abilityBuilder(Response<PokemonDetailsResponse> response) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < response.body().getAbilities().size(); i++) {
            String str = response.body().getAbilities().get(i).getAbility().getName();
            sb.append(str.toString());
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        return abilities = sb.toString();
    }
}
