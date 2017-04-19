package FaceBook_Java_Helpers;

import com.facebook.GraphResponse;

import java.util.ArrayList;

import For_Sale_Item_Object_Pkg.SaleItemMakeup;

/**
 * Created by wogal on 4/19/2017.
 */

public interface Graph_OnCompleted_CallBack_Interface {
    // call back  from post function
    int CallBackFunction (GraphResponse _response, ArrayList<GraphResponse> mMultiPost_Response, ArrayList<SaleItemMakeup> _items_2_Post);
}


