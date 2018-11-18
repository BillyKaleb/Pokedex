package com.kaleb.pokedex.main.Pokemon.Presenter;

import android.os.Handler;
import android.util.Log;

import com.kaleb.pokedex.data.RemoteRepository;
import com.kaleb.pokedex.data.model.Result;
import com.kaleb.pokedex.data.response.PokemonDetailsResponse;
import com.kaleb.pokedex.data.response.PokemonResponse;
import com.kaleb.pokedex.main.Pokemon.Activity.PokemonViewContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonPresenter implements PokemonPresenterContract {

    private PokemonViewContract view;
    private RemoteRepository remoteRepository;
    private String caps, nameUrl;
    private List<Result> allResultList;
    private List<Result> showResultList = new ArrayList<>();
    private static int pageCounter;
    private static int totalCount;

    public PokemonPresenter(PokemonViewContract pokemonViewContract, RemoteRepository remoteRepository) {
        this.view = pokemonViewContract;
        this.remoteRepository = remoteRepository;
    }

    @Override
    public void getPokemonName(String name) {
        if (name != null && !name.trim().equals("")) {
            view.showLoading(true);
            try {
                int id = Integer.parseInt(name);
                remoteRepository.getPokemonInt(id).enqueue(new Callback<PokemonDetailsResponse>() {
                    @Override
                    public void onResponse(Call<PokemonDetailsResponse> call, Response<PokemonDetailsResponse> response) {
                        if (response.code() == 200) {
                            nameUrl = response.body().getName();
                            caps = capitalizeText(response.body().getName());
                            view.showPokemon(caps, response.body().getSprites().getFrontDefault(), true);
                        } else if (response.code() == 404) {
                            view.showToast("There's no Pokemon with that ID");
                        } else {
                            view.showToast(String.valueOf(response.code()) + " error!");
                        }
                        view.showLoading(false);
                    }

                    @Override
                    public void onFailure(Call<PokemonDetailsResponse> call, Throwable t) {
                        view.showToast("There's no Pokemon with that ID");
                        view.showLoading(false);
                    }
                });

            } catch (NumberFormatException nfe) {
                remoteRepository.getPokemonString(name).enqueue(new Callback<PokemonDetailsResponse>() {
                    @Override
                    public void onResponse(Call<PokemonDetailsResponse> call, Response<PokemonDetailsResponse> response) {
                        if (response.code() == 200) {
                            nameUrl = response.body().getName();
                            caps = capitalizeText(response.body().getName());
                            view.showPokemon(caps, response.body().getSprites().getFrontDefault(), true);
                        } else if (response.code() == 404) {
                            view.showToast("There's no Pokemon with that name");
                        } else {
                            view.showToast(String.valueOf(response.code()) + " error!");
                        }
                        view.showLoading(false);
                    }

                    @Override
                    public void onFailure(Call<PokemonDetailsResponse> call, Throwable t) {
                        view.showToast("There's no Pokemon with that name");
                        view.showLoading(false);
                    }
                });
            }
        } else {
            view.showToast("Please type in something first");
        }
    }

    @Override
    public void getFullPokemonList() {
        view.showAllPokemonList(true);
        view.showLoading(true);
        remoteRepository.getPokemonList().enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                totalCount = response.body().getCount();
                allResultList = new ArrayList<>(response.body().getResults());
                pageCounter = 1;
                showPokemonList();
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void showPokemonList() {
        view.showLoading(true);
        // +1 below added because pageCounter go inside this first before proceeding
        if(pageCounter <= (totalCount / 20) + 1){
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showResultList.clear();
                    int page = pageCounter * 20;
                    for (int x = 20; x > 0; x--) {
                        showResultList.add(allResultList.get(page - x));
                    }
                    view.showLoading(false);
                    view.addPokemonResults(showResultList);
                    pageCounter++;
                }
            }, 2000);
        } else {
            view.showLoading(false);
            view.showToast("Limit reached!");
        }

    }

    @Override
    public void onPokemonClick() {
        view.openDetailsActivity(nameUrl);
    }

    private String capitalizeText(String text) {
        text = text.substring(0, 1).toUpperCase() + text.substring(1);
        return text;
    }
}
