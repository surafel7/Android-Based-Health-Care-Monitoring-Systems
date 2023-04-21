package com.example.shms;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Blog> mData;

    public RecycleViewAdapter(Context mContext,List<Blog> mData){

        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.card_view_item_blogs,parent,false);
         return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.blogTitle.setText(mData.get(position).getTitle());
        Glide.with(mContext).load(mData.get(position).getImage()).into(holder.blogImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,TheBlog.class);
                intent.putExtra("Title",mData.get(position).getTitle());
                intent.putExtra("Description",mData.get(position).getDescription());
                intent.putExtra("Category",mData.get(position).getCategory());
                intent.putExtra("Image",mData.get(position).getImage());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView blogTitle;
        ImageView blogImage;
        CardView cardView;
        public MyViewHolder(View itemView){
            super(itemView);
            blogTitle = (TextView) itemView.findViewById(R.id.blog_title);
            blogImage = (ImageView) itemView.findViewById(R.id.blog_image);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }

    }
}
