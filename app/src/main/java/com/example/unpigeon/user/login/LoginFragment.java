package com.example.unpigeon.user.login;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.unpigeon.R;
import com.example.unpigeon.user.UserContract;
import com.example.unpigeon.utils.Utils;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements UserContract.LoginView, View.OnClickListener {
    private LoginPresenter mLoginPresenter;
    private ImageView mBtnLogin;
    private Button mBtnRegister;
    private EditText mEdtUsername;
    private EditText mEdtPassword;
    private EditText mEditCheckNumber;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        init(root);
        return root;
    }

    private void init(View root) {
        mEdtUsername = root.findViewById(R.id.edt_fra_login_username);
        mEdtPassword = root.findViewById(R.id.edt_fra_login_password);
        mEditCheckNumber = root.findViewById(R.id.edt_fra_login_check_number);
        mBtnLogin = root.findViewById(R.id.img_btn_fra_login);
        mBtnRegister = root.findViewById(R.id.btn_fra_register);

        //set on click listener
        mBtnLogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
    }

    public void setPresenter(@NonNull LoginPresenter loginPresenter) {
        this.mLoginPresenter = loginPresenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_fra_login:
                break;
            case R.id.btn_fra_register:
                break;

        }
    }

    @Override
    public String getUsername() {
        return mEdtUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        String basic =  mEdtPassword.getText().toString();
        return Utils.getMD5(basic);
    }

    @Override
    public String getCheckNumber() {
        return mEditCheckNumber.getText().toString();
    }

    @Override
    public void toast(String content) {
        Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginDefeat() {
        mEdtPassword.setText("");
        mEdtUsername.setText("");
        mEditCheckNumber.setText("");
    }

    @Override
    public void onLoginSuccess() {
        Objects.requireNonNull(getActivity()).finish();
    }
}
