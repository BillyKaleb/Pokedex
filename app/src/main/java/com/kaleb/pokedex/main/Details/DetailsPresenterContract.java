package com.kaleb.pokedex.main.Details;

import com.kaleb.pokedex.data.response.PokemonDetailsResponse;

import retrofit2.Response;

public interface DetailsPresenterContract {

    void getPokemonDetails(String name);
    void getListHeight(Response<PokemonDetailsResponse> response);
}
