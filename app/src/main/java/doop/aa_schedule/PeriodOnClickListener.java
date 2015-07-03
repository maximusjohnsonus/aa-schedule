package doop.aa_schedule;

import android.view.View;

public class PeriodOnClickListener implements View.OnClickListener {
    Period period;
    int dayIndex;
    int perIndex;

    public PeriodOnClickListener(Period p, int dayIndex, int periodIndex){
        period = p;
        this.dayIndex = dayIndex;
        perIndex = periodIndex;
    }

    @Override
    public void onClick(View v) {

    }
}
