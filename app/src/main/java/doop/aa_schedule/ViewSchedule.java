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
    //static final int ITEMS = 180;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_view_schedule,container,false);
        List<Fragment> fragments = getFragments();
        pageAdapter = new MyPageAdapter(getActivity().getSupportFragmentManager(), fragments);
        //pageAdapter = new MyPageAdapter(getActivity().getSupportFragmentManager());
        ViewPager pager = (ViewPager) view.findViewById(R.id.viewpager);

        pager.setAdapter(pageAdapter);

        return view;
    }

    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<>();

        for(int i=0; i<180;i++){
            DayFragment df = new DayFragment();
            df.setSchedule(scheduleArray);
            fList.add(df.newInstance((i+1)%10));
            //Log.d("ViewSchedule 193", fList.toString());

        }
        return fList;
    }

    public void sendScheduleArray(ArrayList<ArrayList<Period>> schArr) {
        scheduleArray = schArr;
        if(scheduleArray==null) Log.e("ViewSchedule 343","scheduleArray is null");
        //if(pageAdapter==null) Log.e("ViewSchedule 344","pageAdapter is null");
        //pageAdapter.startUpdate((ViewGroup) getView());
        //pageAdapter.setData(getFragments());

    }
}