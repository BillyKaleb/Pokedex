package com.kaleb.pokedex.main;

import android.util.Log;

import com.google.gson.Gson;
import com.kaleb.pokedex.data.RemoteRepository;
import com.kaleb.pokedex.data.response.PokemonDetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonPresenter implements PokemonPresenterContract {

    private PokemonViewContract view;
    private RemoteRepository remoteRepository;

    public PokemonPresenter(PokemonViewContract pokemonViewContract, RemoteRepository remoteRepository) {
        this.view = pokemonViewContract;
        this.remoteRepository = remoteRepository;
    }

    @Override
    public void getPokemonName(String name) {
        try {
            int id = Integer.parseInt(name);
            remoteRepository.getPokemonInt(id).enqueue(new Callback<PokemonDetailsResponse>() {
                @Override
                public void onResponse(Call<PokemonDetailsResponse> call, Response<PokemonDetailsResponse> response) {
                    view.showToast("Found a pokemon with that ID!");
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
                    view.showToast("Found a pokemon with that Name!");
                    view.showLoading(false);
                }

                @Override
                public void onFailure(Call<PokemonDetailsResponse> call, Throwable t) {
                    view.showToast("There's no Pokemon with that Name");
                    view.showLoading(false);
                }
            });
        }
    }
}
