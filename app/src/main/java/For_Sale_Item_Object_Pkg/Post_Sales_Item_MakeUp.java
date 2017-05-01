package For_Sale_Item_Object_Pkg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.facebook.GraphResponse;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/* Class " Post_Sales_Item_MakeUp " to hold EACH  item For sale Master holding object is
*
*
* */



public class Post_Sales_Item_MakeUp implements Serializable {


    //region Vars Used In each sales items
    // START  of item used vars used in item object

    // bitmap  of pictures for the
    private byte[] _FS_ItemBitmapArray;

    //  private Bitmap _Bitmap -- used as temporary var in translation --
    private transient Bitmap _Bitmap;

    // byte array for item  voice sound file for this item
    private byte[] _FS_ItemHeaderVoiceFileDataArray;

    // text name  of item for sale
    private String _FS_SaleItemName;


    private GraphResponse _graph_response;


    // END of item used vars used in item object
    //endregion


    public GraphResponse get_graph_response () {
        return _graph_response;
    }

    public void set_graph_response (GraphResponse _graph_response) {
        this._graph_response = _graph_response;
    }

    public Bitmap get_Bitmap () {
        Bitmap mBitmapB;
        mBitmapB = BitmapFactory.decodeByteArray( _FS_ItemBitmapArray, 0, _FS_ItemBitmapArray.length );
        return mBitmapB;
    }

    public void set_Bitmap (Bitmap _bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        _bitmap.compress( Bitmap.CompressFormat.PNG, 100, stream );
        _FS_ItemBitmapArray = stream.toByteArray();
    }

    public String get_FS_SaleItemName () {
        return _FS_SaleItemName;
    }

    public void set_FS_SaleItemName (String _FS_SaleItemName) {
        this._FS_SaleItemName = _FS_SaleItemName;
    }


}
