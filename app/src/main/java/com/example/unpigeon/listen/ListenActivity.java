package com.example.unpigeon.listen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.example.unpigeon.R;
import com.example.unpigeon.listen.list.ListFragment;
import com.example.unpigeon.listen.list.ListPresenter;
import com.example.unpigeon.listen.play.PlayFragment;
import com.example.unpigeon.listen.play.PlayPresenter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ListenActivity extends AppCompatActivity {
    private ListPresenter mListPresenter;
    private PlayPresenter mPlayPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);
        hideActionbar();
        setListFragment();
        registerReceiver(mBroadcastReceiver, new IntentFilter("change-to-list"));
    }


    private void hideActionbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void setListFragment() {
        ListFragment listFragment = new ListFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack("first-stack");
        transaction.replace(R.id.listen_main_frame_layout, listFragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("change-to-list".equals(action)) {
                PlayFragment playFragment = new PlayFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack("second-stack");
                transaction.replace(R.id.listen_main_frame_layout, playFragment );
                transaction.commit();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }
}
