package com.example.happystore;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.example.happystore.App.CHANNEL_1_ID;

public class PaymentViaCash extends Activity {
    Button SendEmailUser;
    private ImageView imageView;
    private TextView totalamount;
    private NotificationManagerCompat notificationManager;
    private TextView editTextTitle;
    private EditText editTextMessage;
    public String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_via_cash);

        SendEmailUser=findViewById(R.id.sendEmail);
        imageView = (ImageView)findViewById(R.id.image0);
        totalamount=(TextView)findViewById(R.id.totalAmount);
        editTextTitle = (TextView)findViewById(R.id.edit_text_title);
        editTextMessage = findViewById(R.id.edit_text_message);
        imageView = (ImageView)findViewById(R.id.image0);

        notificationManager = NotificationManagerCompat.from(this);

        Bundle extras1 = getIntent().getExtras();
        String value1 = extras1.getString("MY_KEY_total1");
        totalamount.setText(value1);

        Bundle extras = getIntent().getExtras();
        String value = extras.getString("MY_KEY");
        editTextTitle.setText(value);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentViaCash.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
    public void sendOnChannel1(View v) {

        String ContentText = editTextMessage.getText().toString();
        String ContentTitle = editTextTitle.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_person_black_24dp)
                .setContentTitle(ContentTitle)
                .setContentText("Thank You For ordering at Happy Store"+ContentText)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);
    }
}
