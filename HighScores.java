package com.example.sachin.pr3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HighScores extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor c;
    TextView tv,tv2;
    String n ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        tv=(TextView)findViewById(R.id.textView1);
        tv2=(TextView)findViewById(R.id.textView2);
        Bundle data = getIntent().getExtras();
        n= data.getString("Name");
        openDatabase();
        c=db.rawQuery("SELECT * FROM scores", null);
        if(!c.moveToFirst())
            tv.setText("No high scores yet!");
        else
        {
            tv.setText(c.getString(0));
            tv2.setText(c.getString(1));
        }
    }

    protected void openDatabase() {
        db = openOrCreateDatabase("ScoresDB.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS scores(name VARCHAR, score NUMBER)");
    }

}
