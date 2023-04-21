package com.example.shms;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomFoodListAdapter extends RecyclerView.Adapter<CustomFoodListAdapter.ExampleViewHolder> implements Filterable {

    public CustomFoodListAdapter(Context mContext,List<Blog> mData){

        this.mContext = mContext;
        //this.mData = mData;
    }
    private List<ExampleItem> exampleList;
    private List<ExampleItem> exampleListFull;
    private Context mContext;
    class ExampleViewHolder extends RecyclerView.ViewHolder {
        //SelectMeal x = new SelectMeal();
        //ImageView imageView;

        //mContext = this.Context;

        TextView textView1;
        TextView textView2;
        TextView textView3;
        CardView cardView;
        ExampleViewHolder(View itemView) {
            super(itemView);
            //imageView = itemView.findViewById(R.id.image_view);
            textView1 = itemView.findViewById(R.id.text_view1);
            textView2 = itemView.findViewById(R.id.text_view2);
            textView3 = itemView.findViewById(R.id.text_view3);
            cardView = itemView.findViewById(R.id.my_card_view);
        }
    }
    CustomFoodListAdapter(List<ExampleItem> exampleList) {
        this.exampleList = exampleList;
        exampleListFull = new ArrayList<>(exampleList);
    }
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_list,
                parent, false);
        return new ExampleViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem = exampleList.get(position);
        //holder.imageView.setImageResource(currentItem.getImageResource());
        holder.textView1.setText(currentItem.getText1());
        holder.textView2.setText(currentItem.getText2());
        holder.textView3.setText(currentItem.getText3());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext,MyDiet.class);
//                intent.putExtra("calorie","130");
//                intent.putExtra("name","enjera");
//                intent.putExtra("serving","serving 1");
//                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return exampleList.size();
    }
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ExampleItem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ExampleItem item : exampleListFull) {
                    if (item.getText2().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            exampleList.clear();
            exampleList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}

