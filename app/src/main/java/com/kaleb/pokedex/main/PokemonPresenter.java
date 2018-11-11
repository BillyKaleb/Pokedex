package com.kaleb.pokedex.main;

import com.kaleb.pokedex.data.RemoteRepository;

public class PokemonPresenter {

    private PokemonContract pokemonContract;
    private RemoteRepository remoteRepository;

    public PokemonPresenter(PokemonContract pokemonContract, RemoteRepository remoteRepository) {
        this.pokemonContract = pokemonContract;
        this.remoteRepository = remoteRepository;
    }
}
