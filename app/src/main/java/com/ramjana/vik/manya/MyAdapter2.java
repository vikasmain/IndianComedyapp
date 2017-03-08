package com.ramjana.vik.manya;

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
public class MyAdapter2 extends RecyclerView.Adapter<MyHolder2>  {
    Context c;
    ArrayList<Player2> players,filterList;

    public MyAdapter2(Context ctx, ArrayList<Player2> players)
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

        holder.img.setImageUrl(player2.getImg(), imageLoader);        //IMPLEMENT CLICK LISTENER
        holder.nameTxt.setText(player2.getPos());
        holder.posTxt.setText(player2.getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                switch (pos){
                    case 0:
                        String message="https://app-1486707345.000webhostapp.com/mrbean.json";
                        Intent in1=new Intent(c,newp.class);
                        in1.putExtra("com.example.dell.manya",message);
                        c.startActivity(in1);

                        break;
                    case 1:
                        String message2="https://app-1486707345.000webhostapp.com/netaji.json";
                        Intent in2=new Intent(c,newp.class);
                        in2.putExtra("com.example.dell.manya",message2);
                        c.startActivity(in2);
                        break;
                    case 2:
                        String message3="https://app-1486707345.000webhostapp.com/rajpal.json";
                        Intent in3=new Intent(c,newp.class);
                        in3.putExtra("com.example.dell.manya",message3);
                        c.startActivity(in3);
                        break;
                    case 3:
                        String message4="https://app-1486707345.000webhostapp.com/rajusrivastava.json";
                        Intent in4=new Intent(c,newp.class);
                        in4.putExtra("com.example.dell.manya",message4);
                        c.startActivity(in4);
                        break;
                    case 4:
                        String message5="https://app-1486707345.000webhostapp.com/latestfunny.json";
                        Intent in5=new Intent(c,newp.class);
                        in5.putExtra("com.example.dell.manya",message5);
                        c.startActivity(in5);
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
