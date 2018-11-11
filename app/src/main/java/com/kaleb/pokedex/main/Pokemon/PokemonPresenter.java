package com.kaleb.pokedex.main.Pokemon;

import com.kaleb.pokedex.data.RemoteRepository;
import com.kaleb.pokedex.data.response.PokemonDetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonPresenter implements PokemonPresenterContract {

    private PokemonViewContract view;
    private RemoteRepository remoteRepository;
    private String caps;

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
                            caps = capitalizeText(response.body().getName());
                            view.showPokemon(caps, response.body().getSprites().getFrontDefault(), true);
                            view.showToast("Found a pokemon with that ID!");
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
                            caps = capitalizeText(response.body().getName());
                            view.showPokemon(caps, response.body().getSprites().getFrontDefault(), true);
                            view.showToast("Found a pokemon with that name!");
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

    String capitalizeText(String text){
        text = text.substring(0, 1).toUpperCase() + text.substring(1);
        return text;
    }
}
