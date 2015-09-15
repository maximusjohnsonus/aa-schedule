package doop.aa_schedule;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

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
    private static String DAY_NOTES = "/DAY_NOTES_";


    //TODO: make colors[] stored as sharedPreferences (or other)

    public int paleColor (int color){
        return Color.rgb(255 - (255 - Color.red(color)) / paleFactor, 255 - (255 - Color.green(color)) / paleFactor, 255 - (255 - Color.blue(color)) / paleFactor);
    }

    public int getPerColor (Period p){
        if(p.hasColor()){
            return(p.getColor());
        } else if(!p.isFree())
            return(colors[p.getBlock()]);
        else{
            return(paleColor(colors[p.getBlock()]));
        }
    }
    public int getDefaultPerColor (Period p){
            return(colors[p.getBlock()]);
    }

    public boolean saveSchedule(ArrayList<ArrayList<Period>> schedule, Context c, String tag){
        String prefName = c.getResources().getString(R.string.pref_storage);
        SharedPreferences sp = c.getSharedPreferences(prefName, 0);
        String key = c.getResources().getString(R.string.schedule_JSON);
        if(schedule.size()==0){
            SharedPreferences.Editor editor=sp.edit();
            editor.putString(key, "");
            return editor.commit();
        }

        JSONArray days=new JSONArray();
        JSONArray day;
        for(ArrayList<Period> arr:schedule){ //for day in cycle
            day=new JSONArray();
            for(Period p:arr){ //for period in day
                day.put(p.toJSON(c.getResources()));
            }
            days.put(day);
//            Log.d("CM 67",day.toString());
        }
        String scheduleJSONString=days.toString();
//        Log.d("CustomMethods 457", scheduleJSONString);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(key, scheduleJSONString);
        return editor.commit();
    }

    public boolean saveJSONSchedule(String JSONSchedule, Context c){

        String prefName = c.getResources().getString(R.string.pref_storage);
        SharedPreferences sp = c.getSharedPreferences(prefName, 0);
        String key = c.getResources().getString(R.string.schedule_JSON);

        //Log.d("CustomMethods 482", JSONSchedule);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(key, JSONSchedule);
        return editor.commit();
    }

    public boolean time24(Context c){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
        String key=c.getResources().getString(R.string.time_24_pref);
        return sp.getBoolean(key, false);
    }
    public boolean alignLeft(Context c){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
        String key=c.getResources().getString(R.string.align_pref);
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
    public static boolean showNotes(Context c){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
        String key=c.getResources().getString(R.string.show_notes_pref);
        return sp.getBoolean(key, true);
    }

    public static HashMap getDayNotes(int index, Context c){
        HashMap notes = null;
        try {
            String filePath = c.getFilesDir().getPath();
            //FileInputStream fis = c.openFileInput(filePath + DAY_NOTES + String.valueOf(index));
            FileInputStream fis = new FileInputStream(new File(filePath, DAY_NOTES + String.valueOf(index)));
            ObjectInputStream ois = new ObjectInputStream(fis);
            notes = (HashMap)ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            Log.d("CM 122", "FileNotFound");
            //e.printStackTrace();
        } catch (IOException e) {
            Log.d("CM 125","IOException");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Log.d("CM 128","ClassNotFound");
            e.printStackTrace();
        }
        return notes;
    }
    public static void setDayNotes(int index, Context c, HashMap dayNotes){
        try {
            String filePath = c.getFilesDir().getPath();
            //FileOutputStream fos = c.openFileOutput(filePath + DAY_NOTES + String.valueOf(index), Context.MODE_PRIVATE);
            FileOutputStream fos = new FileOutputStream(new File(filePath, DAY_NOTES + String.valueOf(index)));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dayNotes);
            oos.flush();
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d("CM 142","FileNotFound");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("CM 157","IOException");
            e.printStackTrace();
        }
    }
    public static void updateDayNotes(int index, Context c, String key, String newNotes){
        HashMap dayNotes = getDayNotes(index, c);
        String filePath = c.getFilesDir().getPath();
        File notesFile = new File(filePath+DAY_NOTES+String.valueOf(index));

        if(dayNotes==null) {
            if(newNotes==null || newNotes.length()==0) {
                Log.d("CM168","not making file");
                return;
            }
            dayNotes = new HashMap();
        }

        if(newNotes==null || newNotes.length()==0){
            if(dayNotes.containsKey(key)) {
                dayNotes.remove(key);
                if(dayNotes.isEmpty()) {
                    Log.d("CM176", "deleting file " + notesFile.toString());
                    notesFile.delete();
                    return;
                }
            } else {
                Log.d("CM181","not making Hash entry");
                return;
            }
        }
        dayNotes.put(key, newNotes);

        try {
            //FileOutputStream fos = c.openFileOutput(filePath + DAY_NOTES+String.valueOf(index), Context.MODE_PRIVATE);
            FileOutputStream fos = new FileOutputStream(notesFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dayNotes);
            oos.flush();
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d("CM 185","FileNotFound");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("CM 188","IOException");
            e.printStackTrace();
        }
    }
    public static void clearDayNotes(Context c){
        File myFiles = c.getFilesDir();
        for(File f:myFiles.listFiles()){
            if(f.toString().contains(DAY_NOTES))
                f.delete();
        }
    }


    //public int[] getColors(){ return colors; } //TODO: change default colors for periods by changing colors[] in CustomMethods
}
