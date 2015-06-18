package doop.aa_schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ViewSchedule extends Fragment { //http://architects.dzone.com/articles/android-tutorial-using
    MyPageAdapter pageAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_view_schedule,container,false);
        List<Fragment> fragments = getFragments();
        pageAdapter = new MyPageAdapter(getActivity().getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager) view.findViewById(R.id.viewpager);

        pager.setAdapter(pageAdapter);

        return view;
    }

    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<>();

        fList.add(DayFragment.newInstance("Fragment 1"));
        fList.add(DayFragment.newInstance("Fragment 2"));
        fList.add(DayFragment.newInstance("Fragment 3"));

        return fList;
    }
}