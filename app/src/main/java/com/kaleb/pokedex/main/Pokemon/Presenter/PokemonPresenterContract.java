package com.kaleb.pokedex.main.Pokemon.Presenter;

public interface PokemonPresenterContract {
    void getPokemonName(String name);
    void getFullPokemonList();
    void showPokemonList();
    void onPokemonClick();
}
