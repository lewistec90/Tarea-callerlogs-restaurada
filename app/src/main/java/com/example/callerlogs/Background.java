package com.example.callerlogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;


public class Background extends AppCompatActivity {

    TextView textview = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);

        textview = (TextView) findViewById(R.id.textview_call);

        //if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {

        int checkPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG);
        if(checkPermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, 0);
        } else{
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }

        try{
            getCallDetails();
        }catch (SecurityException e){
            e.printStackTrace();
        }


    }

    private void getCallDetails(){
        StringBuffer sb = new StringBuffer();
        Cursor managedCursor = null;
        managedCursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
        //Cursor managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        sb.append("Call Log :");

        while(managedCursor.moveToNext()){
            String phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);
            String dir = null;
            int dircode = Integer.parseInt(callType);
            switch(dircode){
                case CallLog.Calls.OUTGOING_TYPE:
                    dir="OUTGOING";
                    break;

                case CallLog.Calls.INCOMING_TYPE:
                    dir="INCOMING";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    dir="MISSED";
                    break;
            }
            sb.append("\nPhone Number:--- " + phNumber + " \nCall Type:--- "+ dir + " \nCall Date:--- " + callDayTime + " \nCall duration in sec :--- " + callDuration);
            sb.append("\n---------------------------");
        }
        textview.setText(sb);

    }

}
