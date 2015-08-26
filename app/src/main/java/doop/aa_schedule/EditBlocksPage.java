package doop.aa_schedule;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditBlocksPage extends Fragment {
    private static ArrayList<ArrayList<Period>> schedule;
    private static Period otherPeriod = new Period(0,0,"Other",8,false);
    CustomMethods customMethods = new CustomMethods();
    private static PauseViewPager mViewPager;
    private static FragmentActivity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mActivity = getActivity();
        View v = inflater.inflate(R.layout.view_day, container, false);

        LinearLayout ll = (LinearLayout) v.findViewById(R.id.day_layout);
        //int padding = getResources().getDimensionPixelSize(R.dimen.view_padding);
        //ll.setPadding(padding / 2, padding, padding / 2, 0);

        //TextView label = (TextView) v.findViewById(R.id.dayText);
        //((MainActivity) getActivity()).getSupportActionBar().setTitle("Edit Blocks");

        LinearLayout.LayoutParams params;

        View blockView;
        TextView blockMain;
        Period p;
        for (int block=0; block<9; block++) {
            p=findPeriod(block,schedule.get(9));
            if(p==null || block==8) {
                //Log.d("EditBlocksPage 046", otherPeriod.getColor()+"");
                otherPeriod.setColor(getOtherColor(block));
                //Log.d("EditBlocksPage 048", otherPeriod.getColor() + "");

                p=otherPeriod;
            }
            blockView = inflater.inflate(R.layout.view_period, container, false);
            blockMain = (TextView) blockView.findViewById(R.id.per_main_text);

            blockMain.setText(p.getMainText());
            blockView.setBackgroundColor(customMethods.getPerColor(p));

            blockView.setOnClickListener(new PeriodOnClickListener(p, 0, block) {
                @Override
                public void onClick(View v) {
                    mViewPager.setPagingAllowed(false); //pause ViewPager

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    EditBlockFragment ebf = new EditBlockFragment();
                    ebf.sendArgs(period, perIndex, mViewPager);

                    ft.add(R.id.container, ebf, "");
                    ft.remove(EditBlocksPage.this);
                    ft.addToBackStack(null);
                    ft.commit();
                    //ft.replace(R.id.container,epf).commit();
                }
            });

            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
            ll.addView(blockView, params);

        }
        return v;
    }

    public Period findPeriod(int i, ArrayList<Period> day){
        for(Period p:day){
            if(p.getBlock()==i) return p;
        }
        //Log.e("EditBlocksPage 497", "no period of block "+i+" found");
        return null;
    }
    public int getOtherColor(int block){
        for(ArrayList<Period> day:schedule){
            for(Period p:day){
                //Log.d("EditBlocksPage 093", p.getColor()+" "+p.getBlock()+" "+p.getClassName());
                if(p.getBlock()==block)
                    return customMethods.getDefaultPerColor(p);
            }
        }
        return customMethods.getPerColor(otherPeriod);
    }

    public void sendArgs(ArrayList<ArrayList<Period>> schedule, PauseViewPager vp){
        this.schedule = schedule;
        mViewPager = vp;
    }

    public static void updateBlock(int blockIndex, Bundle b, Resources r){
        CustomMethods customMethods = new CustomMethods();
        String newClass = b.getString(r.getString(R.string.bdl_name),null);
        String newRoom = b.getString(r.getString(R.string.bdl_room),null);
        boolean newFree = b.getBoolean(r.getString(R.string.bdl_free));
        boolean freeChanged = b.getBoolean(r.getString(R.string.bdl_free_changed));
        boolean setColor = b.getBoolean(r.getString(R.string.bdl_set_color));
        int newColor = b.getInt(r.getString(R.string.bdl_color));

        if(blockIndex==8 && setColor)
            otherPeriod.setColor(newColor);

        for(ArrayList<Period> day:schedule){
            for (Period p:day){
                if(p.getBlock()==blockIndex){
                    if(!(!freeChanged && !newFree && p.isFree())){ //The only case where R/CN shouldn't be changed is when the free state stayed on class and the updating period is free
                        if(newClass!=null)
                            p.setClassName(newClass);
                        if(newRoom!=null)
                            p.setRoom(newRoom);
                        if(setColor)
                            p.setColor(newColor);
                    } else {
                        if(setColor)
                            p.setColor(customMethods.paleColor(newColor));
                    }
                    if(freeChanged)
                        p.setIsFree(newFree);
                }
            }
        }

        MainActivity daddy = (MainActivity) mActivity;
        daddy.updateSchedule(schedule);
        if (!customMethods.saveSchedule(schedule, mActivity, "EBP 146")) {
            Log.e("EditBlocksPage 142", "Error in saving sharedpreference");
            Toast.makeText(mActivity, "Unable to save changes. Please try again and report this bug. Sorry :(", Toast.LENGTH_LONG).show();
        }

        FragmentManager fm = mActivity.getSupportFragmentManager(); //reload edit fragment to update day 1
        FragmentTransaction ft = fm.beginTransaction();
        EditSchedule es = new EditSchedule();
        es.sendArgs(schedule);
        ft.replace(R.id.container, es).commit();
    }
}