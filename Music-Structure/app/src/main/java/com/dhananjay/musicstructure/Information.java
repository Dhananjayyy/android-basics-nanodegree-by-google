package com.dhananjay.musicstructure;

class Information {

    private String mSongName;

    private String mArtistName;

    private int mImageResourceId;

    Information(String vSong, String vArtist, int imageResourceId) {
        mSongName = vSong;
        mArtistName = vArtist;
        mImageResourceId = imageResourceId;
    }

    String getSongName() {
        return mSongName;
    }

    String getArtistName() {
        return mArtistName;
    }

    int getImageResourceId() {
        return mImageResourceId;
    }


}

