package com.example.shms;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomListView extends ArrayAdapter<String> {

    private Activity context;
    private String[] foodName;
    private String[] foodType;
    private String[] serving;
    private Integer[] imageId;
    public CustomListView(Activity context,String[] foodName,String[] foodType,String[] serving,Integer[] imageId) {
        super(context,R.layout.layout_food_display,foodName);

       this.context= context;
       this.foodName = foodName;
       this.serving = serving;
       this.imageId = imageId;
       this.foodType = foodType;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View r = convertView;
        ViewHolder viewHolder = null;
        if (r == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.layout_food_display,null,true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) r.getTag ();
        }
        //viewHolder.foodImage.setImageResource(imageId[position]);
        viewHolder.foodName.setText(foodName[position]);
        viewHolder.serving.setText(serving[position]);
        viewHolder.foodCalorie.setText(foodType[position]);
        return r;


    }
    class ViewHolder{

        //ImageView foodImage;
        TextView foodName;
        TextView foodCalorie;
        TextView serving;

        ViewHolder(View view){

            //foodImage = (ImageView) view.findViewById(R.id.food_image);
            foodName = (TextView) view.findViewById(R.id.food_name);
            foodCalorie = (TextView) view.findViewById(R.id.food_type);
            serving = (TextView) view.findViewById(R.id.food_serving);
        }
    }
}
