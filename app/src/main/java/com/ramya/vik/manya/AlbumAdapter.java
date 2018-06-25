package com.ramya.vik.manya;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by dell on 12/30/2016.
 */
public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> implements ActivityCompat.OnRequestPermissionsResultCallback{
private Context context;
    private static String Title="urlvideo";
DownloadManager downloadManager;
    private static String Title2="name";
    String name,title;
    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    private boolean sentToSettings = false;
    private SharedPreferences permissionStatus;
    ArrayList<Album> albumList=new ArrayList<>();
  public class MyViewHolder extends RecyclerView.ViewHolder  {
      public Button title,count;
      public TextView gl;
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
        name=album.getUrls();
        title=album.getTitle();
        permissionStatus = context.getSharedPreferences("permissionStatus",MODE_PRIVATE);

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
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        //Show Information about why you need the permission
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Need Storage Permission");
                        builder.setMessage("This app needs storage permission.");
                        builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();
                    } else if (permissionStatus.getBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE,false)) {
                        //Previously Permission Request was cancelled with 'Dont Ask Again',
                        // Redirect to Settings after showing Information about why you need the permission
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Need Storage Permission");
                        builder.setMessage("This app needs storage permission.");
                        builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                sentToSettings = true;
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                                intent.setData(uri);
                                ((Activity)context).startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                                Toast.makeText(context, "Go to Permissions to Grant Storage", Toast.LENGTH_LONG).show();
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();
                    }
                    else {
                        //just request the permission
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);
                    }


                    SharedPreferences.Editor editor = permissionStatus.edit();
                    editor.putBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE,true);
                    editor.commit();




                } else {
                    //You already have the permission, just go ahead.
                    proceedAfterPermission();
                }


            }
        });
holder.gl.setText("* "+album.getTitle());

    }
    public void proceedAfterPermission() {
        //We've got the permission, now we can proceed further
        Toast.makeText(context, "Downloading", Toast.LENGTH_SHORT).show();
        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(name);
        String nameoffile= URLUtil.guessFileName(name,null, MimeTypeMap.getFileExtensionFromUrl(name));
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle(title);
        request.setDescription("Thanks for Downloading");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,nameoffile);
        DownloadManager manager=(DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == EXTERNAL_STORAGE_PERMISSION_CONSTANT) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //The External Storage Write Permission is granted to you... Continue your left job...
                proceedAfterPermission();
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    //Show Information about why you need the permission
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Need Storage Permission");
                    builder.setMessage("This app needs storage permission");
                    builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();


                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);


                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } else {
                    Toast.makeText(context,"Unable to get Permission",Toast.LENGTH_LONG).show();
                }
            }
        }
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
