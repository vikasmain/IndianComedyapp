package com.ramya.vik.manya;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by Hp on 3/17/2016.
 */
public class MyHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
    //OUR VIEWS
    Context ctx;
    NetworkImageView img;
    TextView nameTxt,posTxt;
    ItemClickListener itemClickListener;
    public MyHolder2(View itemView) {
        super(itemView);
        this.img= (NetworkImageView) itemView.findViewById(R.id.kapiln);
        this.nameTxt= (TextView) itemView.findViewById(R.id.textkaps);
        this.posTxt= (TextView) itemView.findViewById(R.id.tgdg);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }
    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }
}