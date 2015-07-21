package doop.aa_schedule;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;

public class CustomMethods {
    int paleFactor = 7;
    private static int[] colors = {
            Color.rgb(255, 174, 201), //A (pink)
            Color.rgb(255, 127, 39),  //B (red)
            Color.rgb(255, 201, 14),  //C (orange
            Color.rgb(255, 255, 74),  //D (yellow)
            Color.rgb(34, 189, 255),  //E (blue)
            Color.rgb(102, 111, 215), //F (indigo)
            Color.rgb(163, 73, 164),  //G (purple)
            Color.rgb(181, 230, 29),  //Lunch (green)
            Color.rgb(150, 150, 150)};//Misc (grey)


    //TODO: make colors[] stored as sharedPreferences (or other)

    public int paleColor (int color){
        return Color.rgb(255 - (255 - Color.red(color)) / paleFactor, 255 - (255 - Color.green(color)) / paleFactor, 255 - (255 - Color.blue(color)) / paleFactor);
    }

    public int getPerColor (Period p){
        if(p.hasColor()){
            return(p.getColor());
        } else if(p.getType()!=2)
            return(colors[p.getBlock()]);
        else{
            return(paleColor(colors[p.getBlock()]));
        }
    }
    public int getDefaultPerColor (Period p){
        if(p.getType()!=2)
            return(colors[p.getBlock()]);
        else
            return(paleColor(colors[p.getBlock()]));
    }

    public boolean saveSchedule(ArrayList<ArrayList<Period>> schedule, Context c, String tag){
        Log.d("CM 487", tag+schedule.toString());

        String prefName = c.getResources().getString(R.string.pref_storage);
        SharedPreferences sp = c.getSharedPreferences(prefName, 0);
        String key = c.getResources().getString(R.string.schedule_JSON);

        JSONArray days=new JSONArray();
        JSONArray day;
        for(ArrayList<Period> arr:schedule){ //for day in cycle
            day=new JSONArray();
            for(Period p:arr){ //for period in day
                day.put(p.toJSON(c.getResources()));
            }
            days.put(day);

        }
        String scheduleJSONString=days.toString();
        Log.d("CustomMethods 457", scheduleJSONString);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(key, scheduleJSONString);
        return editor.commit();
    }

    public ArrayList<ArrayList<Period>> copySchedule(ArrayList<ArrayList<Period>> schedule){
        ArrayList<ArrayList<Period>> newSchedule = new ArrayList<>();
        ArrayList<Period> tempDay;
        for(ArrayList<Period> iDay:schedule){
            tempDay = new ArrayList<>();
            for(Period iPer:iDay){
                tempDay.add(new Period(iPer));
            }
            newSchedule.add(tempDay);
        }
        return newSchedule;
    }

    public boolean time24(Context c){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
        String key=c.getResources().getString(R.string.time_24_pref);
        return sp.getBoolean(key, false);
    }
    public int getMinPerLength(Context c){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
        String key=c.getResources().getString(R.string.min_per_pref);
        return Integer.valueOf(sp.getString(key, Integer.toString(c.getResources().getInteger(R.integer.default_min_per))));
    }
    public boolean showHighlight(Context c){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
        String key=c.getResources().getString(R.string.highlight_pref);
        return sp.getBoolean(key, true);
    }
    public boolean showWeekend(Context c){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
        String key=c.getResources().getString(R.string.weekend_pref);
        return sp.getBoolean(key, false);
    }


    //public int[] getColors(){ return colors; } //TODO: change default colors for periods by changing colors[] in CustomMethods
}
