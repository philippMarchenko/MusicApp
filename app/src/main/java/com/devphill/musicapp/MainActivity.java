package com.devphill.musicapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.thin.downloadmanager.DefaultRetryPolicy;
import com.thin.downloadmanager.DownloadManager;
import com.thin.downloadmanager.DownloadRequest;
import com.thin.downloadmanager.DownloadStatusListenerV1;
import com.thin.downloadmanager.RetryPolicy;
import com.thin.downloadmanager.ThinDownloadManager;
import com.thin.downloadmanager.util.Log;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    Button button;
    String LOG_TAG = "MainActivityTag";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG,"onCreate");

        mWebView = (WebView) findViewById(R.id.webView);
        // включаем поддержку JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new MyWebViewClient());
        // указываем страницу загрузки

        mWebView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");


        mWebView.loadUrl("https://zf.fm/");


        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(LOG_TAG,"onClick");


            }
        });
    }

    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url)
        {
            Log.d(LOG_TAG,"onPageFinished");

        /* This call inject JavaScript into the page which just finished loading. */
            mWebView.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
        }
    }

    class MyJavaScriptInterface
    {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processHTML(String html)
        {
            Log.d(LOG_TAG,html);
        }
    }
}
