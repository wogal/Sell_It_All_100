package FaceBook_Java_Helpers;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import For_Sale_Item_Object_Pkg.Post_Sales_Item_MakeUp;

/**
 * Created by wogal on 4/13/2017.
 */

public class HlpFbook_Posts implements Runnable {


    public static final String TAG = "HlpFbook_Posts";
    private final int itemUpdateIndex = 0;
    public ArrayList<GraphResponse> mGraphFaceBookPartPost_Id;
    // call back event for final post on multi post actions
    private Graph_OnfinalPost_CallBack_Interface mGraph_onfinalPost_callBack_Listerner;
    // call back event for custom posts  ( we supply most of the stuff and data )
    private Graph_Custom_Post_CallBack_Interface mGraph_custom_post_callBack_Listner;
    // call back event on each iteration of a multi pos
    private Graph_OnCallBackFunction_EachPost_Interface mGraph_onCompleted_callBack_Listerner;
    private ArrayList<GraphResponse> _mMultiPost_Response;
    private ArrayList<Post_Sales_Item_MakeUp> _items_2_Post;
    private Activity _acActivity;
    private String _destination_id;
    private String _MainpostMessage;
    private boolean _bool_image_per_post;

    private int wogal_test = 0;

    // main entry point and auto invoke/run ( if instantRun == true )
    public HlpFbook_Posts (ArrayList<Post_Sales_Item_MakeUp> _items_2_Post, Activity _acActivity, String _destination_id, String _MainpostMessage, boolean _m_bool_image_per_post) {
        // List of for sale items Post_Sales_Master_Object -> < Post_Sales_Item_MakeUp >
        this._items_2_Post = _items_2_Post;
        // handel / pointer to parent
        this._acActivity = _acActivity;
        // FB group / page to post to
        this._destination_id = _destination_id;
        // text to be asserted with this post ( not posted )
        this._MainpostMessage = _MainpostMessage;
        // if true will post each iteration of _items_2_Post separably , else one post will share all objects
        this._bool_image_per_post = _m_bool_image_per_post;
        // holds each posts GraphResponse  ( _ response ) and is passed back during callbacks
        _mMultiPost_Response = new ArrayList<>();
    }


    public void setEventListener_Final_Post (Graph_OnfinalPost_CallBack_Interface _eventListener) {
        mGraph_onfinalPost_callBack_Listerner = _eventListener;
    }

    public void setEventListener_custom_Post (Graph_Custom_Post_CallBack_Interface _eventListener) {
        mGraph_custom_post_callBack_Listner = _eventListener;
    }

    public void setEventListener (Graph_OnCallBackFunction_EachPost_Interface _eventListener) {
        mGraph_onCompleted_callBack_Listerner = _eventListener;
    }


    public ArrayList<GraphResponse> get_mMultiPost_Response () {
        return _mMultiPost_Response;
    }

