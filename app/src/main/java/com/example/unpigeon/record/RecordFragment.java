package com.example.unpigeon.record;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unpigeon.R;

import androidx.fragment.app.Fragment;

/**
 * bottomsheetdialog
 * ok, i find this..
 * https://github.com/dkim0419/SoundRecorder
 * but this might be too huge
 * ni you hao de ji de fen xiang, dou xing (pin yin
 */
public class RecordFragment extends Fragment implements View.OnClickListener{
    private TextView mContentView;
    private Button mControlButton;
    private TextView mTitleView;
    private TextView mTimeView;

    public RecordFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_record, container, false);
        init(root);
        return root;
    }

    private void init(View view) {
        mContentView = view.findViewById(R.id.fra_record_text);
        mControlButton = view.findViewById(R.id.fra_record_control);
        mControlButton.setOnClickListener(this);
        mTimeView = view.findViewById(R.id.fra_record_time);
        mTitleView = view.findViewById(R.id.fra_record_title);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fra_record_control:
                Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
