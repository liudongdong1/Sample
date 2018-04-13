package com.baidu.tts.sample;

import android.Manifest;
import android.content.pm.PackageManager;


import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;



import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    // =========== 以下为UI部分 ==================================================

    private Button mSpeak;


    private MixSpeakUtil mixSpeakUtil;




    private static final String TAG = "MiniActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPermission();
        mixSpeakUtil=MixSpeakUtil.getInstance(this);
        mSpeak =  this.findViewById(R.id.speak);
        mSpeak.setOnClickListener(this);
    }




    //  下面是android 6.0以上的动态授权

    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_SETTINGS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.
            }
        }
        String[] tmpList = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.speak:
                EditText editText=this.findViewById(R.id.editText);
                String msg=editText.getText().toString();
                mixSpeakUtil.speak(msg);
                break;
            default:
                break;
        }

    }
}
