package com.bansalankit.learning;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Ankit Bansal on 5/29/2016.
 */
public class WebViewActivity extends AppCompatActivity {
    private EditText mEditUrl;

    @Override
    @SuppressWarnings("ConstantConditions")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        // Enable back button on top in action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Intialize the URL field and set the cursor location
        mEditUrl = (EditText) findViewById(R.id.webview_url);
        mEditUrl.setSelection(mEditUrl.length());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    private String getUrl() {
        String url = mEditUrl.getText().toString().trim();
        if (!Patterns.WEB_URL.matcher(url).matches()) {
            mEditUrl.setError("Enter valid URL");
            return null;
        }
        // Send the entered URL
        else return url;
    }

    public void onWebBrowser(View view) {
        String url = getUrl();
        if (url != null) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
            browserIntent.setData(Uri.parse(url));
            startActivity(browserIntent);
        }
    }

    public void onWebView(View view) {
        String url = getUrl();
        if (url != null) {
            // TODO Show WebView
            toast("Under Construction");
        }
    }

    public void onCustomTab(View view) {
        String url = getUrl();
        if (url != null) {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            builder.setStartAnimations(this, R.anim.slide_from_right, R.anim.slide_to_left);
            builder.setExitAnimations(this, R.anim.slide_from_left, R.anim.slide_to_right);
            builder.enableUrlBarHiding().setShowTitle(true);
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(this, Uri.parse(url));
        }
    }

    public void onCrossWalk(View view) {
        String url = getUrl();
        if (url != null) {
            // TODO Show CrossWalk
            toast("Under Construction");
        }
    }

    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}