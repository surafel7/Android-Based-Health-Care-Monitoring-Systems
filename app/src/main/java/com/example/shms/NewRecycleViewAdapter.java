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

public class NewRecycleViewAdapter extends RecyclerView.Adapter<NewRecycleViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Blog> mData;

    public NewRecycleViewAdapter(Context mContext, List<Blog> mData){

        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.food_item_list,parent,false);
         return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.foodCal.setText(mData.get(position).getTitle());
        holder.foodName.setText(mData.get(position).getImage());
        holder.foodServing.setText(mData.get(position).getImage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,TheBlog.class);
                intent.putExtra("Title",mData.get(position).getTitle());
                intent.putExtra("Description",mData.get(position).getDescription());
                //intent.putExtra("Category",mData.get(position).getCategory());
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

        TextView foodCal;
        TextView foodName;
        TextView foodServing;
        CardView cardView;
        public MyViewHolder(View itemView){
            super(itemView);
            foodCal = (TextView) itemView.findViewById(R.id.text_view1);
            foodName = (TextView) itemView.findViewById(R.id.text_view2);
            foodServing = (TextView) itemView.findViewById(R.id.text_view3);
            cardView = (CardView) itemView.findViewById(R.id.my_card_view);
        }

    }


}
