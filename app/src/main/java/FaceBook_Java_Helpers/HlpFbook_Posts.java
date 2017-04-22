package FaceBook_Java_Helpers;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import For_Sale_Item_Object_Pkg.SaleItemMakeup;

/**
 * Created by wogal on 4/13/2017.
 */

public class HlpFbook_Posts implements Runnable {
    public static final String TAG = "HlpFbook_Posts";
    private static String mStr_static = "";
    public ArrayList<GraphResponse> mGraphFaceBookPartPost_Id;
    public int tst = 0;
    private String mStr;
    private JSONObject Json_objQ;
    //  private String mStr = "";


    // call back event for final post on multi post actions
    private Graph_OnfinalPost_CallBack_Interface mGraph_onfinalPost_callBack_Listerner;
    // call back event for custom posts  ( we supply most of the stuff and data )
    private Graph_Custom_Post_CallBack_Interface mGraph_custom_post_callBack_Listner;
    // call back event on each iteration of a multi pos
    private Graph_OnCompleted_CallBack_Interface mGraph_onCompleted_callBack_Listerner;
    private ArrayList<GraphResponse> _mMultiPost_Response;
    private ArrayList<SaleItemMakeup> _items_2_Post;
    private Activity _acActivity;
    private String _destination_id;
    private String _MainpostMessage;
    private boolean _bool_image_per_post;

    public HlpFbook_Posts (ArrayList<SaleItemMakeup> _items_2_Post, Activity _acActivity, String _destination_id, String _MainpostMessage, boolean _bool_image_per_post) {
        this._items_2_Post = _items_2_Post;
        this._acActivity = _acActivity;
        this._destination_id = _destination_id;
        this._MainpostMessage = _MainpostMessage;
        this._bool_image_per_post = _bool_image_per_post;
        _mMultiPost_Response = new ArrayList<>();
    }


    public void setEventListener_Final_Post (Graph_OnfinalPost_CallBack_Interface _eventListener) {
        mGraph_onfinalPost_callBack_Listerner = _eventListener;
    }

    public void setEventListener_custom_Post (Graph_Custom_Post_CallBack_Interface _eventListener) {
        mGraph_custom_post_callBack_Listner = _eventListener;
    }

    public void setEventListener (Graph_OnCompleted_CallBack_Interface _eventListener) {
        mGraph_onCompleted_callBack_Listerner = _eventListener;
    }

    public ArrayList<GraphResponse> get_mMultiPost_Response () {
        return _mMultiPost_Response;
    }

    @Override
    public void run () {
        Log.d( TAG, " run Invoked" );
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
        Log.d( TAG, " PostMultiplePicys Invoked" );
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
                Log.d( TAG, " postOPbj_2_Grp Invoked iteration -> " + cnt );
                postOPbj_2_Grp( _acActivity, _destination_id, mItemtxtHeader, mBitmap );
            }
        }
    }

    private void postOPbj_2_Grp (Activity _acActivity, final String _destination_id, String _postMessage, Bitmap _imgObj) {

        Bundle params = new Bundle();
        params.clear();
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();

        Log.d( TAG, " postOPbj_2_Grp Invoked " );
        // convert Bitmap ( _imgObj ) to   byte[] ( _FS_ItemBitmapArray ) for facebook to handel
        ByteArrayOutputStream stream;
        stream = new ByteArrayOutputStream();
        try {
            _imgObj.compress( Bitmap.CompressFormat.PNG, 100, stream );
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] _FS_ItemBitmapArray = stream.toByteArray();
        try {
            params.putByteArray( FB_Consts.FB_picture, _FS_ItemBitmapArray );
        } catch (Exception e) {
            e.printStackTrace();
        }


        //     params.putString( "message", _postMessage );
        params.putString( "message", "--- Diana's Many Sales Items ---" );
        params.putString( "published", _bool_image_per_post ? "false" : "true" );
        new GraphRequest(
                currentAccessToken,
                "/" + _destination_id + "/photos",
                params,
                HttpMethod.POST,
                new GraphRequest.Callback() {
                    public void onCompleted (GraphResponse _response) {
                        Log.d( TAG, " CallBackFunction_MultiPost Invoked " );
                        _mMultiPost_Response.add( _response );
                        mGraph_onCompleted_callBack_Listerner.CallBackFunction_MultiPost( _destination_id, _response, _mMultiPost_Response, _items_2_Post );
                        if (_mMultiPost_Response.size() == _items_2_Post.size()) {
                            // if all done then do final publish post
                            DoFinal_PublishPost( _destination_id, _response );
                        }
                    }
                }
        ).executeAsync();
        Log.d( TAG, " postOPbj_2_Grp Exited  " );
        mStr += "";
    }

    private void DoFinal_PublishPost (String _destination_id, GraphResponse _response) {
        mGraph_onfinalPost_callBack_Listerner.CallBack_OnFinal_On_Mulit_Image_Post( _destination_id, _response, _mMultiPost_Response, _items_2_Post );
        // final post to auth previous multi posts ,,
        String mstr_ = _bool_image_per_post ? "true" : "false";
        Log.d( TAG, " CallBack DoFinal_PublishPost Invoked " + " Img Per Post = " + mstr_ );

        Bundle params;
        params = FB_HelperClss.FB_Extract_NamedValue_from_Respose( _mMultiPost_Response );
        params.putString( FB_Consts.FB_message, "Multi Post Authorise 100" );
        // do custom post
        if (false == _bool_image_per_post) {
            Log.d( TAG, " ** NOT **  Invoking CustomPost (Final ) Due 2  _bool_image_per_post == " + mstr_ );
        } else {
            Log.d( TAG, " ** I Am  **  Invoking CustomPost (Final ) Due 2  _bool_image_per_post == " + mstr_ );
            CustomPost( _destination_id, params );
        }
    }


    public void CustomPost (final String _destination_id, Bundle _params) {
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        Log.d( TAG, " CustomPost Invoked " );
        new GraphRequest(
                currentAccessToken,
                "/" + _destination_id + "/feed",
                _params,
                HttpMethod.POST,
                new GraphRequest.Callback() {
                    public void onCompleted (GraphResponse _response) {
                        mGraph_custom_post_callBack_Listner.CallBack_On_Custom_Post( _destination_id, _response, _mMultiPost_Response, _items_2_Post );
                    }
                }
        ).executeAsync();
        Log.d( TAG, " CustomPost Exited " );
    }

}
















