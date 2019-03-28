package com.example.unpigeon.user.register;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements UserContract.RegisterView, View.OnClickListener {
    private RegisterPresenter mRegisterPresenter;
    private EditText mEditUsername;
    private EditText mEdtEmail;
    private EditText mEdtCheckNum;
    private Button mBtnGetCheckNum;
    private ImageView mBtnRegister;
    private Button mBtnUsePhoneToRegister;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);
        init(root);
        return root;
    }

    private void init(View root) {
          mEditUsername = root.findViewById(R.id.fra_reg_username);
          mEdtEmail = root.findViewById(R.id.fra_reg_password);
          mEdtCheckNum = root.findViewById(R.id.fra_reg_check_num);
          mBtnGetCheckNum = root.findViewById(R.id.fra_reg_get_check_num);
          mBtnGetCheckNum.setOnClickListener(this);
          mBtnRegister = root.findViewById(R.id.fra_reg_register);
          mBtnRegister.setOnClickListener(this);
          mBtnUsePhoneToRegister = root.findViewById(R.id.fra_reg_change_to_phone);
          mBtnUsePhoneToRegister.setOnClickListener(this);
    }

    public void setPresenter(@NonNull RegisterPresenter registerPresenter) {
        this.mRegisterPresenter = registerPresenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fra_reg_get_check_num:
                break;
            case R.id.fra_reg_register:
                mRegisterPresenter.performRegister();
                break;
            case R.id.fra_reg_change_to_phone:
                break;
        }
    }

    @Override
    public String getUsername() {
        return mEditUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEdtEmail.getText().toString();
    }

    @Override
    public String getCheckNumber() {
        return mEdtCheckNum.getText().toString();
    }

    @Override
    public void toast(String content) {
        Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();;
    }

    @Override
    public void onRegisterSuccess() {
        // TODO: 3/28/19
    }

    @Override
    public void onRegisterDefeat() {
        // TODO: 3/28/19
    }
}
