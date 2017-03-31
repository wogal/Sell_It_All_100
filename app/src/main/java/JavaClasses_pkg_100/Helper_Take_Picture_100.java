package JavaClasses_pkg_100;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by wogal on 3/30/2017.
 */

public class Helper_Take_Picture_100 {

    public static final String TAG = "Cam Intent ";
    private static final int ACTIVITY_START_CAMERA_APP = 0;


    /**
     * Modifies the standard behavior to allow results to be delivered to fragments.
     * This imposes a restriction that requestCode be <= 0xffff.
     */
    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        // If this was started from a Fragment we've already checked the upper 16 bits were not in
        // use, and then repurposed them for the Fragment's index.
        if (!mStartedActivityFromFragment) {
            if (requestCode != -1) {
                checkForValidRequestCode(requestCode);
            }
        }
        super.startActivityForResult(intent, requestCode);
    }



    private void takePhoto (View v) {
        Log.d( TAG, "takePhoto start" );
        Intent callCameraApplicationIntent = new Intent();
        callCameraApplicationIntent.setAction( MediaStore.ACTION_IMAGE_CAPTURE );
        File photoFile = null;
        try {

            photoFile = createImageFile();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        // puts bitmap into file and writes to storage ( temp file )
        callCameraApplicationIntent.putExtra( MediaStore.EXTRA_OUTPUT, Uri.fromFile( photoFile ) );
        startActivityForResult( callCameraApplicationIntent, ACTIVITY_START_CAMERA_APP );
        Log.d( TAG, "takePhoto end" );
    }

    private File createImageFile () throws IOException {
        Log.d( TAG, "createImageFile start " );
        String AbsFilePath = mImageFileLocation;
        File image = new File( AbsFilePath );
        Log.d( TAG, "createImageFile end " );
        return (image);
    }


    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        Toast.makeText( this, "Wogal Heck ", Toast.LENGTH_LONG ).show();
        Log.d( TAG, "onActivityResult start " );
        if (requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK && 1 == 1) {
            Bitmap bm_in;
            Bitmap bm_out;
            bm_in = BitmapFactory.decodeFile( mImageFileLocation );
            //    bm_out = rotateImage( bm_in );
            bm_in = ImageClassHelper.getResizedBitmap( bm_in, 700, 700 );
            String path;
            bm_in = rotateImage( bm_in, mImageFileLocation );
            path = Storage_Helper_Class.saveImage( bm_in, "wogal", "jpg" );
            Log.d( TAG, "onActivityResult end ( save pressed " );
            mPhotoCaptureImageView.setImageBitmap( bm_in );
        }
    }





}