    @Override
    public void run () {
        Log.d( TAG, " run Invoked" );
        wogal_test = 0;
        PostMultiplePicys( _items_2_Post, _acActivity, _destination_id, _MainpostMessage );
        if (false) {
            try {
                Thread.sleep( 100 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // master wrapper for posting " multiple " images
    private void PostMultiplePicys (ArrayList<Post_Sales_Item_MakeUp> _items_2_Post, Activity _acActivity, String _destination_id, String _MainpostMessage) {
        int mCnt;
        Log.d( TAG, " PostMultiplePicys Invoked" );
        Post_Sales_Item_MakeUp mSsaleItemMakeUp;
        Bitmap mBitmap;
        String mItemtxtHeader;
        mCnt = _items_2_Post.size();
        wogal_test = 0;
        //    itemUpdateIndex = 0;
        if (mCnt > 0) {
            for (int mIndex = 0; mIndex != mCnt; mIndex++) {
                // get Post_Sales_Item_MakeUp at mIndex
                mSsaleItemMakeUp = _items_2_Post.get( mIndex );
                // get components parts
                mBitmap = mSsaleItemMakeUp.get_Bitmap();
                mItemtxtHeader = mSsaleItemMakeUp.get_FS_SaleItemName();
                // post all activity ( but with publish stats == false ) and store ids
                Log.d( TAG, " postOPbj_2_Group Invoked iteration -> " + mCnt );
                postOPbj_2_Group( wogal_test, mIndex, _acActivity, _destination_id, mItemtxtHeader, mBitmap );
            }
        }
    }

    private void postOPbj_2_Group (int _wogal_test, final int _mCnt, Activity _acActivity, final String _destination_id, String _postMessage, Bitmap _imgObj) {

        Bundle params = new Bundle();
        params.clear();
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();

        Log.d( TAG, " postOPbj_2_Group Invoked " );
        // convert Bitmap ( _imgObj ) to   byte[] ( _FS_ItemBitmapArray ) for facebook to handel
        ByteArrayOutputStream stream;

        //    params.putString( "caption", " ( 0 ) Item Cnt -> " + _mCnt );


        String mDateStr;
        String mTimeStr;
        long msTime = System.currentTimeMillis();
        Date curDateTime = new Date( msTime );
        SimpleDateFormat formatter = new SimpleDateFormat( "MMMM' 'EEEE'  'yyyy  @ hh:mm a" );
        mDateStr = formatter.format( curDateTime );
        params.putString( FB_Consts.FB_caption, "caption Testing part post message App Post Beta 101 \n " + mDateStr );
        //    params.putString( FB_Consts.FB_caption, "Testing part post caption App Post Beta 101 \n " + mDateStr );


        //  GraphRequestBatch


        stream = new ByteArrayOutputStream();
        try {
            _imgObj.compress( Bitmap.CompressFormat.PNG, 100, stream );
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] _FS_ItemBitmapArray = stream.toByteArray();
        if (true) {
            try {
                params.putByteArray( FB_Consts.FB_picture, _FS_ItemBitmapArray );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String mPubPost;
        mPubPost = _bool_image_per_post ? "false" : "true";
        //     params.putString( "message", "Wogal Heck And Puggle Heck" );
        params.putString( "published", mPubPost );


        new GraphRequest(
                currentAccessToken,
                //     "/" + _destination_id + "/feed",
                "/" + _destination_id + "/photos",   //   WORKS
                //           "/357763397958712/feed",
                params,
                HttpMethod.POST,
                new GraphRequest.Callback() {
                    public void onCompleted (GraphResponse _response) {
                        Log.d( TAG, " CallBackFunction_EachPost Invoked " );
                        // add _response to Array List of GraphResponse
                        _mMultiPost_Response.add( _response );
                        // invoke call back to Parent ( or any interested parti's )
                        mGraph_onCompleted_callBack_Listerner.CallBackFunction_EachPost( 0, _destination_id, _response, _mMultiPost_Response, _items_2_Post );
                        // check if its the last post in list if so invoke final post
                        if (_mMultiPost_Response.size() == _items_2_Post.size()) {
                            // if all done then do final publish post
                            mGraph_onfinalPost_callBack_Listerner.CallBack_OnFinal_On_Mulit_Image_Post( 0, _destination_id, _response, _mMultiPost_Response, _items_2_Post );
                            DoFinal_PublishPost_QQQ( _destination_id, _response );
                        }
                    }
                }
        ).executeAsync();
        Log.d( TAG, " postOPbj_2_Group Exited  " );
    }

    private void DoFinal_PublishPost_QQQ (String _destination_id, GraphResponse _response) {
        // final post to auth previous multi posts ,,
        String mstr_ = _bool_image_per_post ? "true" : "false";

        if (false == _bool_image_per_post)
            return;


        Log.d( TAG, " CallBack DoFinal_PublishPost Invoked " + " Img Per Post = " + mstr_ );

        Bundle params;
        params = FB_HelperClss.FB_Extract_NamedValue_from_Respose_QQQ( _mMultiPost_Response );
        //    params.putString( FB_Consts.FB_message, "Multi Post Authorise 100" );
        //    params.putString( FB_Consts.FB_message, "Multi Post Authorise 200" );
        //    params.putString( FB_Consts.FB_message, "Multi Post Authorise 300" );

        String mDateStr;
        String mTimeStr;
        long msTime = System.currentTimeMillis();
        Date curDateTime = new Date( msTime );
        SimpleDateFormat formatter = new SimpleDateFormat( "MMMM' 'EEEE'  'yyyy  @ hh:mm a" );
        mDateStr = formatter.format( curDateTime );

        params.putString( FB_Consts.FB_message, "message  Final Testing message App Post Beta 101 \n " + mDateStr );

        params.putString( FB_Consts.FB_caption, "caption  Testing caption App Post Beta 101 \n " + mDateStr );

        params.putString( "published", "true" );
        // do custom post
        if (false == _bool_image_per_post) {
            Log.d( TAG, " ** NOT **  Invoking CustomPost (Final ) Due 2  _bool_image_per_post == " + mstr_ );
        } else {
            Log.d( TAG, " ** I Am  **  Invoking CustomPost (Final ) Due 2  _bool_image_per_post == " + mstr_ );
            CustomPost_QQQ( _destination_id, params );
        }


    }


    public void CustomPost_QQQ (final String _destination_id, Bundle _params) {
        //   if (false)
        //       return;
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        Log.d( TAG, " CustomPost Invoked " );
        new GraphRequest(
                currentAccessToken,
                "/" + _destination_id + "/feed", _params,
//                "/" + _destination_id + "", _params,
                HttpMethod.POST,
                new GraphRequest.Callback() {
                    public void onCompleted (GraphResponse _response) {
                        mGraph_custom_post_callBack_Listner.CallBack_On_Custom_Post( 0, _destination_id, _response, _mMultiPost_Response, _items_2_Post );
                    }
                }
        ).executeAsync();
        Log.d( TAG, " CustomPost Exited " );
    }

}
















