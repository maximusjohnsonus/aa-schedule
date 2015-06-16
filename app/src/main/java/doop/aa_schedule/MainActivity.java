package doop.aa_schedule;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
//import doop.aa_schedule.R;


public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    //Fragment managing the behaviors, interactions and presentation of the navigation drawer.
    private NavigationDrawerFragment mNavigationDrawerFragment;

    //Used to store the last screen title. For use in {@link #restoreActionBar()}.
    private CharSequence mTitle;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ArrayList<ArrayList<Period>>scheduleArray;


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

        getSchedule();

    }



    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        /*FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container,PlaceholderFragment.newInstance(position)).commit();*/


        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (position){
            case 0:
                ft.replace(R.id.container,new ViewSchedule()).commit();
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

    public void getSchedule() { //first three periods until I get an actual sample schedule (Mr Kim said he'd send it to me 6-17)
        String prefName = getResources().getString(R.string.pref_storage);
        SharedPreferences sp = getSharedPreferences(prefName, 0);
        String key=getResources().getString(R.string.schedule_JSON);
        String scheduleJSONString=sp.getString(key, "");

        Log.d("MainActivity 357","JSON string recovered:"+scheduleJSONString);
        boolean rewrite=false; //set to true to rewrite the data
        if(!scheduleJSONString.equals("") && !rewrite) {
            try {
                JSONArray scheduleJSONArray = new JSONArray(scheduleJSONString);

                scheduleArray=new ArrayList<ArrayList<Period>>();
                ArrayList<Period> day=new ArrayList<>();
                for(int i=0; i<scheduleJSONArray.length(); i++){ //for day in cycle
                    JSONArray dayJSON = (JSONArray) scheduleJSONArray.getJSONArray(i);
                    for(int j=0; j<dayJSON.length(); j++){ //for period in day
                        day.add(new Period(dayJSON.getJSONObject(j), getResources()));
                    }
                    scheduleArray.add(day);
                }
                Log.d("MainActivity 476","should have loaded schedule from sharedpreference. string: "+scheduleJSONString);
            } catch(JSONException e){
                Log.e("MainActivity 304","error retrieving JSON array from string. string: "+scheduleJSONString);
                e.printStackTrace();
            }

        } else{
            Period AShort = new Period(480, 527, "AP Biology", 0, "S125");
            Period ALong = new Period(480, 554, "AP Biology", 0, "S125");
            Period BShort1 = new Period(480, 527, "AP Computer Science", 1, "S222");
            Period BShort2 = new Period(534, 581, "AP Computer Science", 1, "S222");
            Period BShort3 = new Period(561, 608, "AP Computer Science", 1, "S222");
            Period CShort = new Period(588, 635, "English III", 2, "MK123");
            Period CLong = new Period(561, 635, "English III", 2, "MK123");
            Period AFree1 = new Period(480, 527, "Free", 0);
            Period AFree2 = new Period(527, 554, "Free", 0);
            Period BFree1 = new Period(527, 554, "Free", 1);
            Period BFree2 = new Period(608, 635, "Free", 1);
            Period CFree1 = new Period(588, 635, "Free", 2);


            scheduleArray=new ArrayList<>();
            ArrayList<Period> day1=new ArrayList<>();
            day1.add(AShort); day1.add(AFree2);day1.add(BShort3); day1.add(BFree2);
            scheduleArray.add(day1); //1

            ArrayList<Period> all=new ArrayList<>();
            all.add(AShort); all.add(BShort2); all.add(CShort); //day 2,3,5,6,8,9,0
            scheduleArray.add(all); //2

            ArrayList<Period> day3=new ArrayList<>();
            day3.add(AFree1); day3.add(BShort2); day3.add(CShort);
            scheduleArray.add(day3); //3

            ArrayList<Period> day4=new ArrayList<>();
            day4.add(ALong); day4.add(CLong);
            scheduleArray.add(day4); //4

            scheduleArray.add(all); //5

            ArrayList<Period> day6=new ArrayList<>();
            day6.add(AShort); day6.add(BShort2); day6.add(CFree1);
            scheduleArray.add(day6); //6

            ArrayList<Period> day7=new ArrayList<>();
            day7.add(BShort1); day7.add(BFree1); day7.add(CLong);
            scheduleArray.add(day7); //7

            scheduleArray.add(all); //8
            scheduleArray.add(all); //9
            scheduleArray.add(all); //0

            JSONArray days=new JSONArray();
            JSONArray day=new JSONArray();
            for(ArrayList<Period> arr:scheduleArray){ //for day in cycle
                for(Period p:arr){ //for period in day
                    day.put(p.toJSON(getResources())); //error somewhere over here!!!
                }
                days.put(day);
            }
            scheduleJSONString=days.toString();
            Log.d("MainActivity 014","String to be written:"+scheduleJSONString);
            SharedPreferences.Editor editor=sp.edit();
            editor.putString(key, scheduleJSONString);
            editor.commit();
            Log.d("MainActivity 295","should have stored schedule as sharedpreference");
        }

        Log.d("MainActivity 986",scheduleArray.toString());
    }
}