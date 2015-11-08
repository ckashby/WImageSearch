package com.meteoru.kalei.wimagesearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class WebViewActivity extends AppCompatActivity {
    private static final String TAG = WebViewActivity.class.getSimpleName();
    private static final String IMAGE_URL = "url";
    private ImageView ivFullSize;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        ivFullSize = (ImageView) findViewById(R.id.ivFullSize);
        Intent i = getIntent();
        String imageUrl = i.getStringExtra(IMAGE_URL);
        Picasso.with(this).load(imageUrl).into(ivFullSize);
    }

}
