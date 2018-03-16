package com.example.karleinstein.deathclock;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends Activity {
    Spinner spinnerSmoking,spinnerMode,spinnerMonth,spinnerSex,spinnerBMI;
    EditText edDay,edYear,edHeight,edWeight,edIn;
    Button btnShow;
    long a;
    int age,age1,age2,age3,age4;
    double bmi;
    Button checkBMI;
    FileOutputStream fileOutputStream;
    String strSmoke,strMode,strSex,strMonth,strBMI;
    AlertDialog.Builder builder;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        edDay = findViewById(R.id.edDay);
        player = MediaPlayer.create(MainActivity.this,R.raw.aoe);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);
        player.start();
        edIn=findViewById(R.id.edIn);
        checkBMI=findViewById(R.id.checkBMI);
        edHeight=findViewById(R.id.edHeight);
        edWeight=findViewById(R.id.edWeight);
        builder=new AlertDialog.Builder(this);
        spinnerBMI = findViewById(R.id.spinnerBMI);
        spinnerMode = findViewById(R.id.spinnerMode);
        spinnerMonth = findViewById(R.id.spinnerMonth);
        spinnerSex = findViewById(R.id.spinnerSex);
        spinnerSmoking = findViewById(R.id.spinnerSmoking);
        edYear = findViewById(R.id.edYear);
        btnShow = findViewById(R.id.btnShow);
        final Calendar c = Calendar.getInstance();
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        List<String> list4 = new ArrayList<>();
        list.add("January");
        list.add("February");
        list.add("March");
        list.add("April");
        list.add("May");
        list.add("June");
        list.add("July");
        list.add("August");
        list.add("September");
        list.add("October");
        list.add("November");
        list.add("December");
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerMonth.setAdapter(adapter);
        list1.add("Male");
        list1.add("Female");
        ArrayAdapter<String> adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list1);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerSex.setAdapter(adapter1);
        list2.add("Normal");
        list2.add("Pessimistic");
        list2.add("Sadistic");
        list2.add("Toxic");
        list2.add("Optimistic");
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list2);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerMode.setAdapter(adapter2);
        list3.add("Smoker");
        list3.add("Non-Smoker");
        ArrayAdapter<String> adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,list3);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerSmoking.setAdapter(adapter3);
        list4.add("<25");
        list4.add("25");
        list4.add("26");
        list4.add("27");
        list4.add("28");
        list4.add("29");
        list4.add("30");
        list4.add("31");
        list4.add("32");
        list4.add("33");
        list4.add("34");
        list4.add("35");
        list4.add("36");
        list4.add("38");
        list4.add("39");
        list4.add("40");
        list4.add("41");
        list4.add("42");
        list4.add("43");
        list4.add("44");
        list4.add(">=45");
        ArrayAdapter<String> adapter4 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list4);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerBMI.setAdapter(adapter4);
        strSmoke = spinnerSmoking.getSelectedItem().toString();
        strMode = spinnerMode.getSelectedItem().toString();
        strSex = spinnerSex.getSelectedItem().toString();
        strMonth = spinnerMonth.getSelectedItem().toString();
        strBMI = spinnerBMI.getSelectedItem().toString();
        spinnerSmoking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    age = -10;
                }
                if (position == 1) {
                    age = 0;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    age1 = 0;
                }
                if (position == 1) {
                    age1 = -10;
                }
                if (position == 2) {
                    age1 = -20;
                }
                if (position == 3) {
                    age1 = -25;
                }
                if (position == 4) {
                    age1 = 5;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    age2 = 75;
                }
                if (position == 1) {
                    age2 = 80;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerBMI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    age3 = -10;
                }
                if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7) {
                    age3 = -5;
                }
                if (position == 15 || position == 16 || position == 17 || position == 18 || position == 19) {
                    age3 = -5;
                }
                if (position == 20) {
                    age3 = -10;
                } else age3 = +5;
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

                btnShow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (edYear.getText().toString().length()>0 &&edDay.getText().toString().length()>0)
                        {
                            a = Integer.parseInt(edYear.getText().toString());
                            if (a > 1940&&a<2018) {
                                int l = age + age1 + age2 + age3 + age4;
                                long tuoi = a * 525960 + l * 525960;
                                long result = tuoi - 2018 * 525960;
                                android.util.Log.i("Time Class ", " Age: " + l);
                                Log.d("Duong dan bo nho may", getFilesDir().getAbsolutePath());
                                Save(String.valueOf(result));

                                Intent intent = new Intent(MainActivity.this, ShowDate.class);
                                startActivity(intent);
                                player.stop();
                                edYear.setText("");
                                edDay.setText("");

                            }
                            if (a<1940){
                                builder.setTitle("Dog brain")
                                        .setCancelable(false)
                                        .setMessage("Do you want to predict to your grandfather?")
                                        .setPositiveButton("Im sorry :(", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        }).show();
                            }
                            if (a>=2018){
                                builder.setTitle("GG that kid")
                                        .setCancelable(false)
                                        .setMessage("You are still not Born !!! , Do you wish to predict for your future baby?")
                                        .setPositiveButton("Im sorry :(", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        }).show();
                            }
                        }
                        else {
                            builder.setTitle("Fuck you")
                                    .setCancelable(false)
                                    .setMessage("Do you want to eat walnuts?")
                                    .setPositiveButton("No God Please No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).show();
                        }

                    }



                    private void Save(String dulieu) {
                        try {
                            fileOutputStream = openFileOutput("Second.txt", Context.MODE_PRIVATE);
                            fileOutputStream.write(dulieu.getBytes());

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
                checkBMI.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (edHeight.getText().toString().length()>0 &&edWeight.getText().toString().length()>0
                                &&edIn.getText().toString().length()>0)
                        {
                            int height= Integer.parseInt(edHeight.getText().toString());
                            int weight= Integer.parseInt(edWeight.getText().toString());
                            int in= Integer.parseInt(edIn.getText().toString());
                            int fuck=height*12+in;
                            bmi=Math.round((weight*703)/(fuck*fuck));
                            builder.setCancelable(false)
                                    .setMessage("Your BMI is"+bmi)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).show();
                        }
                        else
                        {
                            builder.setTitle("Fuck you")
                                    .setCancelable(false)
                                    .setMessage("Do you want to eat walnuts?")
                                    .setPositiveButton("No God Please No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).show();
                        }

                    }
                });
            }
    @Override
    protected void onRestart() {
        super.onRestart();
        player.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.stop();
    }
    }
