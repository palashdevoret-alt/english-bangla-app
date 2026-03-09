
package com.englishbangla.app;

import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity {

WebView webView;
ProgressBar progressBar;
SwipeRefreshLayout refreshLayout;
String HOME_URL = "https://www.english-bangla.com/";

protected void onCreate(Bundle savedInstanceState){
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);

webView=findViewById(R.id.webview);
progressBar=findViewById(R.id.progress);
refreshLayout=findViewById(R.id.refresh);

webView.getSettings().setJavaScriptEnabled(true);

webView.setWebViewClient(new WebViewClient(){
public void onPageFinished(WebView view,String url){
progressBar.setVisibility(android.view.View.GONE);
refreshLayout.setRefreshing(false);
}

public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError r){
webView.loadUrl("file:///android_asset/offline.html");
}
});

webView.loadUrl(HOME_URL);

refreshLayout.setOnRefreshListener(() -> webView.reload());

findViewById(R.id.btnBack).setOnClickListener(v -> {
if(webView.canGoBack()) webView.goBack();
});

findViewById(R.id.btnHome).setOnClickListener(v -> {
webView.loadUrl(HOME_URL);
});

findViewById(R.id.btnForward).setOnClickListener(v -> {
if(webView.canGoForward()) webView.goForward();
});
}

public void onBackPressed(){
if(webView.canGoBack()) webView.goBack();
else super.onBackPressed();
}
}
