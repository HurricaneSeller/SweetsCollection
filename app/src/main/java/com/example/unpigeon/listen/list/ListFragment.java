package com.example.unpigeon.listen.list;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.unpigeon.R;
import com.example.unpigeon.listen.ListenContract;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements ListenContract.ListView, View.OnClickListener {
    private ListPresenter mListPresenter;
    private ImageButton mTaskButton;
    private ImageButton mRecordButton;//cannot be reached;
    private RecyclerView mRecyclerView;
    private static final String TAG = "SWEET";

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_list, container, false);
        init(root);
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void init(View view) {
        mTaskButton = view.findViewById(R.id.main_task2);
        mTaskButton.setOnClickListener(this);
        mRecyclerView = view.findViewById(R.id.fra_list_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<RecordPiece> sample = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            sample.add(new RecordPiece());
        }
        mRecyclerView.setAdapter(new SwipeAdapter(sample));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_task2:
                shutdown();
                break;
            case R.id.fra_list_recycler_view:
                break;
        }
    }

    @Override
    public void setPresenter(ListPresenter listPresenter) {
        mListPresenter = listPresenter;
        if (mListPresenter == null) {
            shutdown();
        }
    }

    private void shutdown() {
        getActivity().finish();
    }
}
