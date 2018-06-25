package com.ramya.vik.manya;
/**
 * Created by Hp on 3/17/2016.
 */
public class Player2 {
    public Player2(String pos, String img,String name) {
        this.pos = pos;
        this.img = img;
        this.name=name;
    }

    private String name;
    private String pos;
    private String  img;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPos() {
        return pos;
    }
    public void setPos(String pos) {
        this.pos = pos;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
}