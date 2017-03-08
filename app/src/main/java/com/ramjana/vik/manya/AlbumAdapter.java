package com.ramjana.vik.manya;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

/**
 * Created by dell on 12/30/2016.
 */
public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {
private Context context;
    private static String Title="urlvideo";
DownloadManager downloadManager;
    private static String Title2="name";
    ArrayList<Album> albumList=new ArrayList<>();
  public class MyViewHolder extends RecyclerView.ViewHolder  {
      public Button title,count;
      public TextView gl;
      Context cptx;
      public NetworkImageView thumbnail;
      ArrayList<Album> albumList=new ArrayList<>();
Context context;
public Button button;
      public MyViewHolder(View itemView) {
          super(itemView);
          this.albumList=albumList;
          title=(Button) itemView.findViewById(R.id.title);
          count=(Button) itemView.findViewById(R.id.count);
          thumbnail= (NetworkImageView) itemView.findViewById(R.id.thumbnail);
          gl=(TextView)itemView.findViewById(R.id.goi);


      }





  }
    public AlbumAdapter(Context context, ArrayList<Album> albumList){
        this.context=context;
        this.albumList=albumList;
    }
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_card,parent,false);
       return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final AlbumAdapter.MyViewHolder holder, final int position) {
        final Album album = albumList.get(position);

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        holder.thumbnail.setImageUrl(album.getNoofthumbnails(), imageLoader);

        holder.title.setText("Play It");
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = album.getUrls();
                Intent intent = new Intent(context, newppdkks.class);
                intent.putExtra(Title, name);
                context.startActivity(intent);
            }
        });
        holder.count.setText("Download/Save It");
        holder.count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Album album2 = albumList.get(position);
                String name = album2.getUrls();
                downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(name);
                String nameoffile= URLUtil.guessFileName(name,null, MimeTypeMap.getFileExtensionFromUrl(name));
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setTitle("Hasenga Bharat");
                request.setDescription("Thanks for Downloading");
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,nameoffile);
                DownloadManager manager=(DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
                manager.enqueue(request);
            }
        });
holder.gl.setText("* "+album.getTitle());

    }


    @Override
    public int getItemCount() {
        return albumList.size();
    }

public void setfilter(ArrayList<Album> newlist){
    albumList=new ArrayList<>();
    albumList.addAll(newlist);
    notifyDataSetChanged();
}
}
