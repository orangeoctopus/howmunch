package com.phireworkz.pogee.howmunch.data;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pogee on 8/01/2017.
 */

public class TestUtil {
    public static void insertFakeData(SQLiteDatabase db){
        if(db == null){
            return;
        }
        //create a list of fake guests
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(EventContract.EventEntry.COLUMN_EVENT_NAME, "middle of knowhere day");
        cv.put(EventContract.EventEntry.COLUMN_EVENT_PLACE, "timbuk tu");
        cv.put(EventContract.EventEntry.COLUMN_EVENT_NOTE, "otey oteklsjfkdslfsd dfs f");
        list.add(cv);

        cv = new ContentValues();
        cv.put(EventContract.EventEntry.COLUMN_EVENT_NAME, "expensive restaurnt");
        cv.put(EventContract.EventEntry.COLUMN_EVENT_PLACE, "sdfsdf");
        cv.put(EventContract.EventEntry.COLUMN_EVENT_NOTE, "otey oteklsjfkdslfsd dfs f");
        list.add(cv);

        cv = new ContentValues();
        cv.put(EventContract.EventEntry.COLUMN_EVENT_NAME, "soemone bday");
        cv.put(EventContract.EventEntry.COLUMN_EVENT_PLACE, "weird place");
        cv.put(EventContract.EventEntry.COLUMN_EVENT_NOTE, "otey oteklsjfkdslfsd dfs f");
        list.add(cv);

        cv = new ContentValues();
        cv.put(EventContract.EventEntry.COLUMN_EVENT_NAME, "waonderful event");
        cv.put(EventContract.EventEntry.COLUMN_EVENT_PLACE, "lala land");
        cv.put(EventContract.EventEntry.COLUMN_EVENT_NOTE, "otey oteklsjfkdslfsd dfs f");
        list.add(cv);

        cv = new ContentValues();
        cv.put(EventContract.EventEntry.COLUMN_EVENT_NAME, "eat food");
        cv.put(EventContract.EventEntry.COLUMN_EVENT_PLACE, "blackhole");
        cv.put(EventContract.EventEntry.COLUMN_EVENT_NOTE, "otey oteklsjfkdslfsd dfs f");
        list.add(cv);

        //insert all guests in one transaction
        try
        {
            db.beginTransaction();
            //clear the table first
            db.delete (EventContract.EventEntry.TABLE_NAME,null,null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(EventContract.EventEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }

    }
}
