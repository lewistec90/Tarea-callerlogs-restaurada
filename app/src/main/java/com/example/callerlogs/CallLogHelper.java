package com.example.callerlogs;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;

public class CallLogHelper {
    public static Cursor getAllCallLogs(ContentResolver cr){
        //Reading all data in descending order according to DATE
        String strOrder = android.provider.CallLog.Calls.DATE + " DESC";
        Uri callUri = Uri.parse("content://call_log/calls");
        Cursor curCallLogs = cr.query(callUri, null, null, null, strOrder);
        return curCallLogs;
    }
}

