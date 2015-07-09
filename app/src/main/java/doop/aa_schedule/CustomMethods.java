package doop.aa_schedule;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import org.json.JSONArray;

import java.util.ArrayList;

public class CustomMethods {
    int paleFactor = 10;
    private static int[] colors = {Color.RED, Color.rgb(255, 128, 0), Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.rgb(128, 0, 128), Color.rgb(0,150,0), Color.rgb(128, 64, 32), Color.rgb(32, 32, 32)};
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

    public boolean saveSchedule(ArrayList<ArrayList<Period>> schedule, Context c){
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
        //Log.d("CustomMethods 457", scheduleJSONString);
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
}
