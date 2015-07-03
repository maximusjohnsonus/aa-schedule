package doop.aa_schedule;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class EditDayViewFragment extends Fragment {
    private static final String DAY_NUM = "DAY_NUM";
    private static ArrayList<ArrayList<Period>> schedule;

    //TODO: make these stored as sharedPreferences (or other)
    private static int[] colors = {Color.RED, Color.rgb(255, 128, 0), Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.rgb(128, 0, 128), Color.rgb(0,150,0), Color.rgb(128, 64, 32), Color.rgb(32, 32, 32)};
    private static int freeColor = Color.LTGRAY;

    public static EditDayViewFragment newInstance(int dayNum) {
        EditDayViewFragment f = new EditDayViewFragment();
        Bundle bdl = new Bundle();
        //bdl.putParcelableArrayList(DAY_SCHEDULE,daySchedule);
        bdl.putInt(DAY_NUM, dayNum);
        f.setArguments(bdl);

        //day = copyDay(_day, r);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //ArrayList<Period> day = getArguments().getParcelableArrayList(DAY_SCHEDULE);
        int dayNum = getArguments().getInt(DAY_NUM); //0=day 1, 1=day 2, ... , 9=day 0

        View v = inflater.inflate(R.layout.view_day, container, false);

        ArrayList<Period> day = schedule.get(dayNum); //0=day 1, ... , 8=day 9, 9=day 0
        LinearLayout ll = (LinearLayout) v.findViewById(R.id.day_layout);
        int padding = getResources().getDimensionPixelSize(R.dimen.view_padding);
        ll.setPadding(padding/2, padding, padding/2, 0);

        TextView label = (TextView) v.findViewById(R.id.dayText);
        label.setText("Day "+(dayNum+1)%10);

        LinearLayout.LayoutParams params;
        View periodView;
        TextView perTime;
        //TextView perStart;
        //TextView perEnd;
        TextView perMain;

        Period p;
        for (int i=0; i<day.size(); i++) {
            p = day.get(i);
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
            else{
                CustomMethods customMethods = new CustomMethods();
                periodView.setBackgroundColor(customMethods.paleColor(colors[p.getBlock()]));
            }

            periodView.setOnClickListener(new PeriodOnClickListener(p, dayNum, i) {
                public void onClick(View v) {
                    //Log.i("myApp", "newsMenu clicked!");
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    EditPeriodFragment epf = new EditPeriodFragment();
                    epf.sendArgs(period, dayIndex, perIndex);

                    ft.add(R.id.container, epf, "");
                    ft.remove(EditDayViewFragment.this);
                    ft.addToBackStack(null);
                    ft.commit();
                    //ft.replace(R.id.container,epf).commit();
                }
            });

            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, p.getLength());
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

    public void setSchedule(ArrayList<ArrayList<Period>> _schedule){
        schedule = _schedule;
    }

    public static void updatePeriod(int dayIndex, int perIndex, Bundle b, Resources r){
        Log.d("EditDayViewFragment 164", "updating period "+dayIndex+" "+perIndex+" bundle: "+b.toString());
        ArrayList <Period> day = schedule.get(dayIndex);
        Period p = day.get(perIndex);

        //Resources r = getResources();
        String newClass = b.getString(r.getString(R.string.bdl_name));
        String newRoom = b.getString(r.getString(R.string.bdl_room));
        int newStart = b.getInt(r.getString(R.string.bdl_start));
        int newEnd = b.getInt(r.getString(R.string.bdl_end));
        int newType = b.getInt(r.getString(R.string.bdl_type));
        boolean setColor = b.getBoolean(r.getString(R.string.bdl_set_color));
        int newColor = b.getInt(r.getString(R.string.bdl_color));

        //Set class name, room, type, color
        p.setClassName(newClass);
        if(!newRoom.equals("")) p.setRoom(newRoom);
        p.setType(newType);
        if(setColor)
            p.setColor(newColor);

        //If start time is earlier than it was, delete all periods completely absorbed by new period, resize any partly covered (do not account for passing)
        if(newStart < p.getStart()){
            for(int i=perIndex-1; i>=0; i--){
                if(day.get(i).getStart()>=newStart) {
                    day.remove(i);
                    perIndex--;
                } else if(day.get(i).getEnd()>newStart){
                    day.get(i).setEnd(newStart);
                }
            }
            p.setStart(newStart);
        } else if (newStart > p.getStart()) {  //If start time is later than it was, fill gap with free
            Period fillerFree = new Period(p.getStart(),newStart, r.getString(R.string.free_name), p.getBlock(), 2);
            day.add(perIndex, fillerFree);
            perIndex++;
            p.setStart(newStart);
        }

        //If end time is later than it was, delete all periods completely absorbed by new period, resize any partly covered (do not account for passing)
        if(newEnd > p.getEnd()){
            for(int i=perIndex+1; i<day.size(); i++){
                if(day.get(i).getEnd()<=newEnd) {
                    day.remove(i);
                    i--;
                } else if(day.get(i).getStart()<newEnd){
                    day.get(i).setStart(newEnd);
                }
            }
            p.setEnd(newEnd);
        } else if (newEnd < p.getEnd()) {  //If end time is earlier than it was, fill gap with free
            Period fillerFree = new Period(newEnd, p.getEnd(), r.getString(R.string.free_name), p.getBlock(), 2);
            day.add(perIndex+1, fillerFree);
            p.setEnd(newEnd);
        }

        //Delete all periods smaller than minimum
        for(int i=0; i<day.size(); i++){
            if(day.get(i).getLength()<r.getInteger(R.integer.minimum_period)){
                day.remove(i);
                i--;
            }
        }

        //TODO: Save? or make that in editschedule
    }

}