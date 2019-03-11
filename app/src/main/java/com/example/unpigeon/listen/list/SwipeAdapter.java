package com.example.unpigeon.listen.list;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.unpigeon.R;
import com.example.unpigeon.listen.ListenActivity;
import com.example.unpigeon.record.RecordActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeAdapter extends RecyclerView.Adapter<SwipeAdapter.ViewHolder>{
    private List<RecordPiece> mRecordPieces;
    private static final String TAG = "moanbigking";
    SwipeAdapter(List<RecordPiece> recordPieces) {
        mRecordPieces = recordPieces;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_item, parent, false);
        final SwipeAdapter.ViewHolder viewHolder =new SwipeAdapter.ViewHolder(view);
        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 3/11/19  send broadcast to remove the piece
            }
        });
        viewHolder.renameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 3/11/19 send broadcast to rename the piece
            }
        });
        viewHolder.summaryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parent.getContext().sendBroadcast(new Intent("change-to-list"));
                Log.d(TAG, "onClick: ");
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecordPiece recordPiece = mRecordPieces.get(position);
        String tmp = recordPiece.getName();
        holder.summaryView.setText(tmp);
    }

    @Override
    public int getItemCount() {
        return mRecordPieces == null ? 0 : mRecordPieces.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        Button renameButton;
        Button deleteButton;
        TextView summaryView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            renameButton = mView.findViewById(R.id.fra_item_rename);
            deleteButton = mView.findViewById(R.id.fra_item_delete);
            summaryView = mView.findViewById(R.id.fra_item_text);
        }
    }
}
