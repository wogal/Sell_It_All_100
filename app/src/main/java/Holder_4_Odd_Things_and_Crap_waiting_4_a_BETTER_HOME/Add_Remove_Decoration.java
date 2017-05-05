package Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

/**
 * Created by wogal on 5/5/2017.
 */

public class Add_Remove_Decoration {
    public static SpannableString Add_Decoration_Post_details () {
            String mStr";
        mStr = System_Shared_Constants.const_banner_post_details;
        int strLen;
        strLen = mStr.length();
        SpannableString WordSpan = new SpannableString( mStr );
        WordSpan.setSpan( new ForegroundColorSpan( Color.GREEN ), 0, strLen, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
        WordSpan.setSpan( new UnderlineSpan(), 0, strLen, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
        WordSpan.setSpan( new StyleSpan( Typeface.BOLD ), 0, strLen, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
        return WordSpan;
    }

    public static String  Remove_Decoration_Post_details (TextView _TextView) {
        String mStr = "";
        SpannableString WordSpan = new SpannableString( _TextView.getText() );
        mStr = WordSpan.toString().replace( System_Shared_Constants.const_banner_post_details, "" );
    return mStr;
    }


}
