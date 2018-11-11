package com.kaleb.pokedex.main.Details;

import com.kaleb.pokedex.data.RemoteRepository;
import com.kaleb.pokedex.data.response.PokemonDetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsPreseter implements DetailsPresenterContract {

    private RemoteRepository remoteRepository;
    private DetailsViewContract view;
    private String caps;
    private String types;
    private String abilities;


    public DetailsPreseter(DetailsViewContract view, RemoteRepository remoteRepository) {
        this.view = view;
        this.remoteRepository = remoteRepository;

    }

    @Override
    public void getPokemonDetails(String name) {
        remoteRepository.getPokemonString(name).enqueue(new Callback<PokemonDetailsResponse>() {
            @Override
            public void onResponse(Call<PokemonDetailsResponse> call, Response<PokemonDetailsResponse> response) {
                if (response.code() == 200) {
                    caps = capitalizeText(response.body().getName());
                    if (response.body().getTypes().size() > 1) {
                        types = typeBuilder(response);
                    } else {
                        types = response.body().getTypes().get(0).getType().getName();
                    }

                    if (response.body().getAbilities().size() > 1 ){
                        abilities = abilityBuilder(response);
                    } else {
                        abilities = response.body().getAbilities().get(0).getAbility().getName();
                    }
                    view.setPokemonDetails(caps, response.body().getSprites().getFrontDefault(), types, abilities, response.body().getForms().size());
                } else {
                    view.showToast(String.valueOf(response.code()) + " error!");
                }
//                view.showLoading(false);
            }

            @Override
            public void onFailure(Call<PokemonDetailsResponse> call, Throwable t) {
                view.showToast("There's no Pokemon with that ID");
//                view.showLoading(false);
            }
        });
    }

    String capitalizeText(String text) {
        text = text.substring(0, 1).toUpperCase() + text.substring(1);
        return text;
    }

    String typeBuilder(Response<PokemonDetailsResponse> response){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < response.body().getTypes().size(); i++) {
            String str = response.body().getTypes().get(i).getType().getName();
            sb.append(str.toString());
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length()-1);
        return types = sb.toString();
    }

    String abilityBuilder(Response<PokemonDetailsResponse> response){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < response.body().getAbilities().size(); i++) {
            String str = response.body().getAbilities().get(i).getAbility().getName();
            sb.append(str.toString());
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length()-1);
        return abilities = sb.toString();
    }
}
