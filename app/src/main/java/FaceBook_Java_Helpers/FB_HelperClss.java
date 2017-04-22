package FaceBook_Java_Helpers;

import android.os.Bundle;

import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by wogal on 4/20/2017.
 */

public class FB_HelperClss {






    public static String FB_Pares_Format_attached_media_feild (int _feildIndex) {
        StringBuilder sb = new StringBuilder();
        sb.append( FB_Consts.FB_attached_media ).append( "[" ).append( _feildIndex ).append( "]" );
        String mStr_static = sb.toString();
        return mStr_static;
    }



    public static String FB_Format_Post_String (String _idStr) {
        StringBuilder sb = new StringBuilder();
        sb.append( "{\"" ).append( FB_Consts.FB_media_fbid ).append( "\":\"" ).append( _idStr ).append( "\"}" );
        String mStr_static = sb.toString();
        return mStr_static;
    }

    public static Bundle FB_Extract_NamedValue_from_Respose (ArrayList<GraphResponse> _mMultiPost_Response) {
        // final post to auth previous multi posts ,,
        int mMulti_Postcount;
        Bundle params = new Bundle();
        String mMstr_postId = "";
        String mMstr_posttotalStr = "";
        mMulti_Postcount = _mMultiPost_Response.size();
        if (mMulti_Postcount == 0)
            return params;

        params.clear();
        int index;
        String FB_attached_media_index;
        GraphResponse mGraphResponse;
        for (index = 0; index != mMulti_Postcount; index++) {
            mGraphResponse = _mMultiPost_Response.get( index );
            JSONObject Json_objQ;
            Json_objQ = mGraphResponse.getJSONObject();
            try {
                mMstr_postId = (String) Json_objQ.get( FB_Consts.FB_post_id );
            } catch (JSONException e) {
                e.printStackTrace();
            }
            FB_attached_media_index = FB_HelperClss.FB_Pares_Format_attached_media_feild( index );
            mMstr_posttotalStr = FB_HelperClss.FB_Format_Post_String( mMstr_postId );
            params.putString( FB_attached_media_index, mMstr_posttotalStr );
        }
        return params;
    }


}
