package doop.aa_schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ViewSchedule extends Fragment { // http://architects.dzone.com/articles/android-tutorial-using
    MyPageAdapter pageAdapter;
    ArrayList<ArrayList<Period>> scheduleArray;
    ArrayList<Integer> dayList; //0=day 0, 1=day 1, ... , -1=no school
    int currentDay;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_view_schedule,container,false);
        List<Fragment> fragments = getFragments();
        pageAdapter = new MyPageAdapter(getActivity().getSupportFragmentManager(), fragments);
        //pageAdapter = new MyPageAdapter(getActivity().getSupportFragmentManager());
        ViewPager pager = (ViewPager) view.findViewById(R.id.viewpager);

        pager.setAdapter(pageAdapter);
        pager.setCurrentItem(currentDay);

        return view;
    }

    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<>();

        for(Integer i:dayList){
            DayFragment df = new DayFragment();
            df.setSchedule(scheduleArray);
            fList.add(df.newInstance(i)); //0=day 0, 1=day 1, ... , -1=no school
            //Log.d("ViewSchedule 193", fList.toString());

        }
        return fList;
    }

    public void sendArgs(ArrayList<ArrayList<Period>> schArr, ArrayList<Integer> days, int curDay) {
        scheduleArray = schArr;
        dayList = days; //0=day 0, 1=day 1, ... , -1=no school
        currentDay = curDay;
        if(scheduleArray==null) Log.e("ViewSchedule 343","scheduleArray is null");
        if(dayList==null) Log.e("ViewSchedule 344","dayList is null");
    }
}