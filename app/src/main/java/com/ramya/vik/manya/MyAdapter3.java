package com.ramya.vik.manya;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Hp on 3/17/2016.
 */
public class MyAdapter3 extends RecyclerView.Adapter<MyHolder2>  {
    Context c;
    ArrayList<Player2> players,filterList;

    public MyAdapter3(Context ctx, ArrayList<Player2> players)
    {
        this.c=ctx;
        this.players=players;
        this.filterList=players;
    }

    @Override
    public MyHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        //CONVERT XML TO VIEW ONBJ
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model2,null);
        //HOLDER
        MyHolder2 holder=new MyHolder2(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder2 holder, int position) {
        final Player2 player2 = players.get(position);
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        holder.img.setImageUrl(player2.getImg(), imageLoader);        //IMPLEMENT CLICK LISTENET
        holder.nameTxt.setText(player2.getPos());
        holder.posTxt.setText(player2.getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                switch (pos){
                    case 0:
                        String message="https://app-1486707345.000webhostapp.com/rajesharora.json";
                        Intent in6=new Intent(c,newp.class);
                        in6.putExtra("com.example.dell.manya",message);
                        c.startActivity(in6);
                        break;
                    case 1:
                        String message7="https://app-1486707345.000webhostapp.com/siticable.json";
                        Intent in7=new Intent(c,newp.class);
                        in7.putExtra("com.example.dell.manya",message7);
                        c.startActivity(in7);
                        break;
                    case 2:
                        String message8="https://app-1486707345.000webhostapp.com/bestofkaps.json";
                        Intent in8=new Intent(c,newp.class);
                        in8.putExtra("com.example.dell.manya",message8);
                        c.startActivity(in8);
                        break;
                    case 3:
                        String message9="https://app-1486707345.000webhostapp.com/drgulati.json";
                        Intent in9=new Intent(c,newp.class);
                        in9.putExtra("com.example.dell.manya",message9);
                        c.startActivity(in9);
                        break;


                    default:break;
                }
            }
        });
    }



    //GET TOTAL NUM OF PLAYERS
    @Override
    public int getItemCount() {
        return players.size();
    }

    public void setfilter(ArrayList<Player2> newlist){
        players=new ArrayList<>();
        players.addAll(newlist);
        notifyDataSetChanged();
    }

}
