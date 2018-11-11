package com.kaleb.pokedex.main.Details;

public interface DetailsViewContract {

    void setPokemonDetails(String name, String img, String type, String ability, int form);
    void showToast(String toast);
    void showLayout(Boolean showLayout);
    void showProgressBar(Boolean show);
}
