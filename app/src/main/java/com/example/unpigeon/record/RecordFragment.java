package com.example.unpigeon.record;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unpigeon.R;

import androidx.fragment.app.Fragment;

/**
 * bottomsheetdialog
 * ok, i find this..
 * https://github.com/dkim0419/SoundRecorder
 * but this might be too huge
 * ni you hao de ji de fen xiang, dou xing (pin yin
 */
public class RecordFragment extends Fragment {

    public RecordFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record, container, false);
    }

}
