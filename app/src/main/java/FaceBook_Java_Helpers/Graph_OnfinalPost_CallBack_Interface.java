package FaceBook_Java_Helpers;

import com.facebook.GraphResponse;

import java.util.ArrayList;

import For_Sale_Item_Object_Pkg.SaleItemMakeup;

public interface Graph_OnfinalPost_CallBack_Interface {
    int CallBackFunctionFinal_Post (GraphResponse _response, ArrayList<GraphResponse> mMultiPost_Response, ArrayList<SaleItemMakeup> _items_2_Post);
}
