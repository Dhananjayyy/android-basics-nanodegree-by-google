package com.dhananjay.my.Bookshelf.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class Contract {

    public static final String SOURCE = "com.dhananjay.myBookshelf";
    private static final Uri BASE_URI = Uri.parse("content://" + SOURCE);
    public static final String PATH = "library";

    /* Not instantiable */
    private Contract() {
    }

    public static abstract class Bookshelf implements BaseColumns {

        /* Not instantiable */
        private Bookshelf() {
        }

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + SOURCE + "/" + PATH;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + SOURCE+ "/" + PATH;

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI, PATH);

        public static final String TABLE_NAME = "library";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "Name";
        public static final String COLUMN_PRICE = "Price";
        public static final String COLUMN_QUANTITY = "Quantity";
        public static final String COLUMN_SOURCE_NAME = "Source";
        public static final String COLUMN_SOURCE_PHONE_NUMBER = "Phone";
    }
}
