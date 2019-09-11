package com.example.happystore;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class NewAccount extends Activity {
    private  Button submitNewAccount,NewItem,Admin;
    private  String emailId,password;
    private FirebaseAuth mAuth;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_new_account);

        imageView = (ImageView)findViewById(R.id.image0);
        NewItem=(Button)findViewById(R.id.NewItem);
        Admin=findViewById(R.id.admin);


        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                EditText eid=findViewById(R.id.emailIdNewAccount);
                EditText pwd=findViewById(R.id.passwordNewAccount);

                emailId=eid.getText().toString();
                password=pwd.getText().toString();

                if (emailId.equals("admin@gmail.com") && password.equals("7654321"))
                {
                    NewItem.setVisibility(NewItem.VISIBLE);
                }
            }
        });
        NewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent admin=new Intent(NewAccount.this,Admin_new_item.class);
                startActivity(admin);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewAccount.this, MainActivity.class);
                startActivity(intent);
            }
        });

        OnButton();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser User = mAuth.getCurrentUser();
        updateUI(User);
    }

    private void updateUI(FirebaseUser user) {
        if(user !=null)
        {
            String email = user.getEmail();
            String displayName = user.getDisplayName();
            Uri photoUrl = user.getPhotoUrl();
            // update the textview with above details
        }
    }

    public void OnButton()
    {
        submitNewAccount=(Button)findViewById(R.id.submitNewAccount);
        submitNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText eid=findViewById(R.id.emailIdNewAccount);
                EditText pwd=findViewById(R.id.passwordNewAccount);
                emailId=eid.getText().toString();
                password=pwd.getText().toString();

                mAuth.createUserWithEmailAndPassword(emailId,password).addOnCompleteListener(NewAccount.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if(task.isSuccessful())
                        {
                            Toast.makeText(NewAccount.this,"Registration successfull",Toast.LENGTH_SHORT).show();
                            Intent i2=new Intent(NewAccount.this,MainActivity.class);
                            startActivity(i2);
                        }
                        else {
                            Toast.makeText(NewAccount.this,"Registration failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

    }
}