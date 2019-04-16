package com.dhananjay.my.Bookshelf;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dhananjay.my.Bookshelf.data.Contract;
import com.dhananjay.my.Bookshelf.data.DbHelper;
import com.dhananjay.myBookshelf.R;

public class ViewActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private EditText Name;
    private EditText Price;
    private EditText Quantity;
    private EditText Source;
    private EditText Phone;


    private static final int LOADER = 1;
    private Uri libUri;


    private final int MIN_VALUE = 0;
    private final int MAX_VALUE = 9999;

    private boolean bookHasChanged = false;
    private String phone;

    private DbHelper dbHelper;


    private void callSupplier() {
        Intent supplierNumberIntent = new Intent(Intent.ACTION_DIAL);
        supplierNumberIntent.setData(Uri.parse("tel:" + phone));
        startActivity(supplierNumberIntent);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        Intent i = getIntent();
        libUri = i.getData();
        if (libUri == null) {
            setTitle(R.string.add_new_book);
            invalidateOptionsMenu();
        } else {
            setTitle(getString(R.string.edit_book));
            getLoaderManager().initLoader(LOADER, null, this);
        }

        Name = findViewById(R.id.product_name);
        Price = findViewById(R.id.product_price);
        Quantity = findViewById(R.id.product_quantity);
        Source = findViewById(R.id.supplier_name);
        Phone = findViewById(R.id.supplier_contact);
        Button minusButton = findViewById(R.id.subtract_quantity);
        Button plusButton = findViewById(R.id.add_quantity);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myQuantity = Quantity.getText().toString();
                int current;
                if (myQuantity.length() == 0) {
                    current = 0;
                    Quantity.setText(String.valueOf(current));
                } else {
                    current = Integer.parseInt(myQuantity) - 1;
                    if (current >= MIN_VALUE) {
                        Quantity.setText(String.valueOf(current));
                    }
                }

            }
        });
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myQuantity = Quantity.getText().toString();
                int current;
                if (myQuantity.length() == 0) {
                    current = 1;
                    Quantity.setText(String.valueOf(current));
                } else {
                    current = Integer.parseInt(myQuantity) + 1;
                    if (current <= MAX_VALUE) {
                        Quantity.setText(String.valueOf(current));
                    }
                }

            }
        });

        dbHelper = new DbHelper(this);

        Name.setOnTouchListener(mTouchListener);
        Price.setOnTouchListener(mTouchListener);
        Quantity.setOnTouchListener(mTouchListener);
        minusButton.setOnTouchListener(mTouchListener);
        plusButton.setOnTouchListener(mTouchListener);
        Name.setOnTouchListener(mTouchListener);
        Phone.setOnTouchListener(mTouchListener);

    }

    private final View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            bookHasChanged = true;
            return false;
        }

    };



    private void saveBook() {
        String bookName = Name.getText().toString().trim();
        String bookPrice = Price.getText().toString().trim();
        String bookQuantity = Quantity.getText().toString().trim();
        String bookSource = Source.getText().toString().trim();
        String bookPhone = Phone.getText().toString().trim();


        if (TextUtils.isEmpty(bookName)) {
            Name.setError(getString(R.string.required));
            return;
        }

        if (TextUtils.isEmpty(bookPrice)) {
            Price.setError(getString(R.string.required));
            return;
        }
        if (TextUtils.isEmpty(bookQuantity)) {
            Quantity.setError(getString(R.string.required));
            return;
        }

        if (TextUtils.isEmpty(bookSource)) {
            Source.setError(getString(R.string.required));
            return;
        }
        if (TextUtils.isEmpty(bookPhone)) {
            Phone.setError(getString(R.string.required));
            return;
        }

        int mPrice = Integer.parseInt(bookPrice);
        int mQuantity = Integer.parseInt(bookQuantity);

        if (mPrice < 0) {
            Price.setError(getString(R.string.price_cannot_be_negative));
            return;
        }
        if (mQuantity < 0) {
            Quantity.setError(getString(R.string.quantity_cannot_be_negative));
            return;
        }
        ContentValues values = new ContentValues();
        values.put(Contract.Bookshelf.COLUMN_NAME, bookName);
        values.put(Contract.Bookshelf.COLUMN_PRICE, mPrice);
        values.put(Contract.Bookshelf.COLUMN_QUANTITY, mQuantity);
        values.put(Contract.Bookshelf.COLUMN_SOURCE_NAME, bookSource);
        values.put(Contract.Bookshelf.COLUMN_SOURCE_PHONE_NUMBER, bookPhone);

        if (libUri == null) {
            Uri newUri = getContentResolver().insert(Contract.Bookshelf.CONTENT_URI, values);

            if (newUri == null) {
                Toast.makeText(this, getString(R.string.editor_insert_book_failed), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.editor_insert_book_successful), Toast.LENGTH_SHORT).show();
            }
        } else {
            int rowAffected = getContentResolver().update(libUri, values, null, null);
            if (rowAffected == 0) {
                Toast.makeText(this, getString(R.string.editor_update_book_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.editor_update_book_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    private void deleteBook() {
        if (libUri != null) {
            int rowsDeleted = 0;

            rowsDeleted = getContentResolver().delete(
                    libUri,
                    null,
                    null
            );
            if (rowsDeleted == 0) {
                Toast.makeText(this, getString(R.string.error_deleting_book),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.book_deleted),
                        Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }


    @Override
    public void onBackPressed() {
        if (!bookHasChanged) {
            super.onBackPressed();
            return;
        }
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                };

        showUnsavedChangesDialog(discardButtonClickListener);
    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.discard_changes_and_quit_editing));
        builder.setPositiveButton(getString(R.string.discard), discardButtonClickListener);
        builder.setNegativeButton(getString(R.string.keep_editing), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.delete_this_book));
        builder.setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteBook();
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        if (libUri == null) {
            MenuItem menuItem;
            menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
            menuItem = menu.findItem(R.id.action_contact_supplier);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveBook();
                return true;
            case R.id.action_contact_supplier:
                callSupplier();
                break;
            case R.id.action_delete:
                showDeleteConfirmationDialog();
                break;
            case android.R.id.home:
                if (!bookHasChanged) {
                    NavUtils.navigateUpFromSameTask(ViewActivity.this);
                    return true;
                }

                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                NavUtils.navigateUpFromSameTask(ViewActivity.this);
                            }
                        };

                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
                libUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor.moveToFirst()) {

            int productNameColumnIndex = cursor.getColumnIndex(Contract.Bookshelf.COLUMN_NAME);
            int productPriceColumnIndex = cursor.getColumnIndex(Contract.Bookshelf.COLUMN_PRICE);
            int productQuantityColumnIndex = cursor.getColumnIndex(Contract.Bookshelf.COLUMN_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(Contract.Bookshelf.COLUMN_SOURCE_NAME);
            int supplierContactColumnIndex = cursor.getColumnIndex(Contract.Bookshelf.COLUMN_SOURCE_PHONE_NUMBER);

            String productName = cursor.getString(productNameColumnIndex);
            int productPrice = cursor.getInt(productPriceColumnIndex);
            int productQuantity = cursor.getInt(productQuantityColumnIndex);
            String supplierName = cursor.getString(supplierNameColumnIndex);
            phone = cursor.getString(supplierContactColumnIndex);

            Name.setText(productName);
            Price.setText(String.valueOf(productPrice));
            Quantity.setText(String.valueOf(productQuantity));
            Source.setText(String.valueOf(supplierName));
            Phone.setText(String.valueOf(phone));


        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Name.setText("");
        Price.setText("");
        Quantity.setText("");
        Source.setText("");
        Phone.setText("");
    }
}