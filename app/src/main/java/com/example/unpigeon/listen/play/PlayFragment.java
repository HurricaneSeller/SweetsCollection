package com.example.unpigeon.listen.play;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unpigeon.R;

/**
 * A simple {@link Fragment} subclass.
 *
 * background playing:make a remoteview
 * simplified music player
 *
 */
public class PlayFragment extends Fragment {


    public PlayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play, container, false);
    }

}
