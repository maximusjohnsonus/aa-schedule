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

//TODO: make big processes (like saving) show a loading icon (or run in background?)
public class EditSchedule extends Fragment { // http://architects.dzone.com/articles/android-tutorial-using
    MyPageAdapter pageAdapter;
    ArrayList<ArrayList<Period>> scheduleArray;
    //ArrayList<ArrayList<Period>> newSchedule;
    PauseViewPager pager;
    int oldPage;
    CustomMethods customMethods = new CustomMethods();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_edit_schedule, container, false);
        pager = (PauseViewPager) view.findViewById(R.id.edit_pager);
        List<Fragment> fragments = getFragments();
        pageAdapter = new MyPageAdapter(getActivity().getSupportFragmentManager(), fragments);
        pager.setAdapter(pageAdapter);

        oldPage=-1;
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int curPage = (position + (positionOffset > .5 ? 1 : 0));
                if (curPage != oldPage) {
                    changeTitle(curPage);
                    oldPage = curPage;
                }
            }

            public void changeTitle(int curPage) {
                if(curPage==0)
                    ((MainActivity) getActivity()).getSupportActionBar().setTitle("Edit Blocks");
                else if(curPage==11)
                    ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.get_sch_title);
                else
                    ((MainActivity) getActivity()).getSupportActionBar().setTitle("Edit Day "+curPage%10);

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        /*Button save = (Button) view.findViewById(R.id.save_edit);
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
                    Toast.makeText(getActivity(), "Unable to save changes. Please try again and report this bug. Sorry :(", Toast.LENGTH_LONG).show();
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
                ft.replace(R.id.container, es).commit(); //TODO: refresh view instead of totally recreating it (or make it default to old page)
            }
        });*/

        return view;
    }

    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<>();

        EditBlocksPage ebp = new EditBlocksPage();
        ebp.sendArgs(scheduleArray, pager); //change scheduleArray to newSchedule to go back to save/cancel button
        fList.add(ebp);

        EditDayViewFragment edf = new EditDayViewFragment();
        edf.sendArgs(scheduleArray, pager); //change scheduleArray to newSchedule to go back to save/cancel button
        for(int i=0;i<scheduleArray.size();i++){ //add a page for each cycle day    //change scheduleArray to newSchedule to go back to save/cancel button
            fList.add(edf.newInstance(i)); //i: 0=day 1, 1=day 2, ... , 9=day 0
            //Log.d("ViewSchedule 193", fList.toString());
        }

        fList.add(new GetScheduleFragment());

        return fList;
    }

    public void sendArgs(ArrayList<ArrayList<Period>> schArr) {
        scheduleArray = schArr;
        //newSchedule = customMethods.copySchedule(schArr);
        if(schArr==null) Log.e("EditSchedule 283","scheduleArray is null");
    }
}