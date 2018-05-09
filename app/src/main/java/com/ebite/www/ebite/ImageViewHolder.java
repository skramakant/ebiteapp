package com.ebite.www.ebite;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by Ramakant on 08-05-2018.
 */

public class ImageViewHolder extends RecyclerView.ViewHolder  {

    View mView;
    Context mContext;
    ImageViewModel imageViewModel;
    Bitmap imageViewBitmap;
    ImageView imageView;
    ProgressBar progressBar;
    Button shareImage;
    public ImageViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
//        itemView.setOnClickListener(this);
        imageView = (ImageView) mView.findViewById(R.id.ivImage);
        progressBar = (ProgressBar) mView.findViewById(R.id.progressbar);
        shareImage = mView.findViewById(R.id.shareImage);
    }

    public void bindImageData(ImageViewModel model){
        Log.v("ImageViewHolder",model.getImageUrl());
        imageViewModel = model;
        imageView.setDrawingCacheEnabled(true);
        Picasso.get()
                .load(model.getImageUrl())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.placeholder)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });


        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,MapView.class);
                v.buildDrawingCache();

//                Bitmap bitmap = v.getDrawingCache();
//                Bitmap bitmap = Bitmap.createBitmap(v.getDrawingCache());
                intent.putExtra("bitmap",imageViewModel.getImageUrl());
                mContext.startActivity(intent);
                Log.v("MapView","Image Clicked");
            }
        });


        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable mDrawable = imageView.getDrawable();
                Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();

                String path = MediaStore.Images.Media.insertImage(mContext.getContentResolver(), mBitmap, "Image Description", null);
                Uri uri = Uri.parse(path);

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                mContext.startActivity(Intent.createChooser(intent, "Share Image"));
            }
        });
    }



//
//    @Override
//    public void onClick(View v) {
////        (ImageView)v.
//        Intent intent = new Intent(mContext,MapView.class);
//        intent.putExtra("bitmap",imageViewBitmap);
//        mContext.startActivity(intent);
//        Log.v("MapView","Image Clicked");
//
//    }

}
