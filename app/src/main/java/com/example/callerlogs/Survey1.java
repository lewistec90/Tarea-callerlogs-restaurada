package com.example.callerlogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.callerlogs.tools.Tools;

public class Survey1 extends AppCompatActivity {

    private RadioButton s1_option_sagree;
    private RadioButton s1_option_agree;
    private RadioButton s1_option_neutral;
    private RadioButton s1_option_disagree;
    
    private String answer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey1);

        s1_option_sagree = (RadioButton) findViewById(R.id.rb_agreeID);
        s1_option_agree = (RadioButton) findViewById(R.id.rb_agree2ID);
        s1_option_neutral = (RadioButton) findViewById(R.id.rb_neutralID);
        s1_option_disagree = (RadioButton) findViewById(R.id.rb_disagreeID);

    }

    @Override
    public void onBackPressed(){
        Toast.makeText(this, "No puede retroceder", Toast.LENGTH_SHORT).show();
    }

    public void onclick(View view){
        if(view.getId() == R.id.bFinalID){
            //validar();
            answer1 = validar2();
            //getData();
            databaseData(answer1); //escribe datos
            //databaseConsult(); //consultar datos
            clickFunction();
        }
    }
    
    public void databaseData(String ans){

        ConectionSQLiteHelper connect = new ConectionSQLiteHelper(this, MainActivity.dataBaseName, null, 1);
        SQLiteDatabase db = connect.getWritableDatabase();
        ContentValues values = new ContentValues();
        // -------
        // pertence a validar()
        //values.put("id", s1_option_sagree.getText().toString());
        //_------

        // pertenece a validar2()
        //values.put("id", 0);
        values.put("used",1);// 1 es true
        values.put("ans",ans);

        Long idResult = db.insert("survey1", null, values);

        Toast.makeText(this, "RESPUESTA: "+ ans, Toast.LENGTH_LONG).show();

        //flagSurveyStarted = true;
        Toast.makeText(getApplicationContext(), "id registro: "+ idResult, Toast.LENGTH_SHORT).show();
        db.close();

    }
    public void databaseConsult(){
        ConectionSQLiteHelper connect = new ConectionSQLiteHelper(this, MainActivity.dataBaseName, null, 1);
        SQLiteDatabase db = connect.getReadableDatabase();

        String query = "SELECT * FROM survey1";
        String[] columns = {"id", "used", "ans"};
        Cursor res = db.query("survey1", columns, null, null, null, null, null,null);
        if(res.moveToFirst()){
            do{
                String path=res.getString(1);
                Toast.makeText(this, "FROM DATABASE:" + path, Toast.LENGTH_SHORT).show();
            } while (res.moveToNext());
        }

        res.close();
        //Toast.makeText(this, res.getString(0), Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, res.getString(1), Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, res.getString(2), Toast.LENGTH_SHORT).show();

        /*while(res.isAfterLast() == false) {
            res.getString(res.getColumnIndex("name"));
            res.moveToNext();
            Toast.makeText(this, res.toString(), Toast.LENGTH_SHORT).show();
        }*/

    }

    public void clickFunction(){
        Intent intent = new Intent(this, Survey2.class);
        startActivity(intent);
        Toast.makeText(this, "Segunda pregunta", Toast.LENGTH_SHORT).show();
    }

    public void validar(){
        String cadena = "";
        if (s1_option_sagree.isChecked()){
            cadena += "completamente de acuerdo";
            answer1=cadena;
        } else if(s1_option_agree.isChecked()){
            cadena += "de acuerdo";
            answer1=cadena;
        } else if(s1_option_neutral.isChecked()){
            cadena += "neutral";
            answer1=cadena;
        } else if(s1_option_disagree.isChecked()){
            cadena += "des acuerdo";
            answer1=cadena;
        } else {
            Toast.makeText(this, "Debe seleccionar una opción", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, cadena, Toast.LENGTH_SHORT).show();
    }
    public String validar2(){
        String cadena = "";
        if (s1_option_sagree.isChecked()){
            cadena += "completamente de acuerdo";
            answer1=cadena;
        } else if(s1_option_agree.isChecked()){
            cadena += "de acuerdo";
            answer1=cadena;
        } else if(s1_option_neutral.isChecked()){
            cadena += "neutral";
            answer1=cadena;
        } else if(s1_option_disagree.isChecked()){
            cadena += "des acuerdo";
            answer1=cadena;
        } else {
            Toast.makeText(this, "No ANSWER?", Toast.LENGTH_SHORT).show();
        }
        return answer1;
    }
    
    /*public void getData(){
        String sa = (String) s1_option_sagree.getText().toString();
        String a = (String) s1_option_agree.getText().toString();
        String n = (String) s1_option_neutral.getText().toString();
        String d = (String) s1_option_disagree.getText().toString();

        Toast.makeText((this), "Entering to getData", Toast.LENGTH_SHORT).show();
        String Result = sa + a + n + d ;
        try{
            File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File (sdCard.getAbsolutePath() + "/MyFiles");
            directory.mkdirs();
            File file = new File(directory, "androfile.txt");
            FileOutputStream fOut = new FileOutputStream(file);

            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write(Result);
            osw.flush();
            osw.close();

        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }


    }*/

}
