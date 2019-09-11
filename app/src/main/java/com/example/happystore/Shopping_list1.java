package com.example.happystore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Shopping_list1 extends Activity {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ArrayList<User> list;
    ArrayList<NewUser> nuser;
    MyAdapter adapter;
    private String groupHeader;
    private String groupChild;
    private Button GoToShoppingList;
    DatabaseReference DatabaseRef;
    TextView Name,Price,Quantity,TotalPrice;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_list);

        DatabaseRef = FirebaseDatabase.getInstance().getReference().child("Cart");

        Name=findViewById(R.id.name);
        Price=findViewById(R.id.price);
        Quantity=findViewById(R.id.quantity);
        GoToShoppingList=findViewById(R.id.goToShoppingList);
        imageView = (ImageView)findViewById(R.id.image0);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Shopping_list1.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Intent iin = getIntent();
        Bundle bundle = iin.getExtras();
        if (bundle != null) {
            groupHeader = bundle.getString("GroupHeader");
            groupChild = bundle.getString("GroupChild");
        }

        list = new ArrayList<>();

        recyclerView = findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(Shopping_list1.this));

        databaseReference = FirebaseDatabase.getInstance().getReference().child(groupHeader).child(groupChild);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    User u = dataSnapshot1.getValue(User.class);
                    list.add(u);
                }

                adapter = new MyAdapter(Shopping_list1.this, list, Shopping_list1.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Shopping_list1.this, "error!", Toast.LENGTH_SHORT).show();
            }
        });
            GoToShoppingList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Shopping_list1.this,ShoppingCart.class);
                    startActivity(intent);
                }
            });
    }

    public void additems(NewUser newUser)
    {
        String currentuser;
        currentuser= FirebaseAuth.getInstance().getCurrentUser().getUid();

        if (!TextUtils.isEmpty(newUser.getName()) && !TextUtils.isEmpty(newUser.getPrice()) && !TextUtils.isEmpty(newUser.getQuantity()) && !TextUtils.isEmpty(newUser.getTPrice())) {
            String id = DatabaseRef.push().getKey();
            NewUser nuser = new NewUser(newUser.getName(), newUser.getPrice(), newUser.getQuantity(),newUser.getTPrice(),currentuser);

            DatabaseRef.child(id).setValue(nuser);
            Toast.makeText(Shopping_list1.this, "Added into cart", Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(Shopping_list1.this, "The information is empty", Toast.LENGTH_LONG).show();
        }
    }

}