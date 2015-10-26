package com.beenverified.handlebars;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
        String result = null;
        try {

            final File templatesDir = new File(getFilesDir().getAbsolutePath() + "/templates/");
            if (!templatesDir.exists()) {
                templatesDir.mkdirs();
            }
            File layoutFile = new File(templatesDir.getAbsolutePath() + "/hello_template.hbs");
            if (layoutFile.exists() == false) {
                FileUtils.copyInputStreamToFile(getAssets().open("templates/hello_template.hbs"), layoutFile);
            }

            FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(templatesDir);
            mHandlebars = new Handlebars(fileTemplateLoader);
            mTemplate = mHandlebars.compile("hello_template");
            result = mTemplate.apply("Android");

            mWebView.loadData(result, "text/html", "utf-8");
        } catch (Exception e) {
            Log.d(LOG_TAG, "An error has ocurred while compiling handlebars template.", e);
        }

    }

}
