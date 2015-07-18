package doop.aa_schedule;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
//import doop.aa_schedule.R;


public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    //Fragment managing the behaviors, interactions and presentation of the navigation drawer.
    private NavigationDrawerFragment mNavigationDrawerFragment;

    //Used to store the last screen title. For use in {@link #restoreActionBar()}.
    private CharSequence mTitle;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ArrayList<ArrayList<Period>> scheduleArray;
    private ArrayList<Integer> dayList; //0=day 0, 1=day 1, ... , -1=no school
    private int currentDay=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().
                findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
        String[] navOptions = { "View Schedule","Edit Schedule","Settings","Help","About","Feedback" };
        //mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, navOptions));

        if(scheduleArray==null)scheduleArray = getSchedule();
        if(dayList==null)dayList = getDayList();
        if(currentDay==-1)currentDay = getCurDay();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        /*FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container,PlaceholderFragment.newInstance(position)).commit();*/


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        /*ft.add(R.id.container, epf, "");
        ft.remove((Fragment) fm.getBackStackEntryAt(fm.getBackStackEntryCount()-1));
        ft.addToBackStack(null);
        ft.commit();*/

        switch (position){ //TODO: back button in each fragment returns to viewSchedule or last visited page
            case 0:
                ViewSchedule vs = new ViewSchedule();
                if(scheduleArray==null) scheduleArray = getSchedule();
                if(dayList==null) dayList = getDayList();
                if(currentDay==-1)currentDay = getCurDay();
                vs.sendArgs(scheduleArray, dayList, currentDay);
                ft.replace(R.id.container,vs).commit();
                break;
            case 1:
                EditSchedule es = new EditSchedule();
                if(scheduleArray==null) scheduleArray = getSchedule();
                es.sendArgs(scheduleArray);
                ft.replace(R.id.container,es).commit();
                break;
            case 2:
                ft.replace(R.id.container,new SettingsFragment()).commit();
                break;
            case 3:
                ft.replace(R.id.container,new Help()).commit();
                break;
            case 4:
                ft.replace(R.id.container,new About()).commit();
                break;
            case 5:
                ft.replace(R.id.container,new Feedback()).commit();
                break;
        }





    }

    public void onSectionAttached(int number) {
        mTitle = getResources().getStringArray(R.array.sidebar_options)[number];
        /*switch (number) { //TODO: make this a thing
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }*/
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateSchedule(ArrayList<ArrayList<Period>> scheduleArray){
        this.scheduleArray = scheduleArray;
    }

    //A placeholder fragment containing a simple view.
    public static class PlaceholderFragment extends Fragment {
        //The fragment argument representing the section number for this fragment
        private static final String ARG_SECTION_NUMBER = "section_number";

        //Returns a new instance of this fragment for the given section number
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_view_schedule, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    public ArrayList<ArrayList<Period>> getSchedule() {
        String prefName = getResources().getString(R.string.pref_storage);
        SharedPreferences sp = getSharedPreferences(prefName, 0);
        String key=getResources().getString(R.string.schedule_JSON);
        String scheduleJSONString=sp.getString(key, "");


        //Log.d("MainActivity 357","JSON string recovered:"+scheduleJSONString);
        boolean rewrite=false; //set to true to rewrite the data
        if(rewrite)
            Log.e("MainActivity 052", "Rewrite is true, revert when finished testing");
        if(scheduleJSONString.length()>0 && !rewrite) {
            try {
                JSONArray scheduleJSONArray = new JSONArray(scheduleJSONString);

                ArrayList<ArrayList<Period>> tempScheduleArray=new ArrayList<>();
                JSONArray dayJSON;
                Period p;
                for(int i=0; i<scheduleJSONArray.length(); i++){ //for day in cycle
                    dayJSON = scheduleJSONArray.getJSONArray(i);
                    //Log.d("MainActivity 735", "Day JSON recovered:" + dayJSON.toString());

                    ArrayList<Period> day=new ArrayList<>();
                    for(int j=0; j<dayJSON.length(); j++){ //for period in day
                        //Log.d("MainActivity 046","Period JSON recovered:"+dayJSON.getJSONObject(j).toString());
                        p = new Period(dayJSON.getJSONObject(j), getResources());
                        //Log.d("MainActivity 829","Period recovered:"+p.toString());
                        day.add(p);
                    }
                    //Log.d("MainActivity 437","Day recovered:"+day.toString());
                    tempScheduleArray.add(day);
                    //Log.d("MainActivity 193","Schedule so far:"+tempScheduleArray);
                }
                //Log.d("MainActivity 476","should have loaded schedule from sharedpreference.");
                return tempScheduleArray;
            } catch(JSONException e){
                Log.e("MainActivity 304","error retrieving JSON array from string. string: "+scheduleJSONString);
                e.printStackTrace();
            }
            return null;

        } else{
            /*TODO: Make dialog:
                load schedule
                make:
                    10-12
                    8-9
                template
            */
            Period AFreeShort = new Period(480, 527, "Free", 0, 2);
            Period AFreeShortCopy = new Period(480, 527, "Free", 0, 2);
            Period AFreeLong = new Period(480, 554, "Free", 0, 2);

            Period BShort1 = new Period(507, 554, "AP Calc AB", 1, 0, "S101");
            Period BShort2 = new Period(534, 581, "AP Calc AB", 1, 0, "S101");
            Period BLong = new Period(561, 635, "AP Calc AB", 1, 0, "S101");
            Period BFree = new Period(480, 507, "Free", 1, 2);

            Period CShort = new Period(588, 635, "Symph Band", 2, 0, "M154");
            Period CFree = new Period(561, 588, "Free", 2, 2);

            Period DFreeShort1 = new Period(642, 689, "Free", 3, 2);
            Period DFreeShort2 = new Period(676, 723, "Free", 3, 2);
            Period DFreeLong = new Period(643, 716, "Free", 3, 2);

            Period EShort1 = new Period(750, 797, "Hums", 4, 0, "M16");
            Period EShort2 = new Period(777, 824, "Hums", 4, 0, "M16");
            Period ELong1 = new Period(750, 824, "Hums", 4, 0, "M16");
            Period ELong2 = new Period(777, 851, "Hums", 4, 0, "M16");
            Period EGroup = new Period(655, 702, "Large Group Hums", 4, 0);
            Period EFree1 = new Period(750, 797, "Free", 4, 2);
            Period EFree2 = new Period(777, 824, "Free", 4, 2);

            Period FShort1 = new Period(804, 851, "AP Physics I", 5, 0, "S212");
            Period FShort2 = new Period(831, 878, "AP Physics I", 5, 0, "S212");
            Period FLong = new Period(804, 878, "AP Physics I", 5, 0, "S212");
            Period FFree = new Period(831, 878, "Free", 5, 2);

            Period GShort = new Period(885, 932, "English IV", 6, 0, "B404");
            Period GLong = new Period(858, 932, "English IV", 6, 0, "B404");
            Period GFree = new Period(885, 932, "Free", 6, 0);

            Period Lunch1 = new Period(696, 743, "Lunch", 7, 1);
            Period Lunch2 = new Period(709, 743, "Lunch", 7, 1);
            Period Lunch3Short = new Period(723, 770, "Lunch", 7, 1);
            Period Lunch3Long = new Period(723, 797, "Lunch", 7, 1);
            Period Lunch4 = new Period(730, 770, "Lunch", 7, 1);
            Period Lunch0 = new Period(696, 770, "Lunch", 7, 1);


            Period Club = new Period(642, 669, "Club", 8, 3); //8 is miscellaneous here
            Period DivAss = new Period(642, 669, "Division Assembly", 8, 3);
            Period Common = new Period(885, 932, "Common Time", 8, 3);
            Period DinkyFree = new Period(635, 655, "Free", 8, 2);


            ArrayList <ArrayList<Period>> tempScheduleArray=new ArrayList<>();
            ArrayList<Period> day1=new ArrayList<>();
            ArrayList<Period> day2=new ArrayList<>();
            ArrayList<Period> day3=new ArrayList<>();
            ArrayList<Period> day4=new ArrayList<>();
            ArrayList<Period> day5=new ArrayList<>();
            ArrayList<Period> day6=new ArrayList<>();
            ArrayList<Period> day7=new ArrayList<>();
            ArrayList<Period> day8=new ArrayList<>();
            ArrayList<Period> day9=new ArrayList<>();
            ArrayList<Period> day0=new ArrayList<>();

            day1.add(AFreeLong); day1.add(BLong); day1.add(DivAss); day1.add(DFreeShort2);
            day1.add(Lunch4); day1.add(EShort2); day1.add(FShort2); day1.add(GShort);

            day2.add(AFreeShortCopy); day2.add(BShort2); day2.add(CShort); day2.add(DFreeLong);
            day2.add(Lunch3Long); day2.add(FLong); day2.add(GShort);

            day3.add(AFreeShort); day3.add(BShort2); day3.add(CShort); day3.add(Club); day3.add(DFreeShort2);
            day3.add(Lunch4); day3.add(EShort2); day3.add(FShort2); day3.add(GFree);

            day4.add(AFreeLong); day4.add(CFree); day4.add(CShort); day4.add(DFreeShort1);
            day4.add(Lunch1); day4.add(ELong1); day4.add(FFree); day4.add(GShort);

            day5.add(AFreeShort); day5.add(BShort2); day5.add(CShort); day5.add(DinkyFree); day5.add(EGroup);
            day5.add(Lunch2); day5.add(EShort1); day5.add(FShort1); day5.add(GLong);

            day6.add(AFreeShort); day6.add(BShort2); day6.add(CShort); day6.add(DFreeLong);
            day6.add(Lunch3Short); day6.add(EFree2); day6.add(FShort2); day6.add(GShort);

            day7.add(BFree); day7.add(BShort1); day7.add(CFree); day7.add(CShort); day7.add(DivAss); day7.add(DFreeShort2);
            day7.add(Lunch4); day7.add(EShort2); day7.add(FShort2); day7.add(GShort);

            day8.add(AFreeShort); day8.add(BShort2); day8.add(CShort); day8.add(DFreeShort1);
            day8.add(Lunch1); day8.add(EFree1); day8.add(FLong); day8.add(Common);

            day9.add(AFreeShort); day9.add(BShort2); day9.add(CShort); day9.add(Club); day9.add(DFreeShort2);
            day9.add(Lunch4); day9.add(ELong2); day9.add(GLong);

            day0.add(AFreeShort); day0.add(BShort2); day0.add(CShort); day0.add(DFreeShort1);
            day0.add(Lunch0); day0.add(EShort2); day0.add(FShort2); day0.add(GShort);

            tempScheduleArray.add(day1);
            tempScheduleArray.add(day2);
            tempScheduleArray.add(day3);
            tempScheduleArray.add(day4);
            tempScheduleArray.add(day5);
            tempScheduleArray.add(day6);
            tempScheduleArray.add(day7);
            tempScheduleArray.add(day8);
            tempScheduleArray.add(day9);
            tempScheduleArray.add(day0);


            CustomMethods customMethods = new CustomMethods();
            if (!customMethods.saveSchedule(tempScheduleArray, this))
                Log.e("MainActivity 248","Error in saving sharedpreference");

            //Log.d("MainActivity 295","should have stored schedule as sharedpreference");
            return tempScheduleArray;
        }

        //Log.d("MainActivity 986",tempScheduleArray.toString());
    }
    public ArrayList<Integer> getDayList(){
        //0=day 0, 1=day 1, ... , -1=no school
        int[] tempDayArray = {0, 1, 2, 3, 4, -1, -1, 5, 6, 7, 8, 9, -1, -1, 1, 2, 3, 4, 5, -1, -1, 6, 7, 8, 9, 1, -1, -1, 2, 3, 4, 0, 5, -1, -1, 6, 7, 8, 9, 1, -1, -1, 2, 3, 4, 5, 6, -1, -1, 7, 8, 9, 1, 2, -1, -1, 3, 4, 5, 6, -1, -1, -1, 7, 8, 9, 0, 1, -1, -1, -1, -1, 2, 3, 4, -1, -1, 5, 6, 7, 8, 9, -1, -1, 1, 2, 3, 4, 5, -1, -1, 6, 7, 8, 9, 1, -1, -1, 2, 3, 4, 5, 6, -1, -1, 7, 8, 9, 1, 2, -1, -1, 3, 4, 5, 6, 7, -1, -1, 8, 9, 1, 2, 3, -1, -1, 4, 5, 6, 7, 8, -1, -1, 9, 1, 2, 3, 4, -1, -1, 5, 6, 7, 8, 9, -1, -1, 0, 1, 0, 2, 3, -1, -1, 4, 5, 6, 7, 8, -1, -1, 9, 1, 0, 2, 3, -1, -1, 4, -1, 5, 6, 7, -1, -1, 8, 9, 1, 2, 3, -1, -1, 4, 5, 0, 6, 7, -1, -1, 8, 9, 1, 2, -1, -1, -1, 3, 4, 5, 6, 7, -1, -1, 8, 9, 1, 2, 3, -1, -1, 4, 5, -1, 0, 6, -1, -1, 7, 8, 9, 1, 2, -1, -1, 3, 4, 5, 6, 7, -1, -1, 8, 9, 1, 2, 3, -1, -1, 4, 5, 6, 7, 8, -1, -1, 9, 1, 0, 2, 3, -1, -1, 4, 5, 6, 7, 8, -1, -1, 9, 1, 2, 3, 4, -1, -1, 5, 6, 7, 8, 9, -1, -1, 1, 2, 3, 4, 5, -1, -1, 6, 7, 8, 9, 0, -1, -1, 1, 2, 3, 4, 5, -1, -1};
        ArrayList <Integer> tempDayList = new ArrayList<>(0);
        for(int i:tempDayArray)
            tempDayList.add(i);
        return tempDayList;
    }
    public int getCurDay(){
        Calendar cal = new GregorianCalendar();
        Calendar startDay = new GregorianCalendar();
        startDay.set(getResources().getInteger(R.integer.start_year), getResources().getInteger(R.integer.start_month), getResources().getInteger(R.integer.start_day));
        //Log.d("MainActivity 287", "" + cal.get(Calendar.DAY_OF_YEAR));
        //Log.d("MainActivity 288", ""+startDay.get(Calendar.DAY_OF_YEAR));


        int date = (cal.get(Calendar.YEAR)-2015)*365+cal.get(Calendar.DAY_OF_YEAR);
        int startDate = (startDay.get(Calendar.YEAR)-2015)*365+startDay.get(Calendar.DAY_OF_YEAR);

        return date-startDate;
    }
}