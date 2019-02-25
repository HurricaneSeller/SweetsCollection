package com.example.unpigeon;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.unpigeon.record.RecordActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {
    private List<VoicePieceTask> mVoicePieceTasks;

    public BaseAdapter(List<VoicePieceTask> voicePieceTasks) {
        mVoicePieceTasks = voicePieceTasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.voice_piece_item, parent, false);
        final BaseAdapter.ViewHolder viewHolder =new BaseAdapter.ViewHolder(view);
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
        VoicePieceTask voicePieceTask = mVoicePieceTasks.get(position);
        String content = voicePieceTask.getContent();
        holder.mTitleView.setText(content);
    }

    @Override
    public int getItemCount() {
        return mVoicePieceTasks == null ? 0 : mVoicePieceTasks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleView;
        Button mIsFinished;
        View mView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView.findViewById(R.id.item_piece);
            mTitleView = itemView.findViewById(R.id.piece_content);
            mIsFinished = itemView.findViewById(R.id.piece_status);
        }
    }
}
