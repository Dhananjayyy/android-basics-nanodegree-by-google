package com.example.anrdoid.newsone;

@SuppressWarnings("deprecation")
class News {
    private final String mSectionName;
    private final String mWebTitle;
    private final String mDate;
    private final String mUrl;
    private final String mAuthor;

    News(String sectionName, String webTitle, String date, String url, String author) {
        mSectionName = sectionName;
        mWebTitle = webTitle;
        mDate = date;
        mUrl = url;
        mAuthor = author;
    }
    public String getName(){return mSectionName;}
    String getWebTitle() {
        return mWebTitle;
    }
    public String getDate() {
        return mDate;
    }
    String getUrl() {
        return mUrl;
    }
    public String getAuthor(){return mAuthor;}
}
