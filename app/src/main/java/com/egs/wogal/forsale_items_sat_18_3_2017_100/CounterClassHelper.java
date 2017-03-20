package com.egs.wogal.forsale_items_sat_18_3_2017_100;


import android.os.CountDownTimer;

// Wogals Counter
public abstract class CounterClassHelper extends CountDownTimer {


    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public CounterClassHelper (long millisInFuture, long countDownInterval) {
        super( millisInFuture, countDownInterval );
    }
}
