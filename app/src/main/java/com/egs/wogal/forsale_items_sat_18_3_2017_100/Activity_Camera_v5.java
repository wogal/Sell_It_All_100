package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

public class Activity_Camera_v5 extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "Cam Test v5";
    private static final int ACTIVITY_START_CAMERA_APP = 0;
    private ImageView mPhotoCaptureImageView;
    private String mImageFileLocation = null;
    private TextView mTxtVheaderText;
    private Button mButtTakePici;
    private boolean __test = false;
    private Button mBgetPici;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout_v5 );
        this.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
        mImageFileLocation = GetBaseStorageFilePathAndAddFile( "Wogals_Temp_Pic_100", "jpg" );
        mTxtVheaderText = (TextView) findViewById( R.id.txt_v_take_pici_v5 );
        mButtTakePici = (Button) findViewById( R.id.But_take_pici_v5 );
        mButtTakePici.setOnClickListener( this );

        mBgetPici = (Button) findViewById( R.id.But_get_pici_v5 );
        mBgetPici.setOnClickListener( this );

        try {
            Thread.sleep( 50 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mPhotoCaptureImageView = (ImageView) findViewById( R.id.capturePhotoImageView );

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

    //endregion   Activity frf
    //region Description -- Activity States
    //region Description
    @Override
    protected void onStart () {
        Log.d( TAG, "osStart" );
        super.onStart();
    }

    @Override
    protected void onRestart () {
        Log.d( TAG, "  Wogal onRestart " );
        super.onRestart();
    }

    @Override
    protected void onResume () {
        String path;
        path = mImageFileLocation;
        File file = new File( path );
        if (file.exists()) {
            Bitmap bm = BitmapFactory.decodeFile( path );
            if (null != bm) {
                bm = ImageClassHelper.getResizedBitmap( bm, 700, 700 );
                bm = ImageClassHelper.rotateImage( bm, mImageFileLocation );
                mPhotoCaptureImageView.setImageBitmap( bm );
            }
        }
        Log.d( TAG, "  Wogal onResume " );
        super.onResume();
    }

    @Override
    protected void onPause () {
        Log.d( TAG, "  Wogal onPause " );
        super.onPause();
    }

    @Override
    protected void onStop () {
        Log.d( TAG, "  Wogal onStop " );
        super.onStop();
    }

    @Override
    protected void onDestroy () {
        Log.d( TAG, "  Wogal onDestroy " );
        super.onDestroy();
    }


    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.But_take_pici_v5: {
                mTxtVheaderText.setText( "Hi wogal -- " );
                takePhoto( v );
                break;
            }
            case R.id.But_get_pici_v5: {
                break;
            }
        }
    }

}






