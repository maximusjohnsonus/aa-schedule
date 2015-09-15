package doop.aa_schedule;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

//used to store class periods
public class Period{
        //implements Parcelable //reEnable this if one needs to bundle a period or an arraylist of periods
    private int startTime; //minutes
    private int endTime;  //minutes
    private String className;
    private int block; //stores which block the period occurs in. A=0,B=1,...,G=6,Lunch=7, misc=8. Used for coloring and editing as a group
    private String room; //optional
    private boolean isFree;
    private int color; //optional, set is hasColor is true
    private boolean hasColor=false;
    private String notes;
    //private boolean time24=false;
    CustomMethods customMethods = new CustomMethods();

    public Period(int start, int end, String name, int vBlock, boolean isFree, String vRoom){ //constructor for normal class/study hall
        startTime = start;
        endTime = end;
        className = name;
        block = vBlock;
        this.isFree = isFree;
        room = vRoom;
        if(start>end)
            Log.e("Period 923", "Start time ("+start+") is later than end time ("+end+")");
        //time24 = customMethods.time24();
    }
    public Period(int start, int end, String name, int vBlock, boolean isFree){ //constructor for free
        startTime = start;
        endTime = end;
        className = name;
        block = vBlock;
        this.isFree = isFree;
        if(start>end)
            Log.e("Period 924", "Start time ("+start+") is later than end time ("+end+")");
    }
    public Period(Period p){
        startTime = p.getStart();
        endTime = p.getEnd();
        className = p.getClassName();
        block = p.getBlock();
        isFree = p.isFree();
        room = p.getRoom();
        color = p.getColor();
        hasColor = p.hasColor();
        notes = p.getNotes();
    }
    public Period(JSONObject p, Resources r){ //constructor for JSON interpreting
        try{
            if(p.has(r.getString(R.string.JSON_period_start_time)))
                startTime=p.getInt(r.getString(R.string.JSON_period_start_time));
            else Log.e("Period 498","start time not loaded :(");
            if(p.has(r.getString(R.string.JSON_period_end_time)))
                endTime=p.getInt(r.getString(R.string.JSON_period_end_time));
            else Log.e("Period 499","end time not loaded :(");
            if(p.has(r.getString(R.string.JSON_period_class_name)))
                className=p.getString(r.getString(R.string.JSON_period_class_name));
            else Log.e("Period 500","class name not loaded :(");
            if(p.has(r.getString(R.string.JSON_period_block)))
                block=p.getInt(r.getString(R.string.JSON_period_block));
            else Log.e("Period 501","block not loaded :(");
            if(p.has(r.getString(R.string.JSON_period_is_free)))
                isFree=p.getBoolean(r.getString(R.string.JSON_period_is_free));
            else Log.e("Period 502","type not loaded :(");
            if(p.has(r.getString(R.string.JSON_period_color)))
                color=p.getInt(r.getString(R.string.JSON_period_color));
            if(p.has(r.getString(R.string.JSON_period_has_color)))
                hasColor=p.getBoolean(r.getString(R.string.JSON_period_has_color));
            else hasColor=false;
            if(p.has(r.getString(R.string.JSON_period_room)))
                room=p.getString(r.getString(R.string.JSON_period_room));

        }catch (JSONException e){
            Log.e("Period 319","error in JSON constructor. JSONObject:"+p.toString());
            e.printStackTrace();
        }
        if(startTime>endTime)
            Log.e("Period 923", "Start time ("+startTime+") is later than end time ("+endTime+")");
    }

    public String toString(){
        String startMin = startTime%60<10? "0" + startTime%60 : startTime%60+"";
        String endMin = endTime%60<10? "0" + endTime%60 : endTime%60+"";
        return "["+startTime/60+":"+startMin+" - "+endTime/60+":"+endMin+", "+className+", "+block+", "+isFree+", "+room+"]";
    }
    public JSONObject toJSON(Resources r){
        try{
            JSONObject json = new JSONObject();
            json.put(r.getString(R.string.JSON_period_start_time),startTime);
            json.put(r.getString(R.string.JSON_period_end_time),endTime);
            json.put(r.getString(R.string.JSON_period_class_name),className);
            json.put(r.getString(R.string.JSON_period_block),block);
            json.put(r.getString(R.string.JSON_period_is_free), isFree);
            json.put(r.getString(R.string.JSON_period_color), color);
            json.put(r.getString(R.string.JSON_period_has_color), hasColor);
            if(room!=null)
                json.put(r.getString(R.string.JSON_period_room),room);
            //Log.d("Period 105","JSON period:"+json.toString());
            return json;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public String getStartString(Context c) {
        return (( (startTime/60 - 1) % (customMethods.time24(c) ? 24 : 12) ) + 1) + ":" + (startTime%60 < 10 ? "0"+startTime%60 : startTime%60);
    }
    public String getEndString(Context c) {
        return (( (endTime/60 - 1) % (customMethods.time24(c) ? 24 : 12) ) + 1) + ":" + (endTime%60 < 10 ? "0"+endTime%60 : endTime%60);
    }
    public String getTimeString(Context c) {return getStartString(c)+" - "+getEndString(c);}
    public String getMainText(boolean showNotes) {return className + ((room==null||room.length()==0) ? "" : " ("+room+")") + ((notes==null||notes.length()==0||!showNotes)?"":" - "+notes);}
    public float getLength() {return endTime - startTime;}

    public String getClassName(){return className;}
    public String getRoom() {return room;}
    public int getStart() {return startTime;}
    public int getEnd() {return endTime;}
    public int getColor(){return color;}
    public int getBlock(){return block;}
    public boolean isFree(){return isFree;}
    public String getNotes() {return notes;}

    public boolean hasColor(){return hasColor;}

    public void setClassName(String className) {this.className = className;}
    public void setRoom(String room) {this.room = room;}
    public void setStart(int start) {startTime = start;}
    public void setEnd(int end) {endTime = end;}
    public void setColor(int _color) {
        color=_color;
        hasColor=true;
    }
    public void removeColor(){
        color=0;
        hasColor=false;
    }
    public void setIsFree(boolean isFree) {this.isFree = isFree;}
    public void setNotes(String notes) {this.notes = notes;}



    /*//Parcelable part
    public Period(Parcel in){
        int[] iArgs = new int[0];
        String[] sArgs = new String[0];
        boolean[] bArgs = new boolean[0];
        in.readIntArray(iArgs);
        in.readStringArray(sArgs);
        startTime = iArgs[0];
        endTime = iArgs[1];
        block = iArgs[2];
        type = iArgs[3];
        color = iArgs[4];
        className = sArgs[0];
        room = sArgs[1];
        hasColor = bArgs[0];
    }

    @Override
    public int describeContents() {return 0;}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeIntArray(new int[]{startTime,endTime,block,type,color});
        dest.writeStringArray(new String[]{className, room});
        dest.writeBooleanArray(new boolean[]{hasColor});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Period createFromParcel(Parcel in) {
            return new Period(in);
        }
        public Period[] newArray(int size) {
            return new Period[size];
        }
    }; */
}
