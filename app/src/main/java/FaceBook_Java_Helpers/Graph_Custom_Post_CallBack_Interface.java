package FaceBook_Java_Helpers;

import com.facebook.GraphResponse;

import java.util.ArrayList;

import For_Sale_Item_Object_Pkg.SaleItemMakeup;

/**
 * Created by wogal on 4/19/2017.
 */

public interface Graph_Custom_Post_CallBack_Interface {
    int CallBack_On_Custom_Post (String _destination_id, GraphResponse _response, ArrayList<GraphResponse> mMultiPost_Response, ArrayList<SaleItemMakeup> _items_2_Post);
}
