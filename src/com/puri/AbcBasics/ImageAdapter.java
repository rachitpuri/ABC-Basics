package com.puri.AbcBasics;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {  
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            Integer width = mContext.getResources().getInteger(R.integer.character_width);
            Integer height = mContext.getResources().getInteger(R.integer.character_height);
            imageView.setLayoutParams(new GridView.LayoutParams(width, height));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 1, 0);   
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.image_a, R.drawable.image_b,
            R.drawable.image_c, R.drawable.image_d,
            R.drawable.image_e, R.drawable.image_f,
            R.drawable.image_g, R.drawable.image_h,
            R.drawable.image_i, R.drawable.image_j,
            R.drawable.image_k, R.drawable.image_l,
            R.drawable.image_m, R.drawable.image_n,
            R.drawable.image_o, R.drawable.image_p,
            R.drawable.image_q, R.drawable.image_r,
            R.drawable.image_s, R.drawable.image_t,
            R.drawable.image_u, R.drawable.image_v,
            R.drawable.image_w, R.drawable.image_x,
            R.drawable.image_y, R.drawable.image_z,
    };
}