package FaceBook_Java_Helpers;

import com.facebook.GraphResponse;

import java.util.ArrayList;

import For_Sale_Item_Object_Pkg.Post_Sales_Item_MakeUp;

public interface Graph_OnfinalPost_CallBack_Interface {
    int CallBack_OnFinal_On_Mulit_Image_Post (int _post_Index, String _destination_id, GraphResponse _response, ArrayList<GraphResponse> mMultiPost_Response, ArrayList<Post_Sales_Item_MakeUp> _items_2_Post);
}
