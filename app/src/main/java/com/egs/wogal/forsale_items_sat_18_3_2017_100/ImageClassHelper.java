package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.File;
import java.io.IOException;

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

    public static Bitmap GetBitmapfrom_AbsFilePath (String _absFilePath) {
        Bitmap bm = null;
        File imgFile = new File( _absFilePath );
        if (imgFile.exists()) {
            bm = BitmapFactory.decodeFile( _absFilePath );
        } else {
            bm = null;
            imgFile = null;
        }
        return bm;
    }


    public static Bitmap getResizedBitmap (Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale( scaleWidth, scaleHeight );

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false );
        bm.recycle();
        return resizedBitmap;
    }


    public static Bitmap RotateImage (Bitmap bitmap, String _mImageFileLocation) {
        ExifInterface exifInterface = null;
        try {
            exifInterface = new ExifInterface( _mImageFileLocation );
        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = exifInterface.getAttributeInt( ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED );
        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90: {
                matrix.setRotate( 90 );
                break;
            }
            case ExifInterface.ORIENTATION_ROTATE_180: {
                matrix.setRotate( 180 );
                break;
            }
        }
        Bitmap rotatedBitmap = Bitmap.createBitmap( bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true );
        return rotatedBitmap;
    }


}
