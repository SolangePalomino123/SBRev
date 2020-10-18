package com.sbr.sbrevapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends Activity {
    private static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn = (Button) findViewById(R.id.btnToken);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = FirebaseInstanceId.getInstance().getToken();
                Log.d(TAG, token);
                Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
            }
        });

        setup();

    }

    private void setup(){
        Button btnAuth =(Button)findViewById(R.id.btnAuth);
        btnAuth.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startAuth();
            }
        });
    }
    private void startAuth(){
        Intent authIntent=new Intent(this, NotificationAlert.class);
        startActivity(authIntent);
    }
}