package FaceBook_Java_Helpers;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import java.io.ByteArrayOutputStream;

/**
 * Created by wogal on 4/13/2017.
 */

public class HlpFbook_Posts {

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

    public static void postOPbj_2_Grp (Activity _acActivity, String _destination_id, String _postMessage, PostImageType _ImgeTyp, boolean _pubStus, Object _imgObj) {

        Bundle params = new Bundle();
        params.clear();
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();

        switch (_ImgeTyp) {
            case NONE: {
                break;
            }
            case POST_BITMAP_PHOTO: {
                params.putByteArray( "picture", HlpFbook_Posts.getImageAsData( _acActivity, (Integer) _imgObj ) );
                break;
            }
            case POST_IMAGE_PHOTO: {
                params.putString( "url", (String) _imgObj );
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
