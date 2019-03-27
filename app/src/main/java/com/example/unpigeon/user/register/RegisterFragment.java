package com.example.unpigeon.user.register;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unpigeon.R;
import com.example.unpigeon.user.UserContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements UserContract.RegisterView {
    private RegisterPresenter mRegisterPresenter;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);
        init();
        return root;
    }

    private void init() {

    }

    public void setPresenter(@NonNull RegisterPresenter registerPresenter) {
        this.mRegisterPresenter = registerPresenter;
    }
}
