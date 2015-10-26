package com.beenverified.handlebars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();

    private WebView mWebView;
    private Handlebars mHandlebars;
    private Template mTemplate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.web_view);
        String content = null;
        try {
            mHandlebars = new Handlebars();
            mTemplate = mHandlebars.compile("file:///android_asset/template/hello_template");
            content = mTemplate.apply("Android");
            mWebView.loadData(content, "text/html", "utf-8");
        } catch (Exception e) {
            Log.d(LOG_TAG, "An error has ocurred while compiling handlebars template", e);
        }

    }

}
