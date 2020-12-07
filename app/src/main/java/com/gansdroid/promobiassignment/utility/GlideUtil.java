package com.gansdroid.promobiassignment.utility;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.gansdroid.promobiassignment.R;

public class GlideUtil {

    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .centerCrop()
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(true)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
    }
}
