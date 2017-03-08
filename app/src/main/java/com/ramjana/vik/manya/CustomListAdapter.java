package com.ramjana.vik.manya;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<Movielaugh> movieItems;

DownloadManager downloadManager;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    public CustomListAdapter(Activity activity, ArrayList<Movielaugh> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;

    }

    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.profilePic);
        TextView title = (TextView) convertView.findViewById(R.id.name);
TextView a=(TextView)convertView.findViewById(R.id.txtStatusMsg);
        FeedImageView feedImageView = (FeedImageView) convertView


                .findViewById(R.id.feedImage1);

        // getting movie data for the row
        Movielaugh m = movieItems.get(position);

        // thumbnail image

        thumbNail.setImageUrl(m.getImage2(), imageLoader);

        // title
        title.setText(m.getTitle());

a.setText(m.getUrl());
        feedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movielaugh m = movieItems.get(position);
                String name = m.getThumbnailUrl();
                downloadManager = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(name);
                String nameoffile= URLUtil.guessFileName(name,null, MimeTypeMap.getFileExtensionFromUrl(name));
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setTitle("Hasenga Bharat");
                request.setDescription("File is being Downloading");
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,nameoffile);
                DownloadManager manager=(DownloadManager)activity.getSystemService(Context.DOWNLOAD_SERVICE);
                manager.enqueue(request);
            }
        });
        // rating
        if (m.getThumbnailUrl() != null) {
            feedImageView.setImageUrl(m.getThumbnailUrl(), imageLoader);
            feedImageView.setVisibility(View.VISIBLE);
            feedImageView
                    .setResponseObserver(new FeedImageView.ResponseObserver() {
                        @Override
                        public void onError() {
                        }

                        @Override
                        public void onSuccess() {
                        }
                    });
        } else {
            feedImageView.setVisibility(View.GONE);
        }



        return convertView;
    }


}
