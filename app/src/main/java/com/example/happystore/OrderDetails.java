package com.example.happystore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderDetails extends Activity {
    Button clicktoConfirm;
    private ImageView imageView;
    private TextView tot;
    private EditText editTextTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details);

        clicktoConfirm=findViewById(R.id.ClickToConfirm);
        imageView = (ImageView)findViewById(R.id.image0);
        tot=(TextView) findViewById(R.id.tot);
        editTextTitle=(EditText)findViewById(R.id.edit_text_title);

        Bundle extras = getIntent().getExtras();
        String value = extras.getString("MY_KEY_total");
        tot.setText(value);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetails.this, MainActivity.class);
                startActivity(intent);
            }
        });

        clicktoConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OrderDetails.this,PaymentViaCash.class);
                intent.putExtra("MY_KEY_total1",tot.getText().toString());
                intent.putExtra("MY_KEY",editTextTitle.getText().toString());
                startActivity(intent);
            }
        });
    }

}
