package doop.aa_schedule;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
import android.widget.Toast;

import java.util.ArrayList;

public class EditDayViewFragment extends Fragment {
    private static final String DAY_NUM = "DAY_NUM";
    private static ArrayList<ArrayList<Period>> schedule;
    private static CustomMethods customMethods = new CustomMethods();
    private static PauseViewPager mViewPager;
    private static FragmentActivity mActivity;

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
        mActivity = getActivity();
        //ArrayList<Period> day = getArguments().getParcelableArrayList(DAY_SCHEDULE);
        int dayNum = getArguments().getInt(DAY_NUM); //0=day 1, 1=day 2, ... , 9=day 0

        //Log.d("EditBlocksPage 954", mViewPager.getChildCount() + "");

        View v = inflater.inflate(R.layout.view_day, container, false);

        ArrayList<Period> day = schedule.get(dayNum); //0=day 1, ... , 8=day 9, 9=day 0
        LinearLayout ll = (LinearLayout) v.findViewById(R.id.day_layout);
        //int padding = getResources().getDimensionPixelSize(R.dimen.view_padding);
        //ll.setPadding(padding/2, padding, padding/2, 0);

        //TextView label = (TextView) v.findViewById(R.id.dayText);
        //getActivity().setTitle(getResources().getString(R.string.edit_day_label)+" "+(dayNum+1)%10); //Edit Day n

        LinearLayout.LayoutParams params;
        View periodView;
        TextView perTime;
        //TextView perStart;
        //TextView perEnd;
        TextView perMain;

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        Period p;
        for (int i=0; i<day.size(); i++) {
            p = day.get(i);
            periodView = inflater.inflate(R.layout.view_period, container, false);
            perTime = (TextView) periodView.findViewById(R.id.per_time_text);
            //perStart = (TextView) periodView.findViewById(R.id.per_start_text);
            //perEnd = (TextView) periodView.findViewById(R.id.per_end_text);
            perMain = (TextView) periodView.findViewById(R.id.per_main_text);
            perTime.setText(p.getTimeString(getActivity()));
            //perStart.setText(p.getStartString());
            //perEnd.setText(p.getEndString());
            String perMainText = p.getMainText(false);
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


            periodView.setOnClickListener(new PeriodOnClickListener(p, dayNum, i) {
                public void onClick(View v) {
                    mViewPager.setPagingAllowed(false);

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    EditPeriodFragment epf = new EditPeriodFragment();
                    epf.sendArgs(period, dayIndex, perIndex, mViewPager);

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

    public void sendArgs(ArrayList<ArrayList<Period>> _schedule, PauseViewPager vp){
        this.schedule = _schedule;
        this.mViewPager = vp;
    }

    public static void updatePeriod(int dayIndex, int perIndex, Bundle b, Context c){
        Resources r = c.getResources();
        //Log.d("EditDayViewFragment 164", "updating period "+dayIndex+" "+perIndex+" bundle: "+b.toString());
        ArrayList <Period> day = schedule.get(dayIndex);
        Period p = day.get(perIndex);

        String newClass = b.getString(r.getString(R.string.bdl_name));
        String newRoom = b.getString(r.getString(R.string.bdl_room));
        int newStart = b.getInt(r.getString(R.string.bdl_start));
        int newEnd = b.getInt(r.getString(R.string.bdl_end));
        boolean newFree = b.getBoolean(r.getString(R.string.bdl_free));
        boolean setColor = b.getBoolean(r.getString(R.string.bdl_set_color));
        int newColor = b.getInt(r.getString(R.string.bdl_color));

        //Set class name, room, type, color
        p.setClassName(newClass);
        if(newRoom!=null)
            p.setRoom(newRoom);
        p.setIsFree(newFree);
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
            Period fillerFree = new Period(p.getStart(),newStart, r.getString(R.string.free_name), p.getBlock(), true);
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
            Period fillerFree = new Period(newEnd, p.getEnd(), r.getString(R.string.free_name), p.getBlock(), true);
            day.add(perIndex+1, fillerFree);
            p.setEnd(newEnd);
        }

        //Delete all periods smaller than minimum
        for(int i=0; i<day.size(); i++){
            if(day.get(i).getLength()<customMethods.getMinPerLength(c)){
                day.remove(i);
                i--;
            }
        }

        MainActivity daddy = (MainActivity) mActivity;
        daddy.updateSchedule(schedule);
        if (!customMethods.saveSchedule(schedule, mActivity,"EDVF 398")) {
            Log.e("EditDayViewFragment 183", "Error in saving sharedpreference");
            Toast.makeText(mActivity, "Unable to save changes. Please try again and report this bug. Sorry :(", Toast.LENGTH_LONG).show();
        }
    }
}