package doop.aa_schedule;

import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DayFragment extends Fragment {
    private static final String DAY_NUM = "DAY_NUM";
    private static final String DATE = "DATE";
    private static final String BORDER = "BORDER";
    private static final String DAY_INDEX = "DAY_INDEX";
    private static ArrayList<ArrayList<Period>> schedule;
    CustomMethods customMethods = new CustomMethods();

    //private static String[] daysOfWeek = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

    public static DayFragment newInstance(int dayNum, /*Calendar cal,*/ boolean border, int index) {
        DayFragment f = new DayFragment();
        Bundle bdl = new Bundle();
        bdl.putInt(DAY_NUM, dayNum);
        //int[] date={cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),cal.get(Calendar.DAY_OF_WEEK)};
        //bdl.putIntArray(DATE,date);
        bdl.putBoolean(BORDER, border);
        bdl.putInt(DAY_INDEX, index);
        f.setArguments(bdl);

        //day = copyDay(_day, r);
        return f;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Log.d("DayFragment 513", savedInstanceState!=null ? savedInstanceState.toString() : "null");
        //ArrayList<Period> day = getArguments().getParcelableArrayList(DAY_SCHEDULE);

        final int dayNum = getArguments().getInt(DAY_NUM); //0=day 0, 1=day 1, ... , -1=no school
        final int index = getArguments().getInt(DAY_INDEX);
        final HashMap notes = CustomMethods.getDayNotes(index, getActivity());

        //int[] date = getArguments().getIntArray(DATE);
        boolean border = getArguments().getBoolean(BORDER);

        View v = inflater.inflate(R.layout.view_day, container, false);
        if(border && customMethods.showHighlight(getActivity()))
            v.setBackgroundResource(R.drawable.border);

        if(dayNum==-1){ //no school
            //TextView label = (TextView) v.findViewById(R.id.dayText);
            //getActivity().setTitle(daysOfWeek[date[2] - 1] + ", " + (date[0] + 1) + "/" + date[1]);
            return v;
        } else {
            ArrayList<Period> day = schedule.get((dayNum+9)%10); //converted to index: 0=day 1, ... , 8=day 9, 9=day 0
            LinearLayout ll = (LinearLayout) v.findViewById(R.id.day_layout);
            //TextView label = (TextView) v.findViewById(R.id.dayText);
            //label.setText(daysOfWeek[date[2]-1]+", "+(date[0]+1)+"/"+date[1]+", Day "+dayNum);
            LinearLayout.LayoutParams params;
            //View periodView;
            TextView perTime;
            //TextView perStart;
            //TextView perEnd;
            TextView perMain;

            Display display = getActivity().getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            int i=-1;
            for (final Period p:day) {
                i++;
                final View periodView = inflater.inflate(R.layout.view_period, container, false);
                perTime = (TextView) periodView.findViewById(R.id.per_time_text);
                perMain = (TextView) periodView.findViewById(R.id.per_main_text);
                if(notes!=null)
                    p.setNotes((String) notes.get(String.valueOf(i)));
                else
                    p.setNotes(null);
                perTime.setText(p.getTimeString(getActivity()));
                String perMainText = p.getMainText(CustomMethods.showNotes(getActivity()));
                perMain.setText(perMainText);

                Rect bounds = new Rect();
                Paint textPaint = perMain.getPaint();
                textPaint.getTextBounds(perMainText, 0, perMainText.length(), bounds);
                if(bounds.width()/2 > size.x/2 - 80f * getResources().getDisplayMetrics().densityDpi/160f || customMethods.alignLeft(getActivity())) {
                    //if text box reaches time box, change left edge to be flush with time's right, set left align
                    perMain.setGravity(0);
                    RelativeLayout.LayoutParams perParams = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    perParams.addRule(RelativeLayout.RIGHT_OF, R.id.per_time_text);
                    perParams.addRule(RelativeLayout.CENTER_VERTICAL, 1);
                    perMain.setLayoutParams(perParams);
                }

                periodView.setBackgroundColor(customMethods.getPerColor(p));
                /*if(p.hasColor()){
                    periodView.setBackgroundColor(p.getColor());
                } else if(p.getType()!=2)
                    periodView.setBackgroundColor(colors[p.getBlock()]);
                else {
                    CustomMethods customMethods = new CustomMethods();
                    periodView.setBackgroundColor(customMethods.paleColor(colors[p.getBlock()]));
                }*/

                if(CustomMethods.showNotes(getActivity())) {
                    final int notesPerIndex = i;
                    //Adds notes to period.
                    periodView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            FragmentManager fm = getActivity().getSupportFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            PeriodNotes perNotes = new PeriodNotes();
                            //perNotes.sendArgs((notes!=null ? (String) notes.get(String.valueOf(notesPerIndex)):""), index, notesPerIndex, p, periodView);
                            perNotes.sendArgs(p, index, notesPerIndex, periodView);
                            ft.add(R.id.container, perNotes, "");
                            //ft.remove(DayFragment.this);
                            ft.addToBackStack(null);
                            ft.commit();
                        }
                    });
                }

                params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, p.getLength());
                ll.addView(periodView, params);
            }
            return v;
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d("DF 146", "pausing");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d("DF 151", "onResume");
    }

    public void updateNotes(int dayIndex, int perIndex, String newNotes){
        schedule.get(dayIndex).get(perIndex).setNotes(newNotes);
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