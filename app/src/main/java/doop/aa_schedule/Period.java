package doop.aa_schedule;

import android.content.res.Resources;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

//used to store class periods
public class Period
        //implements Parcelable //reEnable this if one needs to bundle a period or an arraylist of periods
        {
    int startTime; //minutes
    int endTime;  //minutes
    String className;
    int block; //stores which block the period occurs in. A=0,B=1,...,G=6,Lunch=7,other stuff continues. Used for coloring and probably editing
    String room; //optional
    int type; //0=class, 1=lunch, 2=free, 3=other
    int color;
    boolean hasColor=false;
    


    public Period(int start, int end, String name, int vBlock, int vType, String vRoom){ //constructor for normal class/study hall
        startTime = start;
        endTime = end;
        className = name;
        block = vBlock;
        type = vType;
        room = vRoom;
    }
    public Period(int start, int end, String name, int vBlock, int vType){ //constructor for free
        startTime = start;
        endTime = end;
        className = name;
        block = vBlock;
        type = vType;
    }
    public Period(JSONObject p, Resources r){ //constructor for JSON interpreting
        try{
            startTime=p.getInt(r.getString(R.string.JSON_period_start_time));
            endTime=p.getInt(r.getString(R.string.JSON_period_end_time));
            className=p.getString(r.getString(R.string.JSON_period_class_name));
            block=p.getInt(r.getString(R.string.JSON_period_block));
            type=p.getInt(r.getString(R.string.JSON_period_type));
            if(p.has(r.getString(R.string.JSON_period_room)))
                room=p.getString(r.getString(R.string.JSON_period_room));

        }catch (JSONException e){
            Log.d("Period 319","error in JSON constructor. JSONObject:"+p.toString());
            e.printStackTrace();
        }
    }

    public String toString(){
        String startMin = startTime%60<10? "0" + startTime%60 : startTime%60+"";
        String endMin = endTime%60<10? "0" + endTime%60 : endTime%60+"";
        return "["+startTime/60+":"+startMin+" - "+endTime/60+":"+endMin+", "+className+", "+block+", "+type+", "+room+"]";
    }
    public JSONObject toJSON(Resources r){
        try{
            JSONObject json = new JSONObject();
            json.put(r.getString(R.string.JSON_period_start_time),startTime);
            json.put(r.getString(R.string.JSON_period_end_time),endTime);
            json.put(r.getString(R.string.JSON_period_class_name),className);
            json.put(r.getString(R.string.JSON_period_block),block);
            json.put(r.getString(R.string.JSON_period_type),type);
            if(room!=null)
                json.put(r.getString(R.string.JSON_period_room),room);
            //Log.d("Period 105","JSON period:"+json.toString());
            return json;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    private String getStartString() {return startTime/60 + ":" + (startTime%60 < 10 ? "0"+startTime%60 : startTime%60);}
    private String getEndString() {return endTime/60 + ":" + (endTime%60 < 10 ? "0"+endTime%60 : endTime%60);}
    public String getTimeString() {return getStartString()+" - "+getEndString();}
    public String getMainText() {return className + (room==null ? "" : " ("+room+")");}
    public float getLength(){return endTime - startTime;}
    public int getColor(){return color;}
    public int getBlock(){return block;}
    public int getType(){return type;}

    public boolean hasColor(){return hasColor;}

    public void setColor(int _color) {
        color=_color;
        hasColor=true;
    }

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
