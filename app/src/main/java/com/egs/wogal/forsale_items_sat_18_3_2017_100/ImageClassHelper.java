package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by wogal on 3/21/2017.
 */

public class ImageClassHelper {

    public static Bitmap setReducedImageSize (int _width, int _height, String _absFilePath) {
        int targetImageViewWidth = _width;
        int targetImageViewHeight = _height;

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile( _absFilePath, bmOptions );
        int cameraImageWidth = bmOptions.outWidth;
        int cameraImageHeight = bmOptions.outHeight;

        int scaleFactor = Math.min( cameraImageWidth / targetImageViewWidth, cameraImageHeight / targetImageViewHeight );
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inJustDecodeBounds = false;

        Bitmap photoReducedSizeBitmp = BitmapFactory.decodeFile( _absFilePath, bmOptions );
        return photoReducedSizeBitmp;
    }

}
