package com.kaleb.pokedex.data;

import com.kaleb.pokedex.data.response.PokemonDetailsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokedexService {

    @GET("pokemon/{pokemon}")
    Call<PokemonDetailsResponse> pokemonString(@Path("pokemon") String pokemonName);

    @GET("pokemon/{pokemon}")
    Call<PokemonDetailsResponse> pokemonInt(@Path("pokemon") int pokemonId);
}
