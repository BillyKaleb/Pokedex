package com.kaleb.pokedex.main.Pokemon;

public interface PokemonViewContract {
    void showPokemon(String pokemonName, String pokemonImg, Boolean showLayout);
    void showLoading(Boolean showLoading);
    void showToast(String toast);
}
