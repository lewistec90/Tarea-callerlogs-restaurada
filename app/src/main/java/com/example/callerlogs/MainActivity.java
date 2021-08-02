package com.example.callerlogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.TooManyListenersException;

public class MainActivity extends AppCompatActivity {

    private int flagSurveyStarted;
    public int count;
    private Button b_survey1;
    public static String dataBaseName = "callerDB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b_survey1 = (Button) findViewById(R.id.buttonNextID);

        //flagSurveyStarted = 0;
        ConectionSQLiteHelper connect = new ConectionSQLiteHelper(this, MainActivity.dataBaseName, null, 1);
        SQLiteDatabase db = connect.getReadableDatabase();
        if(validate(db,"survey1")==false){
            flagSurveyStarted = 0;
        }else{
            flagSurveyStarted = 1;
        }
    }

    public boolean validate(SQLiteDatabase mydb, String tablename){
        Cursor c = mydb.rawQuery("SELECT * FROM "+tablename, null);
        return c.moveToFirst();
    }

     public void onClick(View view){
        //flagSurveyStarted = 1;

        //--------- MODIFICARRRRRRRRRR
        /*ConectionSQLiteHelper connect = new ConectionSQLiteHelper(this, MainActivity.dataBaseName, null, 1);
        SQLiteDatabase db = connect.getReadableDatabase();
        String query = "SELECT * FROM survey1";
        String[] columns = {"id", "used", "ans"};
        Cursor res = db.query("survey1", columns, null, null, null, null, null,null);
        String path="";
        if(res.moveToFirst()){
            do{
                path=res.getString(1);
                Toast.makeText(this, "FROM DATABASE:" + path, Toast.LENGTH_SHORT).show();
            } while (res.moveToNext());

        }
        res.close();
        int val = Integer.parseInt(path);
        flagSurveyStarted = val;*/
        // -----------

        if(flagSurveyStarted == 0){
            fsurvey1(view);
        }
        else{
            fbackground(view);
        }
    }

    public void fsurvey1(View view){
        
        Intent intent = new Intent(this, Survey1.class);
        startActivity(intent);
        Toast.makeText(this, "Survey #1", Toast.LENGTH_SHORT).show();
    }

    public void fbackground(View view){
        Intent intent = new Intent(this, Background.class);
        startActivity(intent);
        Toast.makeText(this, "BACKGROUND", Toast.LENGTH_SHORT).show();
    }
}
