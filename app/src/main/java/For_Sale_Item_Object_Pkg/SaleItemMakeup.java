package For_Sale_Item_Object_Pkg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

// class ( group ) to hold each item in for sale item
public class SaleItemMakeup implements Serializable {


    // bitmap  of pictures for the
    private byte[] _FS_ItemBitmapArray;


    //  private Bitmap _Bitmap;

    private transient Bitmap _Bitmap;

    // byte array for item  voice sound file
    private byte[] _FS_ItemHeaderVoiceFileDataArray;

    // text of item for sale
    private String _FS_SaleItemName;
    // path to voice files for the item in group  ** store as bytes later on
    private String _FS_VoiceItemFilePath;

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

    public String get_FS_VoiceItemFilePath () {
        return _FS_VoiceItemFilePath;
    }

//  private ArrayList<Bitmap> _FS_ItemImages;

    public void set_FS_VoiceItemFilePath (String _FS_VoiceItemFilePath) {
        this._FS_VoiceItemFilePath = _FS_VoiceItemFilePath;
    }

}
