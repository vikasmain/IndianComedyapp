package com.ramjana.vik.manya;

import android.app.Activity;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.ramjana.vik.mycheete.FullscreenVideoLayout;

import java.io.IOException;

public class Notify extends Activity {

    private FullscreenVideoLayout videoLayout;
    public static final String Title="urlvideo";
public String VideoURL="https://felicific-hooks.000webhostapp.com/wowsomes/wow1.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nepwlpd);
        this.videoLayout = (FullscreenVideoLayout) findViewById(R.id.videoview);

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
