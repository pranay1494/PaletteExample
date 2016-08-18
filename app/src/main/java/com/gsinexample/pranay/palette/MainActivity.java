package com.gsinexample.pranay.palette;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private View emptyView;
    private Palette palette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emptyView = findViewById(R.id.emptyview);
        final int dColor = 0x000000;
        ImageView imageView = (ImageView) findViewById(R.id.img);
        imageView.setImageDrawable(getDrawable(R.drawable.salena));

        final Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.salena);
        //synchronous
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                palette = Palette.from(myBitmap).generate();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        emptyView.setBackgroundColor(palette.getVibrantColor(dColor));
                    }
                });
            }
        }).start();
        */


        //Asynchronous
        Palette.from(myBitmap).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette p) {
                emptyView.setBackgroundColor(p.getMutedColor(dColor));
            }
        });
    }
}
