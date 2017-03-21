package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class Activity_Camera_v5 extends AppCompatActivity implements View.OnClickListener {
    private static final int ACTIVITY_START_CAMERA_APP = 0;
    private ImageView mPhotoCaptureImageView;
    private String mImageFileLocation = "";
    private TextView mTxtVheaderText;
    private Button mButtTakePici;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout_v5 );
        this.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mTxtVheaderText = (TextView) findViewById( R.id.txt_v_take_pici_v5 );
        mButtTakePici = (Button) findViewById( R.id.But_take_pici_v5 );
        mButtTakePici.setOnClickListener( this );
        mPhotoCaptureImageView = (ImageView) findViewById( R.id.ImgView_v5 );

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

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        Toast.makeText( this, "Wogal Heck ", Toast.LENGTH_LONG ).show();
        if (requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK) {
            Bitmap photoCapturedBitmap = BitmapFactory.decodeFile( mImageFileLocation );
            mPhotoCaptureImageView.setImageBitmap( photoCapturedBitmap );
        }
    }


    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.But_take_pici_v5: {
                takePhoto( v );
            }
        }

    }
}
