package For_Sale_Item_Object_Pkg;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by wogal on 3/25/2017.
 */

public class For_Sale_Item_Object extends SaleItemMakeup implements Serializable {


    //<editor-fold desc=" Variables  ">
    // date when for sale item was created
    private long _FS_DateTime_of_Conseption;
    // name of item for sale
    private String _FS_SaleItemName;
    // list of item objects ie:pic , text , voice , --
    private ArrayList<SaleItemMakeup> _ItemGroupArray;
    // text to describe sales item
    private String _FS_ItemHeaderText = "";
    // byte array for item header voice sound file
    private byte[] _FS_ItemHeaderVoiceFileDataArray;




    public For_Sale_Item_Object () {
        long _FS_DateTime_of_Conseption = new Time( System.currentTimeMillis() ).getTime();
        this._FS_DateTime_of_Conseption = _FS_DateTime_of_Conseption;
        this.set_ItemGroupArray( new ArrayList<SaleItemMakeup>() );
    }

    public ArrayList<SaleItemMakeup> get_ItemGroupArray () {
        return _ItemGroupArray;
    }

    public void set_ItemGroupArray (ArrayList<SaleItemMakeup> _ItemGroupArray) {
        this._ItemGroupArray = _ItemGroupArray;
    }

    public String get_FS_ItemHeaderText () {
        return _FS_ItemHeaderText;
    }

    public void set_FS_ItemHeaderText (String _FS_ItemHeaderText) {
        this._FS_ItemHeaderText = _FS_ItemHeaderText;
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


    public byte[] get_FS_ItemHeaderVoiceFileData () {
        return _FS_ItemHeaderVoiceFileDataArray;
    }

    public void set_FS_ItemHeaderVoiceFileData (byte[] _FS_ItemHeaderVoiceFileDate) {
        this._FS_ItemHeaderVoiceFileDataArray = _FS_ItemHeaderVoiceFileDate;
    }
    //</editor-fold>


}


