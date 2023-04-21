package com.example.shms;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class FeedAdabter extends RecyclerView.Adapter<FeedAdabter.fViewHolder> {
    private Context mContext;
    private List<feed> mData;

    public FeedAdabter(Context mContext, List<feed> mData){

        this.mContext = mContext;
        this.mData = mData;
    }
    @NonNull
    @Override
    public FeedAdabter.fViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.feedview,parent,false);
        return new fViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedAdabter.fViewHolder holder, int position) {
        holder.mtext.setText(mData.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class fViewHolder extends RecycleViewAdapter.MyViewHolder {
        TextView mtext;
        public fViewHolder(View itemView) {
            super(itemView);
            mtext=itemView.findViewById(R.id.feed125);
        }
    }
}