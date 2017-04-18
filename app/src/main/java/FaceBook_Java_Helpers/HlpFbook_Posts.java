package FaceBook_Java_Helpers;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import For_Sale_Item_Object_Pkg.SaleItemMakeup;

import static com.egs.wogal.forsale_items_sat_18_3_2017_100.Activity_FaceBook_v10.WogalstestGroup;

/**
 * Created by wogal on 4/13/2017.
 */

public class HlpFbook_Posts {

    public ArrayList<GraphResponse> mGraphFaceBookPartPost_Id;
    private String mStr;
    private JSONObject Json_objQ;
    //  private String mStr = "";
    private Graph_OnCompleted_CallBack_Interface mGraph_onCompleted_callBack_Listerner;


    // master wrapper for posting " multiple " images
    public void PostMultiplePicys (ArrayList<SaleItemMakeup> _items_2_Post, Activity _acActivity, String _destination_id, String _MainpostMessage) {
        int cnt;
        SaleItemMakeup mSsaleItemMakeup;
        cnt = _items_2_Post.size();
        Bitmap mBitmap;
        String mItemtxtHeader;
        int PostCount;
        int tst = 0;

        mGraphFaceBookPartPost_Id = new ArrayList<>();
        cnt = 1;


        if (cnt > 0) {
            for (int mIndex = 0; mIndex != cnt; mIndex++) {
                // get SaleItemMakeup at mIndex
                mSsaleItemMakeup = _items_2_Post.get( 1 );
                // get components parts
                mBitmap = mSsaleItemMakeup.get_Bitmap();
                mItemtxtHeader = mSsaleItemMakeup.get_FS_SaleItemName();
                postOPbj_2_Grp( _acActivity, WogalstestGroup, mItemtxtHeader, HlpFbook_Posts.PostImageType.POST_BITMAP_PHOTO, true, mBitmap );
            }
        }


        tst = 0;


        PostCount = mGraphFaceBookPartPost_Id.size();

        mStr = "--";

    }

    public void postOPbj_2_Grp (Activity _acActivity, String _destination_id, String _postMessage, PostImageType _ImgeTyp, boolean _pubStus, Bitmap _imgObj) {

        Bundle params = new Bundle();
        params.clear();
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();


        switch (_ImgeTyp) {
            case NONE: {
                break;
            }
            case POST_BITMAP_PHOTO: {
                ByteArrayOutputStream stream = null;
                stream = new ByteArrayOutputStream();
                try {
                    _imgObj.compress( Bitmap.CompressFormat.PNG, 100, stream );
                } catch (Exception e) {
                    e.printStackTrace();
                }
                byte[] _FS_ItemBitmapArray = stream.toByteArray();
                try {
                    params.putByteArray( "picture", _FS_ItemBitmapArray );
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case POST_IMAGE_PHOTO: {
                //   params.putString( "url", (String) _imgObj );
                break;
            }
        }
        //     params.putString( "message", _postMessage );
        params.putString( "message", "--- 200 300 ---" );
        params.putString( "published", _pubStus ? "true" : "false" );
        new GraphRequest(
                currentAccessToken,
                "/" + _destination_id + "/photos",
                params,
                HttpMethod.POST,
                new GraphRequest.Callback() {
                    public void onCompleted (GraphResponse response) {
                        mGraph_onCompleted_callBack_Listerner.CallBackFunction( response.toString() );
                        mStr = response.toString();
                        mGraphFaceBookPartPost_Id.add( response );
                        mStr += "";
                        Json_objQ = response.getJSONObject();
                        try {
                            mStr = (String) Json_objQ.get( "id" );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mStr += "";
                    }
                }
        ).executeAsync();
        mStr += "";
    }

    public int DummyInVokeEvent (int aa) {
        mGraph_onCompleted_callBack_Listerner.CallBackFunction( "Wogal Heck & puggle heck" );
        return 44;
    }

    public void setEventListener (Graph_OnCompleted_CallBack_Interface eventListener) {
        mGraph_onCompleted_callBack_Listerner = eventListener;
    }


    public enum PostImageType {
        NONE,
        POST_IMAGE_PHOTO,
        POST_BITMAP_PHOTO,
    }


    public interface Graph_OnCompleted_CallBack_Interface {
        int CallBackFunction (String _str);
    }


}
















