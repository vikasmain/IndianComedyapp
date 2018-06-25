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
public class MyAdapter6 extends RecyclerView.Adapter<MyHolder2>  {
    public static final String Title="com.ramjana.vik.manya";

    Context c;
    ArrayList<Player2> players,filterList;
    public MyAdapter6(Context ctx, ArrayList<Player2> players)
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
                        String message11="https://felicific-hooks.000webhostapp.com/wowsomes/wow1.mp4";
                        Intent in11=new Intent(c,download2.class);
                        in11.putExtra(Title,message11);
                        c.startActivity(in11);
                        break;
                    case 1:
                        String message12="https://felicific-hooks.000webhostapp.com/wowsomes/wow2.mp4";
                        Intent in12=new Intent(c,download2.class);
                        in12.putExtra(Title,message12);
                        c.startActivity(in12);
                        break;
                    case 2:
                        String message13="https://felicific-hooks.000webhostapp.com/wowsomes/wow3.mp4";
                        Intent in13=new Intent(c,download2.class);
                        in13.putExtra(Title,message13);
                        c.startActivity(in13);
                        break;
                    case 3:
                        String message14="https://felicific-hooks.000webhostapp.com/wowsomes/wow4.mp4";
                        Intent in14=new Intent(c,download2.class);
                        in14.putExtra(Title,message14);
                        c.startActivity(in14);
                        break;
                    case 4:
                        String message15="https://felicific-hooks.000webhostapp.com/wowsomes/wow5.mp4";
                        Intent in15=new Intent(c,download2.class);
                        in15.putExtra(Title,message15);
                        c.startActivity(in15);
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
