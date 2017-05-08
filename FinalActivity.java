package com.example.sachin.pr3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {
    TextView tv,tv2;
    private SQLiteDatabase db;
    private Cursor c;
    Button btn;
    int s;
    String n,query;
    private static final String x="SELECT * FROM scores";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
    tv = (TextView)findViewById(R.id.textView3);
        tv2 = (TextView)findViewById(R.id.textView2);
        btn = (Button)findViewById(R.id.button1);
        Bundle data = getIntent().getExtras();
        s= data.getInt("score");
        n= data.getString("Name");
        tv.setText("score: "+s);
        createdatabase();
        c=db.rawQuery(x, null);
        if(!c.moveToFirst())
        {
            query="INSERT INTO scores VALUES('"+n+"','"+s+"')";
            db.execSQL(query);
            tv2.setText("You got a high score!");
        }
        else
        {
            c.moveToLast();
            if(s>=Integer.parseInt(c.getString(1)))
            {
                query="UPDATE scores SET Name='"+n+"', score='"+s+"' where score='"+c.getString(1)+"'";
                db.execSQL(query);
                tv2.setText("You got a high score! ");
            }
        }
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i=new Intent(getApplicationContext(), HomeScreen.class);
                i.putExtra("Name",n);
                startActivity(i);
            }
        });
    }
    protected void createdatabase()
    {
        db=openOrCreateDatabase("ScoresDB.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS scores(Name VARCHAR, score NUMBER)");
    }

}
