package com.example.du.takephotodemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int TAKE_PHOTO=1;
    private ImageView showImage;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button takePhoto= (Button) findViewById(R.id.button);
        showImage= (ImageView) findViewById(R.id.imageView);

        takePhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                File outPutImage=new File(getExternalCacheDir(),"output_image.jpg");

                try {
                    if (outPutImage.exists()){
                        outPutImage.delete();
                    }
                    outPutImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT>=24){
                    imageUri= FileProvider.getUriForFile(MainActivity.this,
                            "com.example.du.takephotodemo",outPutImage);
                }else {
                    imageUri=Uri.fromFile(outPutImage);
                }
                Intent intent=new Intent("android.media.action.I");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);

                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case TAKE_PHOTO:
                if (requestCode==RESULT_OK){
                    try {
                        Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        showImage.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
