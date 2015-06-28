package doop.aa_schedule;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class DayFragment extends Fragment {
    private static final String DAY_NUM = "DAY_NUM";
    private static final String DAY_SCHEDULE = "DAY_SCHEDULE";
    private static ArrayList<ArrayList<Period>> schedule;
    
    public static DayFragment newInstance(int dayNum) {
        DayFragment f = new DayFragment();
        Bundle bdl = new Bundle(1);
        //bdl.putParcelableArrayList(DAY_SCHEDULE,daySchedule);
        bdl.putInt(DAY_NUM,dayNum);
        f.setArguments(bdl);

        //day = copyDay(_day, r);
        return f;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Log.d("DayFragment 513", savedInstanceState!=null ? savedInstanceState.toString() : "null");
        //ArrayList<Period> day = getArguments().getParcelableArrayList(DAY_SCHEDULE);
        int dayNum = getArguments().getInt(DAY_NUM); //0=day 0, 1=day 1, ... , -1=no school
        if(dayNum==-1){ //no school
            View v = inflater.inflate(R.layout.view_day, container, false);
            TextView label = (TextView) v.findViewById(R.id.dayText);
            label.setText("sjfas NO SCHOOL!"); //Do NOT use this text in final - use xml resource

            return v;
        } else {
            ArrayList<Period> day = schedule.get((dayNum+9)%10); //converted to index: 0=day 1, ... , 8=day 9, 9=day 0

            View v = inflater.inflate(R.layout.view_day, container, false);
            LinearLayout ll = (LinearLayout) v.findViewById(R.id.day_layout);
            TextView label = (TextView) v.findViewById(R.id.dayText);
            label.setText("sjfas Day " + dayNum); //Do NOT use this text in final - use xml resource

            LinearLayout.LayoutParams params;
            View periodView;
            TextView perStart;
            TextView perEnd;
            TextView perMain;

            for (Period p : day) {
                periodView = inflater.inflate(R.layout.view_period, container, false);
                perStart = (TextView) periodView.findViewById(R.id.per_start_text);
                perEnd = (TextView) periodView.findViewById(R.id.per_end_text);
                perMain = (TextView) periodView.findViewById(R.id.per_main_text);
                perStart.setText(p.getStartString());
                perEnd.setText(p.getEndString());
                perMain.setText(p.getMainText());
                params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, p.getLength());
                ll.addView(periodView, params);
            }
            return v;
        }
    }

    public static ArrayList<Period> copyDay (ArrayList<Period> _day, Resources r){
        ArrayList<Period> day = new ArrayList<>();
        for(Period p:_day){
            day.add(new Period(p.toJSON(r), r));
        }
        return day;
    }

    public void setSchedule(ArrayList<ArrayList<Period>> _schedule){
        schedule = _schedule;
    }

    /*public void setValues(int _cycleDay, ArrayList<Period> _day){
        cycleDay = _cycleDay;
        day = _day;
    }

    public String toString(){
        return cycleDay+", "+day.toString();
    }*/

}