package uk.co.rossbeazley.avp.android.media;

public final class MediaItem {
    private final String programTitle;

    /**
     *
     * Image url
     * https://i.bbcredux.com/asset/media/f55b79c0-6d2d-4c6d-be25-a941e8febfb9/1-1404903165-b2e72fb9fa36c12444a4b03776d3e683/JPEG-1280x/image.jpg
     * https://i.bbcredux.com/asset/media/{uuid}/{key}/JPEG-1280x/image.jpg
     *
     * http://ichef.bbci.co.uk/images/raw/p020v023.png
     *
     * SEE
     * http://www.bignerdranch.com/blog/solving-the-android-image-loading-problem-volley-vs-picasso/
     *
     *
     *
     *
     * GLOBALS
     *
     * mQueue = Volley.newRequestQueue(getActivity());

     mImageLoader = new ImageLoader(mQueue, new ImageCache() {
    @Override
    public void putBitmap(String key, Bitmap value) { }

    @Override
    public Bitmap getBitmap(String key) {
    return null;
    }
    });


     in adapter

     NetworkImageView imageView = (NetworkImageView)convertView
     .findViewById(R.id.gallery_item_imageView);
     imageView.setDefaultImageResId(R.drawable.brian_up_close);
     imageView.setImageUrl(item.getUrl(), mImageLoader);



     *
     */

    public MediaItem(String programTitle) {

        this.programTitle = programTitle;
    }

    public String titleString() {
        return programTitle;
    }
}
