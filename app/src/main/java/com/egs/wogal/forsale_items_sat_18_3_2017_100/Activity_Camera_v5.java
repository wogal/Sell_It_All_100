package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Activity_Camera_v5 extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "Cam Test v5";
    private static final int ACTIVITY_START_CAMERA_APP = 0;
    private ImageView mPhotoCaptureImageView;
    private String mImageFileLocation = null;
    private TextView mTxtVheaderText;
    private Button mButtTakePici;
    private Bitmap image;
    private Bitmap bitmap;
    private int image_Hieght = 0;
    private int image_Widght = 0;
    private boolean __test = false;
    private Button mBgetPici;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout_v5 );
        this.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
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

        mPhotoCaptureImageView.setImageResource( R.drawable.diana_100 );
        image_Hieght = mPhotoCaptureImageView.getMeasuredHeight();
        image_Widght = mPhotoCaptureImageView.getMeasuredWidth();

        image_Hieght = mPhotoCaptureImageView.getHeight();
        image_Widght = mPhotoCaptureImageView.getWidth();

        __test = true;

    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus) {
        super.onWindowFocusChanged( hasFocus );
        image_Hieght = mPhotoCaptureImageView.getHeight();
        image_Widght = mPhotoCaptureImageView.getWidth();

    }


    private void takePhoto (View v) {
        Intent callCameraApplicationIntent = new Intent();
        callCameraApplicationIntent.setAction( MediaStore.ACTION_IMAGE_CAPTURE );
        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        callCameraApplicationIntent.putExtra( MediaStore.EXTRA_OUTPUT, Uri.fromFile( photoFile ) );
        startActivityForResult( callCameraApplicationIntent, ACTIVITY_START_CAMERA_APP );
    }


    private File createImageFile () throws IOException {
        String imageFileName = "Wogals_IMAGE_0";
        File strorageDirectory = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES );
        //    File Tmp_image = File.createTempFile(imageFileName, ".jpg", strorageDirectory);
        String AbsFilePath = strorageDirectory + "/" + imageFileName + ".jpg";
        File image = new File( AbsFilePath );
        mImageFileLocation = image.getAbsolutePath();
        return (image);
    }


    private String GetTempImageFileAbsPath(){
        String AbsFilePath = "";
        String imageFileName = "Wogals_IMAGE_0";
        File strorageDirectory = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES );
        //    File Tmp_image = File.createTempFile(imageFileName, ".jpg", strorageDirectory);
         AbsFilePath = strorageDirectory + "/" + imageFileName + ".jpg";
        mImageFileLocation = AbsFilePath;
        return AbsFilePath;
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        Toast.makeText( this, "Wogal Heck ", Toast.LENGTH_LONG ).show();
        if (requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK) {
            //   Bitmap photoCapturedBitmap = BitmapFactory.decodeFile( mImageFileLocation );
            //  mPhotoCaptureImageView.setImageBitmap( photoCapturedBitmap );
            //  bitmap = photoCapturedBitmap;
            rotateImage( setReducedImageSize() );
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

   /* @Override
    protected void onRestoreInstanceState (Bundle savedInstanceState) {
        //     if (mImageFileLocation == null)
        //      return;
     //   image = savedInstanceState.getParcelable( "BitmapImage" );
     //   mPhotoCaptureImageView.setImageBitmap( image );
        //      mPhotoCaptureImageView.setText(savedInstanceState.getString("path_to_picture"));
    //    mTxtVheaderText.setText( savedInstanceState.getString( "str" ) );
    }

    @Override
    public void onSaveInstanceState (Bundle savedInstanceState) {
        //   if (mImageFileLocation == null)
        //      return;
        super.onSaveInstanceState( savedInstanceState );
        savedInstanceState.putParcelable( "BitmapImage", bitmap );
        savedInstanceState.putString( "path_to_picture", mImageFileLocation );
        savedInstanceState.putString( "str", (String) mTxtVheaderText.getText() );
    }*/

    //endregion


    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.But_take_pici_v5: {
                mTxtVheaderText.setText( "Hi wogal -- " );
                takePhoto( v );
            }
            case R.id.But_get_pici_v5: {
                getfromfile();
            }
        }
    }

    private void getfromfile () {


        if(1 == 2) {
            File imgFile = new File( GetTempImageFileAbsPath() );
            if (imgFile.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile( imgFile.getAbsolutePath() );
                //Drawable d = new BitmapDrawable(getResources(), myBitmap);
                mPhotoCaptureImageView.setImageBitmap( bitmap );
            }
        }else {
            Bitmap bitmap = null;
            File f = new File( GetTempImageFileAbsPath() );
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            try {
                bitmap = BitmapFactory.decodeStream( new FileInputStream( f ), null, options );
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            mPhotoCaptureImageView.setImageBitmap( bitmap );
        }
    }


    private Bitmap setReducedImageSize () {
        image_Widght = 700;
        image_Hieght = 700;

        if (1 == 3) {
            int targetImageViewWidth = image_Widght;
            int targetImageViewHeight = image_Hieght;

            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile( mImageFileLocation, bmOptions );
            int CameraImageWidth = bmOptions.outWidth;
            int CameraImageHeight = bmOptions.outHeight;
            int scaleFactour = Math.min( CameraImageWidth / targetImageViewWidth, CameraImageHeight / targetImageViewHeight );
            bmOptions.inSampleSize = scaleFactour;
            bmOptions.inJustDecodeBounds = false;
            return null;
        }

        Bitmap photoReducedSizeBitmap = BitmapFactory.decodeFile( mImageFileLocation );
        //    mPhotoCaptureImageView.setImageBitmap( photoReducedSizeBitmap );
        //    mPhotoCaptureImageView.setImageBitmap( photoReducedSizeBitmap );

        return photoReducedSizeBitmap;
    }

    private Bitmap rotateImage (Bitmap bitmap) {
        ExifInterface exifInterface = null;
        try {
            exifInterface = new ExifInterface( mImageFileLocation );
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
        mPhotoCaptureImageView.setImageBitmap( rotatedBitmap );
        return bitmap;
    }


}






