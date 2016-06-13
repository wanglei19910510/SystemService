package com.wanglei.systemservice;

import android.app.ActivityManager;
import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button isNetWork;
    private Button bt;
    private Button voice;
    private Button sys;
    private Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //布局系统服务
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_main, null);
        setContentView(view);
        isNetWork = (Button) findViewById(R.id.network);
        bt = (Button) findViewById(R.id.wifi);
        voice = (Button) findViewById(R.id.voice);
        sys = (Button) findViewById(R.id.system);
        isNetWork.setOnClickListener(this);
        bt.setOnClickListener(this);
        voice.setOnClickListener(this);
        sys.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.network:
                Toast.makeText(this, "" + isNetWork(this), Toast.LENGTH_SHORT).show();
                break;
            case R.id.wifi:
                WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
                if (wm.isWifiEnabled()) {
                    wm.setWifiEnabled(false);
                    Toast.makeText(this, "WiFi已经关闭", Toast.LENGTH_SHORT).show();
                } else {
                    wm.setWifiEnabled(true);
                    Toast.makeText(this, "WiFi已经打开", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.voice:
                AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
                int maxAudio = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
                int current = mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
                Toast.makeText(this, "系统的最大音量为" + maxAudio + "系统当前音量为" + current, Toast.LENGTH_SHORT).show();
                break;
            case R.id.system:
               ActivityManager mActivityManager= (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                String packageName = mActivityManager.getRunningTasks(1).get(0).topActivity.getPackageName();
                Toast.makeText(this,packageName,Toast.LENGTH_SHORT).show();
                break;

        }
    }

    //判断是否连接网络
    public boolean isNetWork(Context context) {
        if (context != null) {
            //得到系统服务
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            //得到网络信息
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {

                return mNetworkInfo.isAvailable();
            }

        }
        return false;

    }
}
