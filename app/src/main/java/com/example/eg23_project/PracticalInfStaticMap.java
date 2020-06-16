package com.example.eg23_project;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PracticalInfStaticMap extends Fragment {

    private WebView mWebView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_practical_inf_static_map, container, false);
        mWebView = (WebView) v.findViewById(R.id.myStaticMap);
        mWebView.loadUrl("https://maps.googleapis.com/maps/api/staticmap?center=48.285559,4.077325&zoom=14&size=600x600&maptype=roadmap&markers=markers=color:red|48.268979,4.066985&markers=color:blue|48.299274,4.065707&markers=color:yellow|48.299393,4.084222&markers=color:green|48.295654,4.076387&key=AIzaSyAA6n4tZyglgzAj7ZN55tP6r8h9xlpjplU&format=png");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        return v;
    }
}