package com.kaleb.pokedex.main.Details;

import com.kaleb.pokedex.data.model.Move_;

import java.util.List;

public interface DetailsViewContract {

    void setPokemonDetails(String name, String img, String type, String ability, int form, List<Move_> moveList);
    void showToast(String toast);
    void showLayout(Boolean showLayout);
    void showProgressBar(Boolean show);
}
