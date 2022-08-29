package com.example.mycart.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.mycart.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    List<String> list;
    Context context;

    public CartAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_cart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String present_text = list.get(position);
        String[] tex=present_text.split("@@@");
        holder.itemName.setText(tex[0]);
        holder.itemQuantity.setText(tex[1]);
        holder.itemPrice.setText(tex[2]);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView itemName,itemPrice,itemQuantity;
        ElegantNumberButton elegantNumberButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName=itemView.findViewById(R.id.food_name);
            itemPrice=itemView.findViewById(R.id.food_price);
            itemQuantity=itemView.findViewById(R.id.food_quantity);

            elegantNumberButton=itemView.findViewById(R.id.number_button);

        }
    }
}
