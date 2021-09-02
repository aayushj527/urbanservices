package com.google.firebase.udacity.urbanservices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class detailPage extends AppCompatActivity {

    TextView textView3,textView4;
    ImageView messImg,callImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        messImg = findViewById(R.id.messImg);
        callImg = findViewById(R.id.callImg);

        Intent i = getIntent();
        textView3.setText(i.getStringExtra("title"));
        textView4.setText(i.getStringExtra("detail"));

        callImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:0000000000");
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(uri);
                startActivity(call);
            }
        });

        messImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("smsto:0000000000");
                Intent message = new Intent(Intent.ACTION_SENDTO,uri);
                startActivity(message);
            }
        });
    }
}