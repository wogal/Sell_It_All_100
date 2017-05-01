package FaceBook_Java_Helpers;

import com.facebook.GraphResponse;

import java.util.ArrayList;

import For_Sale_Item_Object_Pkg.Post_Sales_Item_MakeUp;

/**
 * Created by wogal on 4/19/2017.
 */

public interface Graph_OnCallBackFunction_EachPost_Interface {
    int CallBackFunction_EachPost (int _post_Index, String _destination_id, GraphResponse _response, ArrayList<GraphResponse> mMultiPost_Response, ArrayList<Post_Sales_Item_MakeUp> _items_2_Post);

}


