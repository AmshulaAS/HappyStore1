package com.example.happystore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import java.util.ArrayList;

public class ShoppingCart extends Activity implements MyAdapterCart.OnItemClickListener {
    ArrayList<NewUserCart> nuser;
    RecyclerView recyclerView;
    MyAdapterCart adapter1;
    private FirebaseStorage mstorage;
    DatabaseReference databaseReference;
    private ValueEventListener mDBlistener;
    TextView SumTotal;
    Button ConfirmOrder;
    private ImageView imageView;
    int sum,totalPrice=0;
    String currentuser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart);

        nuser = new ArrayList<>();

        recyclerView = findViewById(R.id.myRecycler1);
        SumTotal=findViewById(R.id.sum);
        ConfirmOrder=findViewById(R.id.ConfirmYourOrder);
        imageView =findViewById(R.id.image0);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingCart.this, MainActivity.class);
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(ShoppingCart.this));

        mstorage = FirebaseStorage.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Cart");
        currentuser= FirebaseAuth.getInstance().getCurrentUser().getUid();

        mDBlistener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nuser.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    NewUserCart nu = dataSnapshot1.getValue(NewUserCart.class);
                    if(nu.getUser().equals(currentuser)) {
                    nu.setmKey(dataSnapshot1.getKey());
                    nuser.add(nu); }
                }

                grandTotal(nuser);

                SumTotal.setText(String.valueOf("Total Amount: "+sum));

                adapter1 = new MyAdapterCart(ShoppingCart.this, nuser, ShoppingCart.this);
                recyclerView.setAdapter(adapter1);
                adapter1.notifyDataSetChanged();
                adapter1.setOnItemClickListener(ShoppingCart.this);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ShoppingCart.this, "error!", Toast.LENGTH_SHORT).show();
            }
        });

        ConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShoppingCart.this,OrderDetails.class);
                intent.putExtra("MY_KEY_total",SumTotal.getText().toString());
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseReference.removeEventListener(mDBlistener);
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(ShoppingCart.this, "normal click", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDeleteClick(int position) {
        NewUserCart selectedItem = nuser.get(position);
        final String selectedKey = selectedItem.getmKey();

        databaseReference.child(selectedKey).removeValue();
        Toast.makeText(ShoppingCart.this, "removed item from cart", Toast.LENGTH_LONG).show();
    }
    private int grandTotal(ArrayList<NewUserCart> mCart) {

        sum=0;
        for (int i = 0; i < mCart.size(); i++) {
            totalPrice = Integer.parseInt(mCart.get(i).getTPrice());
            sum = totalPrice + sum;
        }
        return sum;
    }
}

