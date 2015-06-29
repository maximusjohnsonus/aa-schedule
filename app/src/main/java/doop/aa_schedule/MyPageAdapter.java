package doop.aa_schedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

class MyPageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    public MyPageAdapter(FragmentManager fm, List<Fragment> _fragments) {
        super(fm);
        fragments = _fragments;
    }

    public MyPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(List<Fragment> _fragments){
        fragments = _fragments;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }
}