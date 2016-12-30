package com.phireworkz.pogee.howmunch.data;

/**
 * Created by Pogee on 17/12/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.phireworkz.pogee.howmunch.Event;
import com.phireworkz.pogee.howmunch.data.EventContract.EventEntry;

public class EventDbHelper extends SQLiteOpenHelper {


    static final String DATABASE_NAME = "event.db";

    private static final int DATABASE_VERSION = 1;

    public EventDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); //takes context and calls parent constructor
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_EVENT_TABLE = "CREATE TABLE " + EventEntry.TABLE_NAME + " (" +
                EventEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                EventEntry.COLUMN_EVENT_NAME + "TEXT NOT NULL" +
                EventEntry.COLUMN_EVENT_NOTE + "TEXT NOT NULL" +
                " );";

        sqLiteDatabase.execSQL(SQL_CREATE_EVENT_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EventEntry.TABLE_NAME);
        onCreate((sqLiteDatabase));

    }
}
