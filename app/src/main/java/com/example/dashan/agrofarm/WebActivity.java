package com.example.dashan.agrofarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {

    private WebView myWebview;
    private static final String TAG="WebActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        myWebview=(WebView) findViewById(R.id.web_view_activity);

        String title="";
        String descrip="";
        String categorie="";
        Intent intent=getIntent();
        try{
            title=intent.getExtras().getString("Title");
            Log.d(TAG,title);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            descrip=intent.getExtras().getString("Description");
            Log.d(TAG,descrip);
        }catch (Exception e){
            e.printStackTrace();
        }
        WebSettings webSettings=myWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        myWebview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        myWebview.setScrollbarFadingEnabled(false);
        myWebview.loadUrl(descrip);
        //mywebview.loadUrl("https://www.amazon.com");
        myWebview.setWebViewClient(new WebViewClient());
    }
    @Override
    public void onBackPressed() {
        if(myWebview.canGoBack()){
            myWebview.goBack();
        }
        else{
            super.onBackPressed();
           /* Intent intent=new Intent(WebActivity.this,ProfileUser.class);
            startActivity(intent);*/
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_webview,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_settings){
            return true;
        }
        if(id==R.id.ic_icon_back){
            Intent intent=new Intent(WebActivity.this,ProfileUser.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
