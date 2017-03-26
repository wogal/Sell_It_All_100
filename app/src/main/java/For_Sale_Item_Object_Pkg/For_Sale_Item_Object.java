package For_Sale_Item_Object_Pkg;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by wogal on 3/25/2017.
 */

public abstract class For_Sale_Item_Object extends SaleItemMakeup implements Serializable {


    //<editor-fold desc=" Variables  ">
    // date when for sale item was created
    private long _FS_DateTime_of_Conseption;
    // name of item for sale
    private String _FS_SaleItemName;
    // list of item objects ie:pic , text , voice , --
    private ArrayList<SaleItemMakeup> _ItemGroup;
    // path to voice files for the item in group  ** store as bytes later on
    private String _FS_VoiceItemFilePath;

    public For_Sale_Item_Object (long _FS_DateTime_of_Conseption) {
        this._FS_DateTime_of_Conseption = _FS_DateTime_of_Conseption;
        this.set_ItemGroup( new ArrayList<SaleItemMakeup>() );
    }

    //</editor-fold>
    //<editor-fold desc="  Getters And setters  ">
    public long get_FS_DateTime_of_Conseption () {
        return _FS_DateTime_of_Conseption;
    }

    public void set_FS_DateTime_of_Conseption (long _FS_DateTime_of_Conseption) {
        this._FS_DateTime_of_Conseption = _FS_DateTime_of_Conseption;
    }

    public String get_FS_SaleItemName () {
        return _FS_SaleItemName;
    }

    public void set_FS_SaleItemName (String _FS_SaleItemName) {
        this._FS_SaleItemName = _FS_SaleItemName;
    }

    public ArrayList<SaleItemMakeup> get_ItemGroup () {
        return _ItemGroup;
    }

    public void set_ItemGroup (ArrayList<SaleItemMakeup> _ItemGroup) {
        this._ItemGroup = _ItemGroup;
    }

    public String get_FS_VoiceItemFilePath () {
        return _FS_VoiceItemFilePath;
    }

    public void set_FS_VoiceItemFilePath (String _FS_VoiceItemFilePath) {
        this._FS_VoiceItemFilePath = _FS_VoiceItemFilePath;
    }
    //</editor-fold>


    public abstract void test ();


}


// class ( group ) to hold each item in for sale item
class SaleItemMakeup {
    // bitmap list of pictures for the
    public ArrayList<Bitmap> _FS_ItemImages;

    // path to voice files for the item in group  ** store as bytes later on
    private String _FS_VoiceItemFilePath;

}
