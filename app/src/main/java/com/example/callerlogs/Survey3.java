package com.example.callerlogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class Survey3 extends AppCompatActivity {

    private RadioButton s3_option_always;
    private RadioButton s3_option_usually;
    private RadioButton s3_option_about;
    private RadioButton s3_option_rarely;

    private String answer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey3);

        s3_option_always = (RadioButton) findViewById(R.id.rb_alwaysID);
        s3_option_usually = (RadioButton) findViewById(R.id.rb_usuallyID);
        s3_option_about = (RadioButton) findViewById(R.id.rb_aboutID);
        s3_option_rarely = (RadioButton) findViewById(R.id.rb_rarely2ID);

    }
    @Override
    public void onBackPressed(){
        Toast.makeText(this, "No puede retroceder", Toast.LENGTH_SHORT).show();
    }

    /*public void clickFunction(){
        Intent intent = new Intent(this, Survey3.class);
        startActivity(intent);
        Toast.makeText(this, "Tercera pregunta", Toast.LENGTH_SHORT).show();
    }*/

    public void onclick(View view){
        if(view.getId() == R.id.bFinalID){
            //validar();
            //getData();
            //clickFunction();
            validar();
            answer3 = validar2();
            databaseData(answer3);

            Toast.makeText(this, "SURVEY 3", Toast.LENGTH_SHORT).show();
            clickFunction();
        }
    }

    public void clickFunction(){
        Intent intent = new Intent(this, Background.class);
        startActivity(intent);
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

        Long idResult = db.insert("survey3", null, values);

        Toast.makeText(this, "RESPUESTA: "+ ans, Toast.LENGTH_LONG).show();

        //flagSurveyStarted = true;
        Toast.makeText(getApplicationContext(), "id registro: "+ idResult, Toast.LENGTH_SHORT).show();
        db.close();

    }

    public String validar2(){
        String cadena ="";
        if(s3_option_always.isChecked()){
            cadena+="Siempre";
            answer3 = cadena;
        } else if(s3_option_usually.isChecked()){
            cadena += "Usualmente";
            answer3 = cadena;
        } else if(s3_option_about.isChecked()){
            cadena += "Acerca";
            answer3 = cadena;
        } else if(s3_option_rarely.isChecked()){
            cadena += "Raramente";
            answer3 = cadena;
        } else{
            Toast.makeText(this, "No answer?", Toast.LENGTH_SHORT).show();
        }
        return answer3;
    }


    public void validar() {
        String cadena = "";
        if (s3_option_always.isChecked()) {
            cadena += "Siempre";
        } else if (s3_option_usually.isChecked()) {
            cadena += "Usualmente";
        } else if (s3_option_about.isChecked()) {
            cadena += "Mitad";
        } else if (s3_option_rarely.isChecked()) {
            cadena += "Raro acuerdo";
        } else {
            Toast.makeText(this, "Debe seleccionar una opción", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, cadena, Toast.LENGTH_SHORT).show();
    }
}
