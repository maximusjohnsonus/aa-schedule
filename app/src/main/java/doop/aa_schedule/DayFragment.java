package doop.aa_schedule;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class DayFragment extends Fragment {
    private static final String DAY_NUM = "DAY_NUM";
    private static final String DATE = "DATE";
    private static final String BORDER = "BORDER";
    private static final String DAY_SCHEDULE = "DAY_SCHEDULE";
    private static ArrayList<ArrayList<Period>> schedule;

    //TODO: make these stored as sharedPreferences (or other)
    private static int[] colors = {Color.RED, Color.rgb(255, 128, 0), Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.rgb(128, 0, 128), Color.rgb(0,150,0), Color.rgb(128, 64, 32), Color.rgb(32, 32, 32)};
    private static int freeColor = Color.LTGRAY;
    private static String[] daysOfWeek = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

    public static DayFragment newInstance(int dayNum, Calendar cal, boolean border) {
        DayFragment f = new DayFragment();
        Bundle bdl = new Bundle();
        //bdl.putParcelableArrayList(DAY_SCHEDULE,daySchedule);
        bdl.putInt(DAY_NUM, dayNum);
        int[] date={cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),cal.get(Calendar.DAY_OF_WEEK)};
        bdl.putIntArray(DATE,date);
        bdl.putBoolean(BORDER,border);
        f.setArguments(bdl);

        //day = copyDay(_day, r);
        return f;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Log.d("DayFragment 513", savedInstanceState!=null ? savedInstanceState.toString() : "null");
        //ArrayList<Period> day = getArguments().getParcelableArrayList(DAY_SCHEDULE);
        int dayNum = getArguments().getInt(DAY_NUM); //0=day 0, 1=day 1, ... , -1=no school
        int[] date = getArguments().getIntArray(DATE);
        boolean border = getArguments().getBoolean(BORDER);

        View v = inflater.inflate(R.layout.view_day, container, false);
        if(border)
            v.setBackgroundResource(R.drawable.border);
        if(dayNum==-1){ //no school
            TextView label = (TextView) v.findViewById(R.id.dayText);
            label.setText(daysOfWeek[date[2]-1]+", "+(date[0]+1)+"/"+date[1]);
            return v;
        } else {
            ArrayList<Period> day = schedule.get((dayNum+9)%10); //converted to index: 0=day 1, ... , 8=day 9, 9=day 0
            LinearLayout ll = (LinearLayout) v.findViewById(R.id.day_layout);
            TextView label = (TextView) v.findViewById(R.id.dayText);
            label.setText(daysOfWeek[date[2]-1]+", "+(date[0]+1)+"/"+date[1]+", Day "+dayNum);

            LinearLayout.LayoutParams params;
            View periodView;
            TextView perTime;
            //TextView perStart;
            //TextView perEnd;
            TextView perMain;

            for (Period p : day) {
                periodView = inflater.inflate(R.layout.view_period, container, false);
                perTime = (TextView) periodView.findViewById(R.id.per_time_text);
                //perStart = (TextView) periodView.findViewById(R.id.per_start_text);
                //perEnd = (TextView) periodView.findViewById(R.id.per_end_text);
                perMain = (TextView) periodView.findViewById(R.id.per_main_text);
                perTime.setText(p.getTimeString());
                //perStart.setText(p.getStartString());
                //perEnd.setText(p.getEndString());
                perMain.setText(p.getMainText());
                if(p.hasColor()){
                    periodView.setBackgroundColor(p.getColor());
                } else if(p.getType()!=2)
                    periodView.setBackgroundColor(colors[p.getBlock()]);
                else {
                    CustomMethods customMethods = new CustomMethods();
                    periodView.setBackgroundColor(customMethods.paleColor(colors[p.getBlock()]));
                }
                    //periodView.setBackgroundColor(freeColor);
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