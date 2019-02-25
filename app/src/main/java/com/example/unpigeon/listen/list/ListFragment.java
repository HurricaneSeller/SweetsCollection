package com.example.unpigeon.listen.list;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.unpigeon.R;
import com.example.unpigeon.listen.ListenContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements ListenContract.ListView {
    private ImageButton mTaskButton;
    private ImageButton mRecordButton;//cannot be reached;


    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    private void init() {

    }
}
