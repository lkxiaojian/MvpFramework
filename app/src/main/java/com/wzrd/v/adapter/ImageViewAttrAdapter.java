package com.wzrd.v.adapter;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wzrd.R;

/**
 * Created by lk on 2017/10/31.
 */

public class ImageViewAttrAdapter {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {

        if(!TextUtils.isEmpty(imageUrl)){

            Glide.with(view.getContext())
                    .load(imageUrl)
                    .placeholder(R.mipmap.dafen)
                    .error(R.mipmap.feilei_on)
                    .into(view);

        }else{
            view.setImageResource(R.mipmap.dafen_on);
        }

    }
}
