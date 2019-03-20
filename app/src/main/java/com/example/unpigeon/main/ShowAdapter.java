package com.example.unpigeon.main;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unpigeon.R;
import com.example.unpigeon.repository.RecordPieceEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ViewHolder> {
    private List<RecordPieceEntity> mRecordPieceEntities;
    private List<IndexRecordPiece> mIndexRecordPieces= new ArrayList<>(3);
    private int[] indexPicturesIndex = {R.mipmap.index1, R.mipmap.index2, R.mipmap.index3};
    private int[] indexPicturesArrows = {R.mipmap.arrow1, R.mipmap.arrow2, R.mipmap.arrow3};
    private static final String TAG = "moanbigking";

    ShowAdapter(@NonNull List<RecordPieceEntity> recordPieceEntities) {
        if (recordPieceEntities.size() != 3) {
            throw new IllegalArgumentException("must have three tasks!");
        }
        mRecordPieceEntities = recordPieceEntities;
        for (int i = 0; i< 3;i++) {
            mIndexRecordPieces.add(new IndexRecordPiece(i));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.arrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
            }
        });
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int id = mIndexRecordPieces.get(position).getId();
        holder.arrowButton.setImageResource(indexPicturesArrows[id]);
        holder.indexView.setImageResource(indexPicturesIndex[id]);
    }

    @Override
    public int getItemCount() {
        return mRecordPieceEntities == null ? 0 : mRecordPieceEntities.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView indexView;
        TextView titleView;
        ImageView arrowButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            indexView = itemView.findViewById(R.id.main_item_index);
            titleView = itemView.findViewById(R.id.main_item_wait_title);
            arrowButton = itemView.findViewById(R.id.main_item_wait_button);
        }
    }

    private class IndexRecordPiece extends RecordPieceEntity {
        private int id;

        private IndexRecordPiece(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }
}
