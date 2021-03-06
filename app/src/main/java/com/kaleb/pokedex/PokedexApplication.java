package com.kaleb.pokedex;

import android.app.Application;

import com.kaleb.pokedex.data.PokedexService;
import com.kaleb.pokedex.data.RemoteRepository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokedexApplication extends Application {

    private static PokedexService pokedexService;
    private static RemoteRepository remoteRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        remoteRepository = new RemoteRepository();
        apiNetwork();
    }

    public void apiNetwork() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pokedexService = retrofit.create(PokedexService.class);
    }

    public static PokedexService getPokedexService() {
        return pokedexService;
    }

    public static RemoteRepository getRemoteRepository() {
        return remoteRepository;
    }
}
