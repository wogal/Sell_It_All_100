package JavaClasses_pkg_100;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import static JavaClasses_pkg_100.ImageClassHelper.rotateImage;
import static JavaClasses_pkg_100.Storage_Helper_Class.GetBaseStorageFilePathAndAddFile;


/**
 * Created by wogal on 3/30/2017.
 */

public class Helper_Take_Picture_100 extends Activity implements View.OnClickListener {


    public static final String TAG = " Helper_photo";
    private static final int ACTIVITY_START_CAMERA_APP = 0;
    private ImageView mPhotoCaptureImageView;
    private String mImageFileLocation = null;
    private TextView mTxtVheaderText;
    private Button mButtTakePici;
    private boolean __test = false;
    private Button mBgetPici;
    private Activity mContext;

    public Helper_Take_Picture_100 (Activity _Activity) {
        mContext = _Activity;
        mImageFileLocation = GetBaseStorageFilePathAndAddFile( "Wogals_Temp_Pic_100", "jpg" );

    }

    /**
     * Modifies the standard behavior to allow results to be delivered to fragments.
     * This imposes a restriction that requestCode be <= 0xffff.
     */


    public void takePhoto () {
        Log.d( TAG, "takePhoto start" );
        Intent callCameraApplicationIntent = new Intent(mContext,Helper_Take_Picture_100.class);
        callCameraApplicationIntent.setAction( MediaStore.ACTION_IMAGE_CAPTURE );
        File photoFile = null;
        try {

            photoFile = createImageFile();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }

        // puts bitmap into file and writes to storage ( temp file )
       callCameraApplicationIntent.putExtra( MediaStore.EXTRA_OUTPUT, Uri.fromFile( photoFile ) );
        mContext.startActivityForResult( callCameraApplicationIntent, ACTIVITY_START_CAMERA_APP );


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


    @Override
    public void onClick (View v) {

    }
}
