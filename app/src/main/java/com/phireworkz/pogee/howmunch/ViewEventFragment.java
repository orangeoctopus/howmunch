package com.phireworkz.pogee.howmunch;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.phireworkz.pogee.howmunch.data.EventContract;
import com.phireworkz.pogee.howmunch.data.EventDbHelper;

/**
 * Created by Pogee on 3/02/2017.
 */

public class ViewEventFragment extends Fragment {

    private SQLiteDatabase mDb;

    private EventDbHelper dbHelper;

    private Cursor c;

    private TextView mEventText;

    private int eventIDToView;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper =  new EventDbHelper(this.getActivity());
        mDb = dbHelper.getReadableDatabase();

        Bundle arguments = getArguments();
        if (arguments != null) {
            eventIDToView = arguments.getInt("selectedID");

        }

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.viewevent, container, false);
        mEventText = (TextView) rootView.findViewById(R.id.tv_ename);

        Cursor cursor = getEvent(eventIDToView);

        String name = "no name";
        String date = "no date";

        while(cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_NAME));
            date = cursor.getString(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_TIMESTAMP));
        }
        cursor.close();


        mEventText.setText(name + " " + date);

        return rootView;
    }

    private Cursor getEvent(int id) {


        String[] projection = {
                EventContract.EventEntry.COLUMN_EVENT_NAME,
                EventContract.EventEntry.COLUMN_EVENT_PLACE,
                EventContract.EventEntry.COLUMN_EVENT_NOTE,
                //EventContract.EventEntry.COLUMN_EVENT_PHOTO,
                EventContract.EventEntry.COLUMN_EVENT_TIMESTAMP
        };

        String selection = EventContract.EventEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

//        Cursor cursor = db.query(EventContract.EventEntry., new String[] { KEY_ID,
//                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();

        return mDb.query(
                EventContract.EventEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                EventContract.EventEntry.COLUMN_EVENT_TIMESTAMP
        );


    }

    private boolean deleteEvent(int id) {
        return mDb.delete(EventContract.EventEntry.TABLE_NAME, EventContract.EventEntry._ID + "=" + id, null) > 0;
        // -1 if fail otherwise its no of row
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            deleteEvent(eventIDToView);
            getActivity().finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
