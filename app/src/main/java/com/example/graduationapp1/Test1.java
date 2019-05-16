package com.example.graduationapp1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class Test1 extends AppCompatActivity {
    WebView chart1;
    Handler handler = new Handler();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        chart1 = ( WebView )findViewById(R.id.chart1);
        chart1.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        chart1.setWebViewClient(new WebViewClient());
        chart1.setWebChromeClient(new WebChromeClient());
        chart1.setNetworkAvailable(true);
        chart1.getSettings().setJavaScriptEnabled(true);

        //// Sets whether the DOM storage API is enabled.
        chart1.getSettings().setDomStorageEnabled(true);
        ////
        chart1.getSettings().setLoadWithOverviewMode(true);
        chart1.getSettings().setUseWideViewPort(true);
        chart1.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        chart1.setScrollbarFadingEnabled(true);

        String url = "https://thingspeak.com/channels/746581/charts/1?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=30&title=%EC%A1%B0%EB%AF%B8%EB%A3%8C%EB%B3%911&type=line";
        chart1.loadUrl(url);

        /*Thread t = new Thread(new Runnable() {
            @Override
            public void run() {    // 오래 거릴 작업을 구현한다
                // TODO Auto-generated method stub
                try{
                    // 걍 외우는게 좋다 -_-;
                    final WebView iv = chart1 = (WebView)findViewById(R.id.chart1);
                    final URL url = new URL("https://thingspeak.com/channels/746581/charts/1?bgcolor=%23dddddd&color=%23d62020&dynamic=true&results=30&title=%EC%A1%B0%EB%AF%B8%EB%A3%8C%EB%B3%911&type=line");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {  // 화면에 그려줄 작업
                            iv.loadUrl(String.valueOf(url));
                        }
                    });
                } catch(Exception e){

                }

            }
        });

        t.start();*/

    }
}
