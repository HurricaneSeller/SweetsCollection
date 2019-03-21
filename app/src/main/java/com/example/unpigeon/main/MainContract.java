package com.example.unpigeon.main;

import android.content.Context;

public interface MainContract {
    interface View{

    }
    interface Presenter{
        void load(Context context);
    }
}
