package com.example.karleinstein.deathclock;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ShowDate extends Activity {
    TextView tvShow,tvDate;
    String a;
    int b;
    int gg;
    String datez;
    MediaPlayer player;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showdate);
        player = MediaPlayer.create(ShowDate.this,R.raw.best);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);
        player.start();
        builder = new AlertDialog.Builder(ShowDate.this);
        Take();
        b=Integer.parseInt(a)*60;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        tvShow=findViewById(R.id.tvShow);
        tvDate=findViewById(R.id.tvDate);
        if(b<0){
            builder.setMessage("I am sorry, but your time has expired!\n" +
                    "Have a nice day.")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).show();
            tvShow.setText("0");
            tvDate.setText("RIP!!!!");
        }
        else {
            //Declare the timer
            Timer t = new Timer();
//Set the schedule function and rate
            t.scheduleAtFixedRate(new TimerTask() {

                                      @Override
                                      public void run() {
                                          //Called each time when 1000 milliseconds (1 second) (the period parameter)
                                          runOnUiThread(new Runnable() {

                                              @Override
                                              public void run() {
                                                  tvShow.setText(String.valueOf(b));
                                                  b-=1;
                                              }

                                          });
                                      }
                                  },
//Set how long before to start calling the TimerTask (in milliseconds)
                    0,
//Set the amount of time between each execution (in milliseconds)
                    1000);
            NumberFormat formatter = new DecimalFormat("#0");
            double lz=b*0.0000115741;
            datez=formatter.format(lz);
            gg= Integer.parseInt(datez);
            Calendar calendar = Calendar.getInstance();

            calendar.add(Calendar.DAY_OF_YEAR, gg);
            Date tomorrow = calendar.getTime();
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            String tomorrowAsString = dateFormat.format(tomorrow);
            tvDate.setText(tomorrowAsString);
        }



    }

    @Override
    protected void onRestart() {
        super.onRestart();
        player.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stop();
    }

    private void Take() {
                    String dulieu;
                        try {
                    FileInputStream fileInputStream=openFileInput("Second.txt");
                    InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
                    BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                    StringBuffer stringBuffer=new StringBuffer();
                    while ((dulieu=bufferedReader.readLine())!=null)
                    {
                        stringBuffer.append(dulieu+"");
                    }
                    a= String.valueOf(stringBuffer);
                    bufferedReader.close();
                    inputStreamReader.close();
                    fileInputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
               } catch (IOException e) {
                    e.printStackTrace();
                }
    }
}
