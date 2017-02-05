package com.phireworkz.pogee.howmunch;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.phireworkz.pogee.howmunch.data.EventContract;
import com.phireworkz.pogee.howmunch.data.EventDbHelper;

/**
 * Created by Pogee on 17/12/2016.
 */

public class AddEventActivity extends AppCompatActivity {

    public Context mContext;

    private SQLiteDatabase mDb;

    private EditText etEventName;
    private EditText etEventPlace;
    private  EditText etEventNote;
    private  EditText etEventDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent);
        mContext = getApplicationContext();

        etEventName = (EditText) findViewById(R.id.et_event_name);
        etEventPlace = (EditText) findViewById(R.id.et_event_place);
        etEventNote = (EditText) findViewById(R.id.et_event_note);
        etEventDate = (EditText) findViewById(R.id.et_event_date);

        etEventName.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateEventName();
            }
        });


        EventDbHelper dbHelper = new EventDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.newevent_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
//        MenuItem tick = (MenuItem) findViewById(R.id.action_newevent);
//        tick.setEnabled(false);

        if (id == R.id.action_newevent) {
            long res = addNewEvent();
            if(res > -1){
                this.finish();
            }else if (res == -2) {

                Toast toast = Toast.makeText(mContext, "Please enter an Event name", Toast.LENGTH_SHORT);
                toast.show();

            }

        }

        return super.onOptionsItemSelected(item);

    }

    private boolean validateEventName(){
        TextInputLayout tilEventName = (TextInputLayout) findViewById(R.id.til_eventname);
        if(etEventName.getText().toString().trim().isEmpty()){
            tilEventName.setError(getString(R.string.err_msg_eventname));
            return false;
        } else {
            tilEventName.setError(null);
            return true;
        }

    }

    private long addNewEvent(){
        String name = etEventName.getText().toString();
        if(name.length() == 0){
            return -2;   //need event name
        }
        String place = etEventPlace.getText().toString();
        String date = etEventDate.getText().toString();
        String note = etEventNote.getText().toString();


        ContentValues cv = new ContentValues();
        cv.put(EventContract.EventEntry.COLUMN_EVENT_NAME,name);

        if(place.length()!=0){
            cv.put(EventContract.EventEntry.COLUMN_EVENT_PLACE,place);
        }
        if(date.length()!=0){
            cv.put(EventContract.EventEntry.COLUMN_EVENT_TIMESTAMP,date);
        }

        if(note.length()!=0){
            cv.put(EventContract.EventEntry.COLUMN_EVENT_NOTE,note);
        }

        return mDb.insert(EventContract.EventEntry.TABLE_NAME,null,cv);



    }



}
