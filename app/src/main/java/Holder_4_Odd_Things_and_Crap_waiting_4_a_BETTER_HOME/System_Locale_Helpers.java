package Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME;

/**
 * Created by wogal on 5/4/2017.
 */

public class System_Locale_Helpers {


    // only a temporary  cheat ( promise )
    public static String GetLocaleCurrency_char(){
        return "$";
    }

    public static Float Get_Float_From_String(String _inString){
        String mSubStr;
        String mCostStr;
        Float mFloat;
        mCostStr = _inString;
        mSubStr = mCostStr.substring( mCostStr.lastIndexOf( System_Locale_Helpers.GetLocaleCurrency_char() ) + 1 );
        mFloat =  Float.parseFloat( mSubStr );
        return mFloat;
    }


    /**
     * will correctly format ( i hope ) currency sting for giving locale
     *
     * @param _PostCost
     * @return formated string
     */
    public static String Format_PostCost_String (float _PostCost) {
        String Str_Currency;
        Str_Currency = GetLocaleCurrency_char();  // only a temporary cheat ( promise )
        Str_Currency = String.format( "%s %3.2f", Str_Currency, _PostCost );
        return Str_Currency;
    }
}
