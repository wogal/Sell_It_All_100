package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_test_v4 extends AppCompatActivity implements View.OnClickListener {

    private Button mBut_test;
    private TextView m_txt_view;
    private Button mBut_Done;
    private int mCount = 0;
    private CountDownTimer mCountDownTimer = null;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage (Message msg) {
            String _str = "";
            if (msg.arg1 >= 0) {
                _str = "Cnt -> " + msg.arg1;
            } else {
                _str = " All Done";
            }
            m_txt_view.setText( _str );
        }
    };


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout_v4 );

        mBut_test = (Button) findViewById( R.id.But_test_button_v4 );
        mBut_test.setOnClickListener( this );

        m_txt_view = (TextView) findViewById( R.id.txt_test_v4 );
        m_txt_view.setText( "-- --" );

        mBut_Done = (Button) findViewById( R.id.But_Done_v4 );
        mBut_Done.setOnClickListener( this );

    }

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.But_Done_v4: {
                this.finish();
                break;
            }
            case R.id.But_test_button_v4: {
                if (mCountDownTimer == null) {
                    mCount = 1;
                    mCountDownTimer = new CountDownTimer( 10000, 100 ) {
                        @Override
                        public void onTick (long millisUntilFinished) {
                            Message message = Message.obtain();
                            message.arg1 = mCount++;
                            handler.sendMessage( message );
                        }
                        @Override
                        public void onFinish () {
                            Message message = Message.obtain();
                            message.arg1 = -1; // indicate all dose
                            handler.sendMessage( message );
                            mCountDownTimer = null;
                        }
                    }.start();
                }
                break;
            }
        }
    }
}
