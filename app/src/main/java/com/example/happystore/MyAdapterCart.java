package com.example.happystore;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterCart extends RecyclerView.Adapter<MyAdapterCart.MyViewHolder> {
    private Context mContext;
    ArrayList<NewUserCart> newUser;
    private final ShoppingCart activity;
    private OnItemClickListener mlistener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener , View.OnCreateContextMenuListener,MenuItem.OnMenuItemClickListener {
        TextView Name,Quantity,Price,TotalPriceCart;
        ImageView Delete;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.namecart);
            Price = itemView.findViewById(R.id.pricecart);
            Quantity = itemView.findViewById(R.id.quantitycart);
            TotalPriceCart = itemView.findViewById(R.id.totalPriceCart);
            Delete = itemView.findViewById(R.id.delete);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
            totalprice();
        }
        private void totalprice()
        {
            String price = Price.getText().toString();
            String quantity=Quantity.getText().toString();

            int priceToInt = 0,quantityToInt=0;
            try {
                priceToInt = Integer.parseInt(price);
                quantityToInt=Integer.parseInt(quantity);
            } catch (NumberFormatException exception) {

            }

            int totalp = priceToInt * quantityToInt;
            TotalPriceCart.setText("" + totalp);
        }

        @Override
        public void onClick(View v) {
            if(mlistener!= null)
            {
                int position = getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    mlistener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Options");
            MenuItem delete = menu.add(Menu.NONE,1,1,"delete");
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if(mlistener!= null)
            {
                int position = getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                   switch (item.getItemId())
                   {
                       case 1:
                           mlistener.onDeleteClick(position);
                           return true;
                   }
                }
            }
            return false;
        }

    }


    public MyAdapterCart(Context c, ArrayList<NewUserCart> nu, ShoppingCart activity) {
        mContext = c;
        newUser = nu;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyAdapterCart.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_list1, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        NewUserCart newUser = this.newUser.get(position);

        holder.Name.setText(newUser.getName());
        holder.Price.setText(newUser.getPrice());
        holder.Quantity.setText(newUser.getQuantity());
        holder.TotalPriceCart.setText(newUser.getTPrice());

    }

    @Override
    public int getItemCount() {
        return newUser.size();
    }

public interface  OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
}

public void setOnItemClickListener(OnItemClickListener listener)
{
    mlistener = listener;
}
}
