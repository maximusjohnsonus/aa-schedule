package doop.aa_schedule;

import android.content.res.Resources;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

//used to store class periods
public class Period {
    protected int startTime; //minutes
    protected int endTime;  //minutes
    protected String className;
    protected int block; //stores which block the period occurs in. A=0,B=1,...,G=6,Lunch=7,other stuff continues. Used for coloring and probably editing
    protected String room; //optional
    /*private String JSON_start_time =
    private String JSON_end_time =
    private String JSON_class_name =
    private String JSON_room =
    private String JSON_block = */

    public Period(int start, int end, String name, int vBlock, String vRoom){ //constructor for normal class/study hall
        startTime = start;
        endTime = end;
        className = name;
        block = vBlock;
        room = vRoom;
    }

    public Period(int start, int end, String name, int vBlock){ //constructor for free
        startTime = start;
        endTime = end;
        className = name;
        block = vBlock;
    }

    public Period(JSONObject p, Resources r){ //constructor for JSON interpreting
        try{
            startTime=p.getInt(r.getString(R.string.JSON_period_start_time));
            endTime=p.getInt(r.getString(R.string.JSON_period_end_time));
            className=p.getString(r.getString(R.string.JSON_period_class_name));
            block=p.getInt(r.getString(R.string.JSON_period_block));
            if(p.has(r.getString(R.string.JSON_period_room)))
                room=p.getString(r.getString(R.string.JSON_period_room));

        }catch (JSONException e){
            Log.d("Period 319","error in JSON constructor. JSONObject:"+p.toString());
            e.printStackTrace();
        }
    }

    public void setClassName(String name){
        className = name;
    }
    public String toString(){
        String startMin = startTime%60<10? "0" + startTime%60 : startTime%60+"";
        String endMin = endTime%60<10? "0" + endTime%60 : endTime%60+"";
        return "["+startTime/60+":"+startMin+" - "+endTime/60+":"+endMin+", "+className+", "+block+", "+room+"]";
    }

    public JSONObject toJSON(Resources r){
        try{
            JSONObject json = new JSONObject();
            json.put(r.getString(R.string.JSON_period_start_time),startTime);
            json.put(r.getString(R.string.JSON_period_end_time),endTime);
            json.put(r.getString(R.string.JSON_period_class_name),className);
            json.put(r.getString(R.string.JSON_period_block),block);
            if(room!=null)
                json.put(r.getString(R.string.JSON_period_room),room);
            Log.d("Period 105","JSON period:"+json.toString());
            return json;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }
}
