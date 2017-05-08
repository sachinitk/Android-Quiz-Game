package com.example.sachin.pr3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {
        TextView tv;
    String n;
    Button btn,btn2,btn3;
    SharedPreferences sharedPreferences;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        tv = (TextView)findViewById(R.id.textView1);
        btn= (Button)findViewById(R.id.button1);
            btn2= (Button)findViewById(R.id.button2);
            btn3= (Button)findViewById(R.id.button3);
            Bundle data = getIntent().getExtras();
            n= data.getString("Name");
            tv.setText("Welcome "+n);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sharedPreferences = getSharedPreferences(LoginScreen.MyPREFERENCES,Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.commit();
                    Intent i = new Intent(getApplicationContext(),LoginScreen.class);
                    i.putExtra("Name",n);
                    startActivity(i);
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent i = new Intent(getApplicationContext(),GameStart.class);
                    i.putExtra("Name",n);
                    startActivity(i);

                }
            });
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent i = new Intent(getApplicationContext(),HighScores.class );
                    i.putExtra("Name",n);
                    startActivity(i);
                }
            });
        }
}
