package com.kaleb.pokedex.data;

import com.kaleb.pokedex.PokedexApplication;
import com.kaleb.pokedex.data.response.PokemonDetailsResponse;
import com.kaleb.pokedex.data.response.PokemonResponse;

import retrofit2.Call;

public class RemoteRepository {

    public Call<PokemonDetailsResponse> getPokemonString(String pokemonName){
        return PokedexApplication.getPokedexService().pokemonString(pokemonName);
    }

    public Call<PokemonDetailsResponse> getPokemonInt(int pokemonId){
        return PokedexApplication.getPokedexService().pokemonInt(pokemonId);
    }

    public Call<PokemonResponse> getPokemonList(){
        return PokedexApplication.getPokedexService().pokemonList();
    }
}
