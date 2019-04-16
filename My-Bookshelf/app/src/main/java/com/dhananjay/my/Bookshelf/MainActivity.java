package com.dhananjay.my.Bookshelf;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dhananjay.my.Bookshelf.data.Contract;
import com.dhananjay.myBookshelf.R;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int INVENTORY_LOADER = 0;

    RelativeLayout EmptyView;
    private CursorAdapterDb cAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                startActivity(intent);
            }
        });

        ListView booksListView = findViewById(R.id.list_view_books);

        EmptyView = findViewById(R.id.empty_view);
        booksListView.setEmptyView(EmptyView);

        cAdapter = new CursorAdapterDb(this, null);
        booksListView.setAdapter(cAdapter);

        booksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, ViewActivity.class);
                Uri currentBookUri = ContentUris.withAppendedId(Contract.Bookshelf.CONTENT_URI, id);
                i.setData(currentBookUri);
                startActivity(i);
            }
        });


        getLoaderManager().initLoader(INVENTORY_LOADER, null, this);
    }

    private void deleteAllBooks() {
        int rowsDeleted;

        rowsDeleted = getContentResolver().delete(
                Contract.Bookshelf.CONTENT_URI,
                null,
                null
        );
        if (rowsDeleted == 0) {
            Toast.makeText(this, R.string.error_while_deleting_books,
                    Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(this, R.string.all_books_deleted,
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete_all_entries:
                showDeleteConfirmationDialog();
                return true;
            default:
                return false;
        }
    }

    private void showDeleteConfirmationDialog() {

        if (!(EmptyView.getVisibility() == View.VISIBLE)) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage(R.string.delete_all_books);
            alert.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    deleteAllBooks();
                }
            });
            alert.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                }
            });
            AlertDialog alertDialog = alert.create();
            alertDialog.show();
        }
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                Contract.Bookshelf._ID,
                Contract.Bookshelf.COLUMN_NAME,
                Contract.Bookshelf.COLUMN_PRICE,
                Contract.Bookshelf.COLUMN_QUANTITY,
                Contract.Bookshelf.COLUMN_SOURCE_NAME,
                Contract.Bookshelf.COLUMN_SOURCE_PHONE_NUMBER,
        };
        return new CursorLoader(this,
                Contract.Bookshelf.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        cAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cAdapter.swapCursor(null);
    }
}
