package com.example.callerlogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class Survey2 extends AppCompatActivity {

    private RadioButton s2_option_sagree;
    private RadioButton s2_option_agree;
    private RadioButton s2_option_neutral;
    private RadioButton s2_option_disagree;
    private RadioButton s2_option_sdiagree;

    private String answer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey2);

        s2_option_sagree = (RadioButton) findViewById(R.id.rb_agreeID);
        s2_option_agree = (RadioButton) findViewById(R.id.rb_agree2ID);
        s2_option_neutral = (RadioButton) findViewById(R.id.rb_neutralID);
        s2_option_disagree = (RadioButton) findViewById(R.id.rb_disagreeID);
        s2_option_sdiagree = (RadioButton) findViewById(R.id.rb_disagree2ID);
    }

    @Override
    public void onBackPressed(){
        Toast.makeText(this, "No puede retroceder", Toast.LENGTH_SHORT).show();
    }

    public void clickFunction(){
        Intent intent = new Intent(this, Survey3.class);
        startActivity(intent);
        Toast.makeText(this, "Tercera pregunta", Toast.LENGTH_SHORT).show();
    }

    public void onclick(View view){
        if(view.getId() == R.id.bFinalID){
            //validar();
            //getData();
            //clickFunction();
            validar();
            answer2 = validar2();
            databaseData(answer2);
            clickFunction();
            //Toast.makeText(this, "SURVEY 3", Toast.LENGTH_SHORT).show();


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

        Long idResult = db.insert("survey2", null, values);

        Toast.makeText(this, "RESPUESTA: "+ ans, Toast.LENGTH_LONG).show();

        //flagSurveyStarted = true;
        Toast.makeText(getApplicationContext(), "id registro: "+ idResult, Toast.LENGTH_SHORT).show();
        db.close();
    }


    public String validar2(){
        String cadena = "";
        if (s2_option_sagree.isChecked()){
            cadena += "completamente de acuerdo";
            answer2=cadena;
        } else if(s2_option_agree.isChecked()){
            cadena += "de acuerdo";
            answer2=cadena;
        } else if(s2_option_neutral.isChecked()){
            cadena += "neutral";
            answer2=cadena;
        } else if(s2_option_disagree.isChecked()){
            cadena += "des acuerdo";
            answer2=cadena;
        } else if(s2_option_sdiagree.isChecked()){
            cadena += "Completamente en des acuerdo";
            answer2=cadena;
        }
        else {
            Toast.makeText(this, "No ANSWER?", Toast.LENGTH_SHORT).show();
        }

        return answer2;
    }

    public void validar() {
        String cadena = "";
        if (s2_option_sagree.isChecked()) {
            cadena += "completamente de acuerdo";
        } else if (s2_option_agree.isChecked()) {
            cadena += "de acuerdo";
        } else if (s2_option_neutral.isChecked()) {
            cadena += "neutral";
        } else if (s2_option_disagree.isChecked()) {
            cadena += "des acuerdo";
        } else if (s2_option_sdiagree.isChecked()) {
            cadena += "totalmente des acuerdo";
        } else {
            Toast.makeText(this, "Debe seleccionar una opción", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, cadena, Toast.LENGTH_SHORT).show();
    }
}
