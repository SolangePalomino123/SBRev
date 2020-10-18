package com.sbr.sbrevapp;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseInstance extends FirebaseInstanceIdService {

    public static final String TAG ="NOTICIAS";

    public void onToken(){
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"Token: "+token);
    }
}
