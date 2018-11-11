package com.kaleb.pokedex.main.Pokemon;

import com.kaleb.pokedex.data.model.Result;

import java.util.List;

public interface PokemonViewContract {
    void addPokemonResults(List<Result> results);
    void showAllPokemonList(Boolean showLayout);
    void showPokemon(String pokemonName, String pokemonImg, Boolean showLayout);
    void showLoading(Boolean showLoading);
    void showToast(String toast);
}
