package FaceBook_Java_Helpers;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;

import com.egs.wogal.forsale_items_sat_18_3_2017_100.R;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import java.io.ByteArrayOutputStream;

/**
 * Created by wogal on 4/13/2017.
 */

public class Helper_Facebook_Posts {

    private static String mStr;
    //  private String mStr = "";


    public static void wogal () {

    }

    public static byte[] getImageAsData (Activity _acActivity, @DrawableRes int _id) {

        Drawable d = _acActivity.getDrawable( _id );
        Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress( Bitmap.CompressFormat.JPEG, 100, stream );
        byte[] bitmapdata = stream.toByteArray();
        return bitmapdata;
    }

    public static void postImage_toGroup (Activity _acActivity, String _destination_id, String _postMessage, PostImageType _poPostImageType, boolean _publishStatus) {

        Bundle params = new Bundle();
        params.clear();
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();

        switch (_poPostImageType) {
            case NONE: {
                break;
            }
            case POST_BITMAP_PHOTO: {
                params.putByteArray( "picture", Helper_Facebook_Posts.getImageAsData( _acActivity, R.drawable.star ) );
                break;
            }
            case POST_IMAGE_PHOTO: {
                break;
            }
        }

        params.putString( "message", _postMessage );

        new GraphRequest(
                currentAccessToken,
                "/" + _destination_id + "/photos",
                params,
                HttpMethod.POST,
                new GraphRequest.Callback() {
                    public void onCompleted (GraphResponse response) {
                        mStr = response.toString();
                        mStr += "";
                    }
                }
        ).executeAsync();
    }


    //region Overrides
    @Override
    public String toString () {
        return super.toString();
    }

    public enum PostImageType {
        NONE,
        POST_IMAGE_PHOTO,
        POST_BITMAP_PHOTO,
    }
    //endregion


}
