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
    //public ArrayList<ArrayList<Period>> schedule;
    private ArrayList<Period> day; //you could make this static and use newInstance; just bundle your data (ArrayList needs to be parcelable)
    private int cycleDay;
    
    /*public static DayFragment newInstance(ArrayList<ArrayList<Period>> scheduleArr, int dayNum) {
        DayFragment f = new DayFragment();
        /*Bundle bdl = new Bundle(1);
        bdl.putArr(DAY, day);
        f.setArguments(bdl);* /

        //day = copyDay(_day, r);
        cycleDay=_i;
        return f;
    }*/
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //ArrayList<Period> day = getArguments().getParcelableArrayList(DAY);
        View v = inflater.inflate(R.layout.view_day, container, false);
        LinearLayout ll = (LinearLayout) v.findViewById(R.id.day_layout);

        LinearLayout.LayoutParams params;
        View periodView;
        TextView perStart;
        TextView perEnd;
        TextView perMain;

        for(Period p:day){
            periodView = inflater.inflate(R.layout.view_period, container, false);
            perStart = (TextView) periodView.findViewById(R.id.per_start_text);
            perEnd = (TextView) periodView.findViewById(R.id.per_end_text);
            perMain = (TextView) periodView.findViewById(R.id.per_main_text);
            perStart.setText(p.getStartString());
            perEnd.setText(p.getEndString());
            perMain.setText(p.getMainText());
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,p.getLength());
            ll.addView(periodView, params);
        }
        return v;
    }

    public static ArrayList<Period> copyDay (ArrayList<Period> _day, Resources r){
        ArrayList<Period> day = new ArrayList<>();
        for(Period p:_day){
            day.add(new Period(p.toJSON(r), r));
        }
        return day;
    }

    public void setValues(int _cycleDay, ArrayList<Period> _day){
        cycleDay = _cycleDay;
        day = _day;
    }

    public String toString(){
        return cycleDay+", "+day.toString();
    }

}