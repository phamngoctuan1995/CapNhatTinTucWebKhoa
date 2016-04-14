package com.example.phamngoctuan.capnhattintucwebkhoa;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebContent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_content);
        WebView webView = (WebView)findViewById(R.id.web_content);
        webView.getSettings().setUserAgentString("Android");
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setSupportZoom(true);

        Intent intent= getIntent();
        if (intent != null)
        {
            webView.loadUrl(intent.getStringExtra("link"));
        }
    }
}
