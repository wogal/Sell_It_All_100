package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import JavaClasses_pkg_100.ImageClassHelper;
import JavaClasses_pkg_100.Storage_Helper_Class;

import static JavaClasses_pkg_100.ImageClassHelper.rotateImage;
import static JavaClasses_pkg_100.Storage_Helper_Class.GetBaseStorageFilePathAndAddFile;

public class Activity_Camrea_100 extends AppCompatActivity {

    public static final String TAG = " Helper_photo";
    private static final int ACTIVITY_START_CAMERA_APP = 0;
    private ImageView mPhotoCaptureImageView;
    private String mImageFileLocation = null;
    private TextView mTxtVheaderText;
    private Button mButtTakePici;
    private boolean __test = false;
    private Button mBgetPici;

    public Activity_Camrea_100 () {

    }

    //   private Activity mContext;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );    mImageFileLocation = GetBaseStorageFilePathAndAddFile( "Wogals_Temp_Pic_100", "jpg" );
        setContentView( R.layout.activity__camrea_100 );
        //   mContext = _Activity;
        mImageFileLocation = GetBaseStorageFilePathAndAddFile( "Wogals_Temp_Pic_100", "jpg" );
    }

    public void takePhoto () {
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
