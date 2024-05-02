package com.example.utsmobileprograming1_adzibilalmh_22552011164;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class NewsActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        webView = findViewById(R.id.webView);

        // Get the URL from the intent
        String url = getIntent().getStringExtra("url");

        // Load the URL into the WebView
        webView.loadUrl(url);

    }
}