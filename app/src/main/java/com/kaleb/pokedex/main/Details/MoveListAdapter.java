package com.kaleb.pokedex.main.Details;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kaleb.pokedex.R;
import com.kaleb.pokedex.data.model.Move_;

import java.util.ArrayList;
import java.util.List;

public class MoveListAdapter extends ArrayAdapter<Move_> {
    public MoveListAdapter(@NonNull Context context, List<Move_> resource) {
        super(context, 0, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Move_ move = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_move_list_adapter, parent, false);
        }
        // Lookup view for data population
        TextView moveList = (TextView) convertView.findViewById(R.id.moveListText);
        // Populate the data into the template view using the data object
        moveList.setText(move.getName());
        // Return the completed view to render on screen
        return convertView;
    }

}
