package com.dhananjay.my.Bookshelf.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DBV = 1;
    private static final String DATABASE_NAME = "library.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DBV);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_TABLE = "CREATE TABLE " + Contract.Bookshelf.TABLE_NAME + " ("
                + Contract.Bookshelf._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.Bookshelf.COLUMN_NAME + " TEXT NOT NULL, "
                + Contract.Bookshelf.COLUMN_PRICE + " INTEGER NOT NULL, "
                + Contract.Bookshelf.COLUMN_QUANTITY + " INTEGER NOT NULL, "
                + Contract.Bookshelf.COLUMN_SOURCE_NAME + " TEXT NOT NULL, "
                + Contract.Bookshelf.COLUMN_SOURCE_PHONE_NUMBER + " TEXT NOT NULL );";
        sqLiteDatabase.execSQL(SQL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
