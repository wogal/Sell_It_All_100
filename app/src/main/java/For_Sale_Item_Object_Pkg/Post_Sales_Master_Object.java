package For_Sale_Item_Object_Pkg;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by wogal on 3/25/2017.
 */

public class Post_Sales_Master_Object extends Post_Sales_Item_MakeUp implements Serializable {


    //<editor-fold desc=" Variables  ">
    // date when for sale item was created
    private long _FS_DateTime_of_Conseption = 0;
    // name of item for sale
    private String _FS_SaleItemName = "";
    // list of item objects ie:pic , text , voice , --
    private ArrayList<Post_Sales_Item_MakeUp> _ItemGroupArray;
    // text to describe sales post
    private String _FS_Post_Details_Text = "";
    // byte array for item header voice sound file
    private byte[] _FS_ItemHeaderVoiceFileDataArray;
    // float representation of post cost
    private float _FS_PostCost = 12.69F;

    public String get_FS_Post_File_Name () {
        return _FS_Post_File_Name;
    }

    public void set_FS_Post_File_Name (String _FS_Post_File_Name) {
        this._FS_Post_File_Name = _FS_Post_File_Name;
    }

    private String _FS_Post_File_Name = "";


    public float get_FS_PostCost () {
        return _FS_PostCost;
    }

    public void set_FS_PostCost (float _FS_PostCost) {
        this._FS_PostCost = _FS_PostCost;
    }




    public Post_Sales_Master_Object () {
        long _FS_DateTime_of_Conseption = new Time( System.currentTimeMillis() ).getTime();
        this._FS_DateTime_of_Conseption = _FS_DateTime_of_Conseption;
        this.set_ItemGroupArray( new ArrayList<Post_Sales_Item_MakeUp>() );
    }

    private static void GetCreateSalesItemObject () {

    }



    public ArrayList<Post_Sales_Item_MakeUp> get_ItemGroupArray () {
        return _ItemGroupArray;
    }

    public void set_ItemGroupArray (ArrayList<Post_Sales_Item_MakeUp> _ItemGroupArray) {
        this._ItemGroupArray = _ItemGroupArray;
    }

    public String get_FS_Post_Details_Text () {
        return _FS_Post_Details_Text;
    }

    public void set_FS_Post_Details_Text (String _FS_Post_Details_Text) {
        this._FS_Post_Details_Text = _FS_Post_Details_Text;
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
    //</editor-fold>

    public byte[] get_FS_ItemHeaderVoiceFileData () {
        return _FS_ItemHeaderVoiceFileDataArray;
    }

    public void set_FS_ItemHeaderVoiceFileData (byte[] _FS_ItemHeaderVoiceFileDate) {
        this._FS_ItemHeaderVoiceFileDataArray = _FS_ItemHeaderVoiceFileDate;
    }

}


