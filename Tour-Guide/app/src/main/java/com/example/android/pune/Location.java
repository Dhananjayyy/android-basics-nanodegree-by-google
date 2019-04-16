package com.example.android.pune;

@SuppressWarnings("deprecation")
class Location {


    private final String mAddress;
    private final String mTitle;
    private final String mDescription;


    private int mImageResourceId = IMAGE;
    private static final int IMAGE = -1;

    Location(String Title, String Description, String Address, int ImageResourceId) {
        mTitle = Title;
        mDescription = Description;
        mAddress = Address;
        mImageResourceId = ImageResourceId;
    }


    public String getTitle() {
        return mTitle;
    }


    public String getDescription() {
        return mDescription;
    }

    String getAddress() {
        return mAddress;
    }


    int getImageResourceId() {
        return mImageResourceId;
    }


}
