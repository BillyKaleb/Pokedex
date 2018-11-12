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

import java.util.List;

public class FormListAdapter extends ArrayAdapter<String> {
    public FormListAdapter(@NonNull Context context, List<String> resource) {
        super(context, 0, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String forms = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_form_list_adapter, parent, false);
        }
        // Lookup view for data population
        TextView formList = convertView.findViewById(R.id.formListText);
        // Populate the data into the template view using the data object
        formList.setText(forms);
        // Return the completed view to render on screen
        return convertView;
    }
}
