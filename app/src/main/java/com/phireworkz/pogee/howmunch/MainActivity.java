package com.phireworkz.pogee.howmunch;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.phireworkz.pogee.howmunch.data.EventDbHelper;
import com.phireworkz.pogee.howmunch.data.EventContract;
import com.phireworkz.pogee.howmunch.data.TestUtil;


public class MainActivity extends AppCompatActivity implements EventListAdapter.EventAdapterOnClickHandler{

    private EventListAdapter mAdapter;

    private SQLiteDatabase mDb; // COMPLETED (1) Create a local field SQLiteDatabase called mDb

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.swapCursor(getAllEvents());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mEventListRecyclerView = (RecyclerView) findViewById(R.id.all_events_list_view);
        // Set layout for the RecyclerView, because it's a list we are using the linear layout
        mEventListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // COMPLETED (2) Create a WaitlistDbHelper instance, pass "this" to the constructor
        // Create a DB helper (this will create the DB if run for the first time)
        EventDbHelper dbHelper = new EventDbHelper(this);

        // COMPLETED (3) Get a writable database reference using getWritableDatabase and store it in mDb
        // Keep a reference to the mDb until paused or killed. Get a writable database
        // because you will be adding events
        mDb = dbHelper.getWritableDatabase();
        // COMPLETED (4) call insertFakeData in TestUtil and pass the database reference mDb
        //Fill the database with fake data
        //TestUtil.insertFakeData(mDb);

        // COMPLETED (7) Run the getAllGuests function and store the result in a Cursor variable
        Cursor cursor = getAllEvents();

        // COMPLETED (12) Pass the resulting cursor count to the adapter
        // Create an adapter for that cursor to display the data
        mAdapter = new EventListAdapter(this, cursor,this);



        // Link the adapter to the RecyclerView
        mEventListRecyclerView.setAdapter(mAdapter);




        // toolbar stuff
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_new_event);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),AddEventActivity.class));
                //mAdapter.swapCursor(getAllGuests());
            }
        });
    }

    // COMPLETED (5) Create a private method called getAllEventss that returns a cursor

    private Cursor getAllEvents() {
        // COMPLETED (6) Inside, call query on mDb passing in the table name and projection String [] order by COLUMN_TIMESTAMP
        return mDb.query(
                EventContract.EventEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                EventContract.EventEntry.COLUMN_EVENT_TIMESTAMP
        );
    }

    public void onClick(int id) {
        Intent intentToStartViewEvent = new Intent(this, ViewEventActivity.class);
        intentToStartViewEvent.putExtra("selectedID", id);

        startActivity(intentToStartViewEvent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
