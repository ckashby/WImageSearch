package com.meteoru.kalei.wimagesearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ImageResult {
    private String tbUrl;
    private String url;
    private String title;

    public String getTbUrl() {
        return tbUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    // New ImageResult from raw JSON
    public ImageResult(JSONObject json){
        try {
            this.tbUrl = json.getString("tbUrl");
            this.url  = json.getString("url");
            this.title = json.getString("title");
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    // ArrayList of ImageResult objects from JSONArray
    public static ArrayList<ImageResult> fromJson(JSONArray array){
        ArrayList<ImageResult> imageResultsArrayList = new ArrayList<ImageResult>();
        for (int i = 0; i < array.length(); i++){
            try {
                imageResultsArrayList.add(new ImageResult(array.getJSONObject(i)));
            } catch (JSONException e){
                e.printStackTrace();
            }
        } return imageResultsArrayList;
    }


}
