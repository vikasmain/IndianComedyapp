package com.ramjana.vik.manya;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dell on 12/30/2016.
 */
public class Album {
    private String name;
    private int noofsongs;
    String noofthumbnails;
    private String hmhy;
private String cover;
private String urls;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getHmhy() {
        return hmhy;
    }

    public void setHmhy(String hmhy) {
        this.hmhy = hmhy;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Album(){


    }

    public String getNoofthumbnails() {
        return noofthumbnails;
    }

    public void setNoofthumbnails(String noofthumbnails) {
        this.noofthumbnails = noofthumbnails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoofsongs() {
        return noofsongs;
    }

    public void setNoofsongs(int noofsongs) {
        this.noofsongs = noofsongs;
    }

    public Album(String name, int noofsongs, String noofthumbnails, String urls,String title){
        this.name=name;
        this.noofsongs=noofsongs;
        this.noofthumbnails=noofthumbnails;
        this.urls=urls;
this.title=title;
    }
    public static Album fromJson(JSONObject jsonObject) {
        Album b = new Album();
        // Deserialize json into object fields
        try {
            b.noofsongs = jsonObject.getInt("noofsongs");
            b.name = jsonObject.getString("name");
            b.noofthumbnails = jsonObject.getString("image");
            b.urls = jsonObject.getString("urlvideo");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return b;
    }
}
