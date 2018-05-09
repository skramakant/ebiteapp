package com.ebite.www.ebite;

import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class MapView extends AppCompatActivity {
    private TouchImageView touchImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        touchImageView = (TouchImageView) findViewById(R.id.map_view);
        if(getIntent().hasExtra("bitmap")){
//            Bitmap image_bitmap = (Bitmap) getIntent().getExtras().getParcelable("bitmap");
            String url = getIntent().getExtras().getString("bitmap");
            Picasso.get()
                    .load(url)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .placeholder(R.mipmap.placeholder)
                    .error(R.mipmap.placeholder)
                    .into(touchImageView);

//            if(image_bitmap != null){
//                Log.v("MapView","NOT NULL BITMAP");
//                touchImageView.setImageBitmap(image_bitmap);
//            }else {
//                Log.v("MapView","NULL BITMAP");
//            }
        }
    }
}


