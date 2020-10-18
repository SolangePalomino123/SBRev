package com.sbr.sbrevapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class NotificationAlert extends Activity {

    ImageView imgViewHand;
    Button btnCaptureHand;
    static final int REQUEST_IMAGE_CAPTURE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_alert);

        imgViewHand=(ImageView)findViewById(R.id.imgViewHand);
        btnCaptureHand=(Button)findViewById(R.id.btnCaptureHand);

        btnCaptureHand.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                llamarIntent();
            }
        });
    }

    private void llamarIntent(){
        Intent takePictureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){
            Bundle extras=data.getExtras();
            Bitmap imageBitmap=(Bitmap)extras.get("data");
            imgViewHand.setImageBitmap(imageBitmap);
        }
    }
}