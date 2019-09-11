package com.example.happystore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final Shopping_list1 activity;
    public Context mContext;
    ArrayList<User> user;
    private EditText edit1;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Name, Price,TotalP;
        private Button button1Increment, button2Decrement, button3Addtolist;
        private EditText Quantity;
        public int count;
        public Context mContext;
        public String totalvalue;
        private AdapterView.OnItemClickListener listener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.name);
            Price = itemView.findViewById(R.id.price);
            Quantity = itemView.findViewById(R.id.quantity);
            button1Increment = itemView.findViewById(R.id.increment);
            button2Decrement = itemView.findViewById(R.id.decrement);
            button3Addtolist = itemView.findViewById(R.id.addtocart);
            TotalP=itemView.findViewById(R.id.totalpricelist);

            button1Increment.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    count++;
                    totalprice();
                    Quantity.setText(String.valueOf(count));
                }
            });

            button2Decrement.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    count--;
                    totalprice();
                    Quantity.setText(String.valueOf(count));
                }
            });

            }

        private void totalprice() {
            String price = Price.getText().toString();
            int priceToInt = 0;
            try
            {
                priceToInt = Integer.parseInt(price);
            }
            catch (NumberFormatException exception)
            {

            }

            int totalp = priceToInt * count;
            TotalP.setText("" + totalp);

       }
    }

    public MyAdapter(Context c, ArrayList<User> u, Shopping_list1 activity) {
        mContext = c;
        user = u;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        User user = this.user.get(position);

        holder.Name.setText(user.getName());
        holder.Price.setText(user.getPrice());
        holder.TotalP.setText(user.getTotalPrice());

        holder.button3Addtolist.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Shopping_list1) mContext).additems(new NewUser(holder.Name,holder.Price, holder.Quantity,holder.TotalP));
            }
        });
    }

    @Override
    public int getItemCount() {
        return user.size();
    }

}

