package com.example.happystore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class Admin_new_item extends AppCompatActivity {

    private EditText name,price;
    private Button addToList;
    FirebaseDatabase Database;
    DatabaseReference reference;
    Firebase reference1;
    User user;
    private ImageView imageView;
    private Spinner Heading,Subcategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_new_item);

        name=(EditText)findViewById(R.id.name);
        price=(EditText)findViewById(R.id.Price);
        addToList=(Button)findViewById(R.id.addToList);
        imageView = (ImageView)findViewById(R.id.image0);

        Heading=(Spinner)findViewById(R.id.spinner1);
        Subcategory=(Spinner)findViewById(R.id.spinner2);

        Heading.setOnItemSelectedListener(new ItemSelectedListner());
        Heading.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                List<String> list = new ArrayList<String>();
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("HomeNeeds"))
                {
                    list.clear();
                    list.add("CleaningAccessories");
                    list.add("Cookware");
                    list.add("Detergents");
                    list.add("Electricals");
                    list.add("FestivalNeeds");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Admin_new_item.this, android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Subcategory.setAdapter(dataAdapter);
                }
                else if(selectedItem.equals("GroceryStaples"))
                {
                    list.clear();
                    list.add("BiscuitsBreads");
                    list.add("CerealsGrains");
                    list.add("OilGhee");
                    list.add("InstantFood");
                    list.add("Masalaspices");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Admin_new_item.this, android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Subcategory.setAdapter(dataAdapter);
                }
                else if(selectedItem.equals("FruitsVegetables"))
                {
                    list.clear();
                    list.add("ExoticVegetables");
                    list.add("Fruits");
                    list.add("ImportedFruits");
                    list.add("Vegetables");
                    list.add("DryFruits");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Admin_new_item.this, android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Subcategory.setAdapter(dataAdapter);
                }
                else if(selectedItem.equals("Beverages"))
                {
                    list.clear();
                    list.add("Healthdrinks");
                    list.add("Energydrink");
                    list.add("Softdrink");
                    list.add("Coffeecans/Tea");
                    list.add("Mineralwater");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Admin_new_item.this, android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Subcategory.setAdapter(dataAdapter);
                }
                else if(selectedItem.equals("PersonalCare"))
                {
                    list.clear();
                    list.add("Fashionaccessories");
                    list.add("Haircarecosmetics");
                    list.add("SanitaryNeeds");
                    list.add("ShavingNeeds");
                    list.add("Deos/Perfumes");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Admin_new_item.this, android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Subcategory.setAdapter(dataAdapter);
                }
                else if(selectedItem.equals("kidsUtilities"))
                {
                    list.clear();
                    list.add("Schoolneeds");
                    list.add("Toys/Games");
                    list.add("Kidscare");

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Admin_new_item.this, android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Subcategory.setAdapter(dataAdapter);
                }
                else if(selectedItem.equals("Fashion"))
                {
                    list.clear();
                    list.add("Men");
                    list.add("Women");
                    list.add("Kids");

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Admin_new_item.this, android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Subcategory.setAdapter(dataAdapter);
                }
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_new_item.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Firebase.setAndroidContext(this);
        Database=FirebaseDatabase.getInstance();
        reference=Database.getReference("HomeNeeds");

        String.valueOf(Heading.getSelectedItemId());
        user=new User();

        addToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnInsert(v);
            }
        });
    }


    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
    }

    private void getValues()
    {
        user.setName(name.getText().toString());
        user.setPrice(price.getText().toString());
    }
    public void btnInsert(View v)
    {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getValues();
               reference1=new Firebase("https://happystore-264a9.firebaseio.com/"+Heading.getSelectedItem()+"/"+Subcategory.getSelectedItem()+"/"+price.getText());
               reference1=new Firebase("https://happystore-264a9.firebaseio.com/"+Heading.getSelectedItem()+"/"+Subcategory.getSelectedItem()+"/"+name.getText());

               reference1.setValue(user);
                //reference.child("Cleaning accessories").setValue(user);
                Toast.makeText(Admin_new_item.this,"Data Inserted",Toast.LENGTH_LONG).show();

                //reference.child("item1").setValue(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Admin_new_item.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
            }
        });
    }
}
