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

    private static String mStr_static = "";
    public ArrayList<GraphResponse> mGraphFaceBookPartPost_Id;
    public int tst = 0;
    private String mStr;
    private JSONObject Json_objQ;
    //  private String mStr = "";



    // call back event for final post on multi post actions
    private Graph_OnfinalPost_CallBack_Interface mGraph_onfinalPost_callBack_Listerner;
    public void setEventListener_Final_Post (Graph_OnfinalPost_CallBack_Interface _eventListener) {
        mGraph_onfinalPost_callBack_Listerner = _eventListener;
    }


    // call back event for custom posts  ( we supply most of the stuff and data )
    private Graph_Custom_Post_CallBack_Interface mGraph_custom_post_callBack_Listner;
    public void setEventListener_custom_Post (Graph_Custom_Post_CallBack_Interface _eventListener) {
        mGraph_custom_post_callBack_Listner = _eventListener;
    }

    // call back event on each iteration of a multi pos
    private Graph_OnCompleted_CallBack_Interface mGraph_onCompleted_callBack_Listerner;
    public void setEventListener (Graph_OnCompleted_CallBack_Interface _eventListener) {
        mGraph_onCompleted_callBack_Listerner = _eventListener;
    }




    private ArrayList<GraphResponse> _mMultiPost_Response;
    private ArrayList<SaleItemMakeup> _items_2_Post;
    private Activity _acActivity;
    private String _destination_id;
    private String _MainpostMessage;

    public HlpFbook_Posts (ArrayList<SaleItemMakeup> _items_2_Post, Activity _acActivity, String _destination_id, String _MainpostMessage) {
        this._items_2_Post = _items_2_Post;
        this._acActivity = _acActivity;
        this._destination_id = _destination_id;
        this._MainpostMessage = _MainpostMessage;
        _mMultiPost_Response = new ArrayList<>();
    }


    public static void TestPost (String _postId, String _postText) {
        Bundle params = new Bundle();
        params.clear();
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        params.putString( "message", _postText );

        new GraphRequest(
                currentAccessToken,
                "/" + _postId + "/feed",
                params,
                HttpMethod.POST,
                new GraphRequest.Callback() {
                    public void onCompleted (GraphResponse _response) {
                        mStr_static = _response.toString();
                        //   DoFinal_PublishPost( _response );
                    }
                }
        ).executeAsync();
    }

    public ArrayList<GraphResponse> get_mMultiPost_Response () {
        return _mMultiPost_Response;
    }

    @Override
    public void run () {
        PostMultiplePicys( _items_2_Post, _acActivity, _destination_id, _MainpostMessage );
        if (false) {
            try {
                Thread.sleep( 1000 );
                if (_mMultiPost_Response.size() == 3)
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
                postOPbj_2_Grp( _acActivity, WogalstestGroup, mItemtxtHeader, FB_Consts.PostImageType.POST_BITMAP_PHOTO, true, mBitmap );
            }
        }
        tst = 0;
        PostCount = mGraphFaceBookPartPost_Id.size();
        mStr = "--";
    }

    private void postOPbj_2_Grp (Activity _acActivity, String _destination_id, String _postMessage, FB_Consts.PostImageType _ImgeTyp, boolean _pubStus, Bitmap _imgObj) {

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
                    public void onCompleted (GraphResponse _response) {
                        _mMultiPost_Response.add( _response );
                        mGraph_onCompleted_callBack_Listerner.CallBackFunction( _response, _mMultiPost_Response, _items_2_Post );
                        if (_mMultiPost_Response.size() == _items_2_Post.size()) {
                            // if all done then do final publish post
                            DoFinal_PublishPost( _response );
                        }
                    }
                }
        ).executeAsync();
        mStr += "";
    }



    private void DoFinal_PublishPost (GraphResponse _response) {
        mGraph_onfinalPost_callBack_Listerner.CallBackFunctionFinal_Post( _response, _mMultiPost_Response, _items_2_Post );
        // final post to auth previous multi posts ,,
        Bundle params = new Bundle();

        params = FB_HelperClss.FB_Extract_NamedValue_from_Respose( _mMultiPost_Response );


    }

}
















