package com.helper;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import url.Url;

public class DescriptionActivity extends AppCompatActivity {
    private ImageView circleImage, imgBack;
    TextView tvProduct, tvPrice, tvCategory, tvDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        tvProduct = findViewById(R.id.tvProduct);
        tvPrice = findViewById(R.id.tvPrice);
        tvCategory = findViewById(R.id.tvCategory);
        tvDesc = findViewById(R.id.tvDesc);
        circleImage = findViewById(R.id.circleImage);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // DescriptionActivity.super.onBackPressed();
                Intent intent = new Intent(DescriptionActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        StrictMode();
        URL url = null;

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            circleImage.setImageResource(bundle.getInt("image"));

            try {
                url = new URL(Url.BASE_URL + "uploads/" + bundle.getString("image"));
                circleImage.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            tvProduct.setText(bundle.getString("itemsname"));
            tvPrice.setText(bundle.getString("price"));
            tvCategory.setText( bundle.getString("category"));
            tvDesc.setText(bundle.getString("desc"));
        }
    }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

}
