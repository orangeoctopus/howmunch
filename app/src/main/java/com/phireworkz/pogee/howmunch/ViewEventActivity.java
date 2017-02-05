package com.phireworkz.pogee.howmunch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

/**
 * Created by Pogee on 3/02/2017.
 */

public class ViewEventActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewevent);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.

            Intent intentThatStartedThisActivity = this.getIntent();
            int id = -1;

            if (intentThatStartedThisActivity != null) {
                if (intentThatStartedThisActivity.hasExtra("selectedID")) {
                    id = getIntent().getIntExtra("selectedID", -1);

                }

                Bundle arguments = new Bundle();

                arguments.putInt("selectedID", id);

                ViewEventFragment fragment = new ViewEventFragment();
                fragment.setArguments(arguments);

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.event_detail_container, fragment)
                        .commit();

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.viewevent_menu, menu);
        return true;
    }


}

