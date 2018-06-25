package com.ramya.vik.manya;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class newp extends AppCompatActivity implements SearchView.OnQueryTextListener{
  // private static final String url = "http://192.168.56.1/albumfinal.json";

    private static final String TAG = newp.class.getSimpleName();
    private RecyclerView recyclerView;
    private AlbumAdapter albumAdapter;
    RequestQueue queue;
//private String url="https://app-1486707345.000webhostapp.com/mrbean.json";
    private ArrayList<Album> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newpmain);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbarnewp);
        setSupportActionBar(toolbar);
        initcollapsingtoolbar();

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        list=new ArrayList<>();
        albumAdapter=new AlbumAdapter(this,list);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingitemdecoration(2,dpToPx(10),true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(albumAdapter);

        queue=AppController.getInstance().getRequestQueue();

        preparealbums();
        try{
            Glide.with(this).load(R.drawable.evrgreen).into((ImageView)findViewById(R.id.backdrop));

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    private void initcollapsingtoolbar()
    {
        final CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsingtoolbar);
        collapsingToolbarLayout.setTitle("");
        AppBarLayout appBarLayout=(AppBarLayout)findViewById(R.id.app_bar2);
        appBarLayout.setExpanded(true);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener(){
            boolean isshow=false;
            int scrollrange=-1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(scrollrange==1){
                    scrollrange=appBarLayout.getTotalScrollRange();
                }
                if(scrollrange+verticalOffset==0){
                    collapsingToolbarLayout.setTitle("Collapsing");
                    isshow=true;
                }
                else if(isshow){
                    collapsingToolbarLayout.setTitle("");
                    isshow=false;
                }
            }
        });
    }
    public  void preparealbums(){
         String url=getIntent().getStringExtra("com.example.dell.manya");


        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());


                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Album movie = new Album(obj.getString("name"),obj.getInt("noofsongs"),
                                        obj.getString("image"),obj.getString("urlvideo"),obj.getString("title"));
                                list.add(movie);

                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        albumAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());


            }
        });

        // Adding request to request queue
        queue.add(movieReq);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
newText=newText.toLowerCase();
        ArrayList<Album> arrayListp=new ArrayList<>();
for(Album album:list){
    String name=album.getTitle().toLowerCase();
    if(name.contains(newText))
        arrayListp.add(album);
}
 albumAdapter.setfilter(arrayListp);
        return true;
    }

    public class GridSpacingitemdecoration extends RecyclerView.ItemDecoration{
        private int spancount;
        private int spacing;
        private boolean includeedge;
        public GridSpacingitemdecoration(int spancount,int spacing,boolean includeedge){
            this.spacing=spacing;
            this.includeedge=includeedge;
            this.spancount=spancount;
        }
        public void getItemOffsets(Rect rect, View view, RecyclerView parent, RecyclerView.State state){
            int position=parent.getChildAdapterPosition(view);
            int column=position % spancount;
            if(includeedge){
                rect.left=spacing-column*spacing/spancount;
                rect.right=(column+1)*spacing/spancount;
                if(position<spancount){
                    rect.top=spacing;
                }
                rect.bottom=spacing;
            }
            else
            {
                rect.left=column*spacing/spancount;
                rect.right=spacing-(column+1)*spacing/spancount;
                if(position>=spancount){
                    rect.top=spacing;
                }
            }
        }
    }
    private int dpToPx(int dp){
        Resources resource=getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,resource.getDisplayMetrics()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);

        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }


}
