package doop.aa_schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditSchedule extends Fragment { // http://architects.dzone.com/articles/android-tutorial-using
    MyPageAdapter pageAdapter;
    ArrayList<ArrayList<Period>> scheduleArray;
    ArrayList<ArrayList<Period>> newSchedule;
    CustomMethods customMethods = new CustomMethods();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_edit_schedule, container, false);
        List<Fragment> fragments = getFragments();
        pageAdapter = new MyPageAdapter(getActivity().getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager) view.findViewById(R.id.edit_pager);
        pager.setAdapter(pageAdapter);

        Button save = (Button) view.findViewById(R.id.save_edit);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleArray = customMethods.copySchedule(newSchedule);
                MainActivity daddy = (MainActivity) getActivity();
                daddy.updateSchedule(scheduleArray);
                if (customMethods.saveSchedule(newSchedule, getActivity())) {
                    Toast.makeText(getActivity(), "Save successful", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("EditSchedule 585", "Error in saving sharedpreference");
                    Toast.makeText(getActivity(), "Unable to save changes. Please try again and submit feedback. Sorry :(", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button cancel = (Button) view.findViewById(R.id.cancel_edit);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("EditSchedule 486", backupSchedule.toString());

                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                EditSchedule es = new EditSchedule();
                es.sendArgs(scheduleArray);
                ft.replace(R.id.container, es).commit();
            }
        });

        return view;
    }

    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<>();

        //Add first page

        EditDayViewFragment edf = new EditDayViewFragment();
        edf.setSchedule(newSchedule);
        for(int i=0;i<newSchedule.size();i++){ //add a page for each cycle day
            fList.add(edf.newInstance(i)); //i: 0=day 1, 1=day 2, ... , 9=day 0
            //Log.d("ViewSchedule 193", fList.toString());
        }
        return fList;
    }

    public void sendArgs(ArrayList<ArrayList<Period>> schArr) {
        scheduleArray = schArr;
        newSchedule = customMethods.copySchedule(schArr);
        if(schArr==null) Log.e("EditSchedule 283","scheduleArray is null");
    }
}