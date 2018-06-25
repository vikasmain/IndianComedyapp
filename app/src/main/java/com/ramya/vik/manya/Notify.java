package com.ramya.vik.manya;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.ramya.vik.mycheete.FullscreenVideoLayout;

import java.io.IOException;

public class Notify extends Activity {

    private FullscreenVideoLayout videoLayout;
    public static final String Title="urlvideo";

public String VideoURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nepwlpd);
        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("urlname",MODE_PRIVATE);
        String url=sharedPreferences.getString("url","NotFound");
        this.videoLayout = (FullscreenVideoLayout) findViewById(R.id.videoview);
        if(!TextUtils.isEmpty(url)) {

            VideoURL=url;
            Toast.makeText(this, VideoURL, Toast.LENGTH_SHORT).show();
        }
        else
        {
            VideoURL="https://vikasbajpayee.000webhostapp.com/33997361_196897604279016_4947457847140024320_n.mp4";
        }
        videoLayout.setActivity(this);
        videoLayout.setShouldAutoplay(true);

        loadVideo();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        videoLayout.resize();
    }

    public void loadVideo() {

        Uri videoUri = Uri.parse(VideoURL);
        try {
            videoLayout.setVideoURI(videoUri);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reset(View v) {
        if (this.videoLayout != null) {
            this.videoLayout.reset();
            loadVideo();
        }
    }
}
