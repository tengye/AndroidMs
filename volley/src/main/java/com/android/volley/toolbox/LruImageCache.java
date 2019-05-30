package com.android.volley.toolbox;

import com.android.volley.RequestQueue;

// TODO 自己实现一套图片缓存机制
public class LruImageCache extends ImageLoader {
    /**
     * Constructs a new ImageLoader.
     *
     * @param queue      The RequestQueue to use for making image requests.
     * @param imageCache The cache to use as an L1 cache.
     */
    public LruImageCache(RequestQueue queue, ImageCache imageCache) {
        super(queue, imageCache);


    }



}
