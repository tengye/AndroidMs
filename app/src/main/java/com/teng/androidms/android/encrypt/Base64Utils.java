package com.teng.androidms.android.encrypt;

import android.util.Base64;

/**
 * Created by teng on 2018/7/8.
 */
public class Base64Utils {
    // A-Z a-z 0-9 + /
    public static String encoderData(byte[] data) throws Exception {
        return Base64.encodeToString(data, 1);
    }

    public static byte[] decoderData(String str) throws Exception {
        return Base64.decode(str.getBytes("utf-8"), 1);
    }
}
