package com.example.unpigeon.listen.list;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SlipAdapter extends RecyclerView.Adapter<SlipAdapter.ViewHolder>{
    private List<RecordPiece> mRecordPieces;
    public SlipAdapter(List<RecordPiece> recordPieces) {
        mRecordPieces = recordPieces;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mRecordPieces == null ? 0 : mRecordPieces.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
