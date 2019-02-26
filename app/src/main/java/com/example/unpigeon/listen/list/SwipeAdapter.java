package com.example.unpigeon.listen.list;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unpigeon.R;
import com.example.unpigeon.record.RecordActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeAdapter extends RecyclerView.Adapter<SwipeAdapter.ViewHolder>{
    private List<RecordPiece> mRecordPieces;
    public SwipeAdapter(List<RecordPiece> recordPieces) {
        mRecordPieces = recordPieces;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_item, parent, false);
        final SwipeAdapter.ViewHolder viewHolder =new SwipeAdapter.ViewHolder(view);
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // with information
                Intent intent = new Intent(parent.getContext(), RecordActivity.class);
                parent.getContext().startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // TODO: 2/26/19
    }

    @Override
    public int getItemCount() {
        return mRecordPieces == null ? 0 : mRecordPieces.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }
    }
}
