package com.example.unpigeon.listen;

public interface ListenContract {
    interface ListView {
        void setPresenter(com.example.unpigeon.listen.list.ListPresenter listPresenter);
    }

    interface ListPresenter {

    }

    interface PlayView {
        void setPresenter(com.example.unpigeon.listen.play.PlayPresenter playPresenter);
        void setText();
    }

    interface PlayPresenter {
        void startPlaying();
    }
}
