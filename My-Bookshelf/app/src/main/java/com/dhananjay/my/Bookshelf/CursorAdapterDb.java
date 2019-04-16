package com.dhananjay.my.Bookshelf;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dhananjay.my.Bookshelf.data.Contract;
import com.dhananjay.myBookshelf.R;


class CursorAdapterDb extends android.widget.CursorAdapter {

    public CursorAdapterDb(Context context, Cursor c) {
        super(context, c, 0);
    }
   @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }
  @SuppressLint("SetTextI18n")
  @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        TextView productNameTextView = view.findViewById(R.id.name);
        TextView productPriceTextView = view.findViewById(R.id.price);
        TextView productQuantityTextView = view.findViewById(R.id.quantity);

        int productNameColumnIndex = cursor.getColumnIndex(Contract.Bookshelf.COLUMN_NAME);
        int productPriceColumnIndex = cursor.getColumnIndex(Contract.Bookshelf.COLUMN_PRICE);
        int productQuantityColumnIndex = cursor.getColumnIndex(Contract.Bookshelf.COLUMN_QUANTITY);

         String productName = cursor.getString(productNameColumnIndex);
        int productPrice = cursor.getInt(productPriceColumnIndex);
        int productQuantity = cursor.getInt(productQuantityColumnIndex);

        productNameTextView.setText(productName);
        productPriceTextView.setText("Price" + ":"+String.valueOf(productPrice));
        productQuantityTextView.setText("Quantity"+":"+String.valueOf(productQuantity));

        int productIdColumIndex = cursor.getColumnIndex(Contract.Bookshelf._ID);

        final long productIdVal = Integer.parseInt(cursor.getString(productIdColumIndex));
        final int currentProductQuantityVal = cursor.getInt(productQuantityColumnIndex);

         Button saleButton = view.findViewById(R.id.button_sale);
        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri currentUri = ContentUris.withAppendedId(Contract.Bookshelf.CONTENT_URI, productIdVal);

                String updatedQuantity = String.valueOf(currentProductQuantityVal - 1);

                if(Integer.parseInt(updatedQuantity)>=0){
                    ContentValues values = new ContentValues();
                    values.put(Contract.Bookshelf.COLUMN_QUANTITY,updatedQuantity);
                    context.getContentResolver().update(currentUri,values,null,null);
                }
            }
        });

    }
}
