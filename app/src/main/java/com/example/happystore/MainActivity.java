package com.example.happystore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends Activity
{
    private static EditText EmailIdLogin,PasswordLogin;
    private static TextView Heading,Forgotpassword;
    private static Button SubmitButton,NewAccount;
    private FirebaseAuth mAuth;
    private String emailId,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        LoginButton();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser User = mAuth.getCurrentUser();
    }

    public void LoginButton()
    {
        EmailIdLogin=(EditText)findViewById(R.id.EmailIdLogin);
        PasswordLogin=(EditText)findViewById(R.id.PasswordLogin);
        SubmitButton=(Button)findViewById(R.id.SubmitButton);
        Heading=(TextView)findViewById(R.id.Heading);
        Forgotpassword = (TextView) findViewById(R.id.link);
        NewAccount=(Button)findViewById(R.id.NewAccount);

        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailId=EmailIdLogin.getText().toString();
                password=PasswordLogin.getText().toString();

                mAuth.signInWithEmailAndPassword(emailId,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this,"Authentication successfull",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this,SecondPage1.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Authentication failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        Forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity.this,ForgotPassword.class);
                startActivity(intent1);
            }
        });

        NewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(MainActivity.this,NewAccount.class);
                startActivity(i1);
            }
        });
    }
}
