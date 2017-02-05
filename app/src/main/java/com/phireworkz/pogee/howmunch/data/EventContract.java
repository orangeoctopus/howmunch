package com.phireworkz.pogee.howmunch.data;

import android.provider.BaseColumns;

/**
 * Created by Pogee on 14/12/2016.
 */

public class EventContract {

    public static final class EventEntry implements BaseColumns {

        public static final String TABLE_NAME = "event";

        public static final String COLUMN_EVENT_NAME = "ename";
        public static final String COLUMN_EVENT_PLACE = "place";
        public static final String COLUMN_EVENT_NOTE = "note";
        public static final String COLUMN_EVENT_PHOTO = "photopath";
        public static final String COLUMN_EVENT_TIMESTAMP = "timestamp";


    }

    public static final class ItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "item";
        public static final String COLUMN_ITEM_NAME = "iname";
        public static final String COLUMN_ITEM_PRICE = "price";


    }


    public static final class PersonEntry implements BaseColumns {
        public static final String TABLE_NAME = "person";
        public static final String COLUMN_PERSON_NAME = "pname";
        public static final String COLUMN_PERSON_PAIDSTATUS = "paid_status";


    }

    public static final class PaymentEntry implements BaseColumns {
        public static final String TABLE_NAME = "person_item";

        public static final String COLUMN_PERSON_KEY = "person_id";
        public static final  String COLUMN_ITEM_KEY = "item_id";
        public static final String COLUMN_PAYMENT_AMOUNT = "split_amount";
        // Column with the foreign key
        public static final String COLUMN_EVENT_KEY= "event_id";


    }
}
