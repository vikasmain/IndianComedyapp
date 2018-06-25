package com.ramya.vik.manya;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class CustomListAdapter21 extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Movie> movieItems;
    Context context;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    public static final String Title="url";
    public CustomListAdapter21(Activity activity, List<Movie> movieItems) {
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
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row2, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        final Button rating = (Button) convertView.findViewById(R.id.rating);



        // getting movie data for the row
        final Movie m = movieItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // title
        title.setText(m.getTitle());


        // rating
        rating.setText("Watch Now");
        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=m.getUrl();
                Intent intent = new Intent(activity,downloadnewp.class);
                intent.putExtra(Title,name);
                activity.startActivity(intent);
            }
        });


        // release year

        return convertView;
    }

}