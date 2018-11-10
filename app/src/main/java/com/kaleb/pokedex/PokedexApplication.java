package com.kaleb.pokedex;

import android.app.Application;

import com.kaleb.pokedex.data.response.PokedexService;
import com.kaleb.pokedex.data.response.RemoteRepository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokedexApplication extends Application {

    private PokedexService pokedexService;
    private RemoteRepository remoteRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        remoteRepository = new RemoteRepository();
        apiNetwork();
    }

    public void apiNetwork() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pokedexService = retrofit.create(PokedexService.class);
    }

    public PokedexService getPokedexService() {
        return pokedexService;
    }

    public RemoteRepository getRemoteRepository() {
        return remoteRepository;
    }
}
