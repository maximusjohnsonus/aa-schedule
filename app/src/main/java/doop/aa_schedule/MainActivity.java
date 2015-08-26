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
    //private CharSequence mTitle;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ArrayList<ArrayList<Period>> scheduleArray;
    private ArrayList<Integer> dayList; //0=day 0, 1=day 1, ... , -1=no school
    private int currentDay=-1;

    private boolean rewrite=false; //set to true to rewrite the data


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().
                findFragmentById(R.id.navigation_drawer);
        //mTitle = getTitle();

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
                if(scheduleArray!=null) {
                    if (dayList == null) dayList = getDayList();
                    if (currentDay == -1) currentDay = getCurDay();
                    vs.sendArgs(scheduleArray, dayList, currentDay);
                    ft.replace(R.id.container, vs).commit();
                }
                break;
            case 1:
                EditSchedule es = new EditSchedule();
                if(scheduleArray==null) scheduleArray = getSchedule();
                if(scheduleArray!=null) {
                    es.sendArgs(scheduleArray);
                    ft.replace(R.id.container, es).commit();
                }
                break;
            case 2:
                getSupportActionBar().setTitle("Settings");
                ft.replace(R.id.container,new SettingsFragment()).commit();
                break;
            case 3:
                getSupportActionBar().setTitle("Help");
                ft.replace(R.id.container,new Help()).commit();
                break;
            case 4:
                getSupportActionBar().setTitle("About");
                ft.replace(R.id.container,new About()).commit();
                break;
            case 5:
                getSupportActionBar().setTitle("Feedback");
                ft.replace(R.id.container,new Feedback()).commit();
                break;
        }





    }

    /*public void onSectionAttached(int number) {
        mTitle = getResources().getStringArray(R.array.sidebar_options)[number];
        /*switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }* /
    }*/

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        //actionBar.setTitle(mTitle);
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
        /*if (id == R.id.action_settings) {
            return true;
        }*/

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
            //((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }


    public ArrayList<ArrayList<Period>> getSchedule() {
        String prefName = getResources().getString(R.string.pref_storage);
        SharedPreferences sp = getSharedPreferences(prefName, 0);
        String key=getResources().getString(R.string.schedule_JSON);
        String scheduleJSONString=sp.getString(key, "");
        Log.d("MainActivity","213");

        //Log.d("MainActivity 357","JSON string recovered:"+scheduleJSONString);
        if(rewrite){
            CustomMethods customMethods = new CustomMethods();
            customMethods.saveSchedule(new ArrayList<ArrayList<Period>>(0), this, "Rewrite");
            Log.e("MainActivity 052", "Rewrite is true, revert when finished testing");
            rewrite = false;
            scheduleJSONString="";
        }

        if(scheduleJSONString.length()>0) {
            Log.d("MainActivity","224");
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

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.container,new GetScheduleFragment()).commit();

            return null;
        }

        //Log.d("MainActivity 986",tempScheduleArray.toString());
    }

    public ArrayList<Integer> getDayList(){
        //0=day 0, 1=day 1, ... , -1=no school
        int[] tempDayArray = {0, 1, -1, -1, 2, 3, 4, 5, 0, -1, -1, 6, 7, 8, 9, 1, -1, -1, -1, 2, 3, 4, 5, -1, -1, 6, 7, 8, 9, 1, -1, -1, 2, 3, 4, 5, 6, -1, -1, 0, 7, 8, 9, 1, -1, -1, 2, 3, 4, 5, -1, -1, -1, -1, 6, 0, 7, 8, -1, -1, 9, 1, 2, 3, 4, -1, -1, 5, 6, 7, 8, 9, -1, -1, 1, 2, 3, 4, -1, -1, -1, 5, 6, 7, 8, 9, -1, -1, 1, 2, 3, 4, 5, -1, -1, 6, 7, -1, -1, -1, -1, -1, 8, 9, 1, 2, 3, -1, -1, 4, 5, 6, 7, 0, -1, -1, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 2, 3, 4, 5, -1, -1, 6, 7, 8, 9, 1, -1, -1, -1, 2, 3, 4, 5, -1, -1, 6, 7, 8, 0, 9, -1, -1, 1, 2, 3, 4, 5, -1, -1, 6, 7, 8, 9, 1, -1, -1, -1, 2, 3, 4, 5, -1, -1, 6, 7, 8, 9, 1, -1, -1, 2, 3, 4, 5, 6, -1, -1, 7, 8, 9, 1, 2, -1, -1, 3, 4, 0, 5, 6, -1, -1, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 2, 3, -1, -1, 4, 5, 6, 7, 8, -1, -1, 9, 1, 2, 3, 4, -1, -1, 5, 6, 7, 8, -1, -1, -1, 9, 1, 2, 3, 4, -1, -1, 5, 6, 7, 8, 9, -1, -1, 1, 2, 0, 0, 0};

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