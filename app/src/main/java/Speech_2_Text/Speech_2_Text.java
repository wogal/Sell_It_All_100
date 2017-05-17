package Speech_2_Text;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by wogal on 5/15/2017.
 */

public class Speech_2_Text extends AppCompatActivity {
    private final int SPEECH_RECOGNITION_CODE = 1;
    private Context mContext;

    public Speech_2_Text (Context _mContext) {
        this.mContext = _mContext;
        this.Start_Speech_Text_1();
    }


    public void Start_Speech_Text_1 () {
        String mStr = "";


        Intent intent = new Intent( RecognizerIntent.ACTION_RECOGNIZE_SPEECH );
        intent.putExtra( RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault() );
        intent.putExtra( RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM );
        intent.putExtra( RecognizerIntent.EXTRA_PROMPT,
                "Speak something..." );
        try {
            startActivityForResult( intent, SPEECH_RECOGNITION_CODE );
        } catch (ActivityNotFoundException a) {
            Toast.makeText( getApplicationContext(),
                    "Sorry! Speech recognition is not supported in this device.",
                    Toast.LENGTH_SHORT ).show();
        }


    }


    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        switch (requestCode) {
            case SPEECH_RECOGNITION_CODE: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> text = data.getStringArrayListExtra( RecognizerIntent.EXTRA_RESULTS );

                    //     mTxtV_text.setText( text.get( 0 ) );
                    int a;

                    a = text.get( 0 ).length();

                }
                break;
            }
        }
    }


}
