package com.phireworkz.pogee.howmunch;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phireworkz.pogee.howmunch.data.EventContract;
import com.phireworkz.pogee.howmunch.data.EventDbHelper;

/**
 * Created by Pogee on 8/01/2017.
 */

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> {

    private Context mContext;
    //Add a new local variable mCount to store the count of items to be displayed in the recycler view
    private Cursor mCursor;

    private final EventAdapterOnClickHandler mClickHandler;



    public interface EventAdapterOnClickHandler {
        void onClick(int id);
    }

    /**
     * Constructor using the context and the db cursor
     *
     * @param context the calling context/activity
     */

    public EventListAdapter(Context context, Cursor cursor, EventAdapterOnClickHandler clickHandler) {
        this.mContext = context;
        this.mCursor = cursor;
        mClickHandler = clickHandler;

    }


    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.event_list_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {

        // Move the mCursor to the position of the item to be displayed
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null
        //  Call getString on the cursor to get details info form db
        String name = mCursor.getString(mCursor.getColumnIndex(EventContract.EventEntry.COLUMN_EVENT_NAME));
        String place = mCursor.getString(mCursor.getColumnIndex(EventContract.EventEntry.COLUMN_EVENT_PLACE));
        String note = mCursor.getString(mCursor.getColumnIndex(EventContract.EventEntry.COLUMN_EVENT_NOTE));
        // Set the holder's textviews and disply info
        holder.tvEventName.setText(name);
        holder.tvEventPlace.setText(place);
        holder.tvEventNote.setText(note);
        //get id from cursor and set tag of itemview in teh holder to the id (so now stored in ui
        int id = mCursor.getInt(mCursor.getColumnIndex(EventContract.EventEntry._ID));
        holder.itemView.setTag(id);

    }

    public void swapCursor(Cursor newCursor) {
        // COMPLETED (16) Inside, check if the current cursor is not null, and close it if so
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        // COMPLETED (17) Update the local mCursor to be equal to  newCursor
        mCursor = newCursor;
        // COMPLETED (18) Check if the newCursor is not null, and call this.notifyDataSetChanged() if so
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }


    // TODO (11) Modify the getItemCount to return the mCount value rather than 0
    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }



        /**
         * Inner class to hold the views needed to display a single item in the recycler-view
         */
        class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            // Will display the guest name
            TextView tvEventName;
            // Will display the party size number
            TextView tvEventPlace;
            TextView tvEventNote;

            /**
             * Constructor for our ViewHolder. Within this constructor, we get a reference to our
             * TextViews
             *
             * @param itemView The View that you inflated in
             *                 {@link EventListAdapter#onCreateViewHolder(ViewGroup, int)}
             */
            public EventViewHolder(View itemView) {
                super(itemView);
                tvEventName = (TextView) itemView.findViewById(R.id.tv_event_name);
                tvEventPlace = (TextView) itemView.findViewById(R.id.tv_event_place);
                tvEventNote = (TextView) itemView.findViewById(R.id.tv_event_note);

                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int id = (int) v.getTag();   //gettign it from the tag id we added
                //because    int ind = getAdapterPosition();   will not necesarily get teh ID to search from database
                mClickHandler.onClick(id);

            }

        }
    }
