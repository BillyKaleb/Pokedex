package com.kaleb.pokedex.main;

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

    }
}
