package FaceBook_Java_Helpers;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import For_Sale_Item_Object_Pkg.SaleItemMakeup;

import static com.egs.wogal.forsale_items_sat_18_3_2017_100.Activity_FaceBook_v10.WogalstestGroup;

/**
 * Created by wogal on 4/13/2017.
 */

public class HlpFbook_Posts implements Runnable {

    public ArrayList<GraphResponse> mGraphFaceBookPartPost_Id;
    public int tst = 0;
    private String mStr;
    private JSONObject Json_objQ;
    //  private String mStr = "";
    private Graph_OnCompleted_CallBack_Interface mGraph_onCompleted_callBack_Listerner;
    private ArrayList<GraphResponse> mMultiPost_Response;
    private ArrayList<SaleItemMakeup> _items_2_Post;
    private Activity _acActivity;
    private String _destination_id;
    private String _MainpostMessage;
    public HlpFbook_Posts (ArrayList<SaleItemMakeup> _items_2_Post, Activity _acActivity, String _destination_id, String _MainpostMessage) {
        this._items_2_Post = _items_2_Post;
        this._acActivity = _acActivity;
        this._destination_id = _destination_id;
        this._MainpostMessage = _MainpostMessage;
        mMultiPost_Response = new ArrayList<>();
    }

    public ArrayList<GraphResponse> get_mMultiPost_Response () {
        return mMultiPost_Response;
    }

    @Override
    public void run () {
        PostMultiplePicys( _items_2_Post, _acActivity, _destination_id, _MainpostMessage );
        if (false) {
            try {
                Thread.sleep( 1000 );
                if (mMultiPost_Response.size() == 3)
                    return;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    // master wrapper for posting " multiple " images
    private void PostMultiplePicys (ArrayList<SaleItemMakeup> _items_2_Post, Activity _acActivity, String _destination_id, String _MainpostMessage) {
        int cnt;
        SaleItemMakeup mSsaleItemMakeup;
        cnt = _items_2_Post.size();
        Bitmap mBitmap;
        String mItemtxtHeader;
        int PostCount;


        mGraphFaceBookPartPost_Id = new ArrayList<>();
        cnt = _items_2_Post.size();

        if (cnt > 0) {
            for (int mIndex = 0; mIndex != cnt; mIndex++) {
                // get SaleItemMakeup at mIndex
                mSsaleItemMakeup = _items_2_Post.get( mIndex );
                // get components parts
                mBitmap = mSsaleItemMakeup.get_Bitmap();
                mItemtxtHeader = mSsaleItemMakeup.get_FS_SaleItemName();
                // post all activity ( but with publish stats == false ) and store ids 
                postOPbj_2_Grp( _acActivity, WogalstestGroup, mItemtxtHeader, HlpFbook_Posts.PostImageType.POST_BITMAP_PHOTO, true, mBitmap );
            }
        }




        tst = 0;
        PostCount = mGraphFaceBookPartPost_Id.size();
        mStr = "--";
    }

    private void postOPbj_2_Grp (Activity _acActivity, String _destination_id, String _postMessage, PostImageType _ImgeTyp, boolean _pubStus, Bitmap _imgObj) {

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
        params.putString( "message", "--- Diana's Many Sales Items ---" );
        params.putString( "published", _pubStus ? "true" : "false" );
        new GraphRequest(
                currentAccessToken,
                "/" + _destination_id + "/photos",
                params,
                HttpMethod.POST,
                new GraphRequest.Callback() {
                    public void onCompleted (GraphResponse _response ) {
                        mMultiPost_Response.add( _response );
                        mGraph_onCompleted_callBack_Listerner.CallBackFunction( _response,mMultiPost_Response,_items_2_Post);
                    }
                }
        ).executeAsync();
        mStr += "";
    }

    public void setEventListener (Graph_OnCompleted_CallBack_Interface eventListener) {
        mGraph_onCompleted_callBack_Listerner = eventListener;
    }


    private enum PostImageType {
        NONE,
        POST_IMAGE_PHOTO,
        POST_BITMAP_PHOTO,
    }


    public interface Graph_OnCompleted_CallBack_Interface {
        // call back  from post function
        int CallBackFunction (GraphResponse _response, ArrayList<GraphResponse> mMultiPost_Response, ArrayList<SaleItemMakeup> _items_2_Post);
    }


}
















