package com.meteoru.kalei.wimagesearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageResultAdapter extends ArrayAdapter<ImageResult> {
    public ImageResultAdapter(Context context, List<ImageResult> images) {
        super(context, R.layout.item_image_result, images);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for the position
        ImageResult imageResult = getItem(position);
        // Check if viewholder exists
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_image_result, parent, false);
        }
        // Get references to the objects in the layout.xml
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
        ivImage.setImageResource(0);
        // Set the text for title from ImageResult
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        tvTitle.setText(imageResult.title);
        Picasso.with(getContext()).load(imageResult.tbUrl).into(ivImage);



        //
        return convertView;
    }
}
