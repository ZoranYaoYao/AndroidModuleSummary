package com.example.zoran.drawerlayout;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Locale;

/**
 * Created by zqs on 2018/3/8.
 */

public class PlanetFragment extends Fragment {

    public static final String ARG_PLANET_NUMBER = "planet_number";

    public PlanetFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_planet,container,false);
        int i = getArguments().getInt(ARG_PLANET_NUMBER);
        String planet = getResources().getStringArray(R.array.planets_array)[i];
        int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
                "drawable", getActivity().getPackageName());
        ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
        getActivity().setTitle(planet);
        return rootView;
    }
}
