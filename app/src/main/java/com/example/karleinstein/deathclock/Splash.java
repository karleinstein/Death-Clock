package com.example.karleinstein.deathclock;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.WindowManager;

public class Splash extends Activity {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    MediaPlayer player;
    @Override
    protected void onCreate(@Nullable Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        player = MediaPlayer.create(Splash.this,R.raw.fuck);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);
        player.start();
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Splash.this,MainActivity.class);
                Splash.this.startActivity(mainIntent);
                player.stop();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
    @Override
    protected void onResume() {
        super.onResume();
        player.start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        player.stop();
    }
}
