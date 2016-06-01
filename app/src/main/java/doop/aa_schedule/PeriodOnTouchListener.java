package doop.aa_schedule;

import android.view.MotionEvent;
import android.view.View;

public class PeriodOnTouchListener implements View.OnTouchListener {
    Period period;
    int dayIndex;
    int perIndex;

    public PeriodOnTouchListener(Period p, int dayIndex, int periodIndex){
        period = p;
        this.dayIndex = dayIndex;
        perIndex = periodIndex;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

}
