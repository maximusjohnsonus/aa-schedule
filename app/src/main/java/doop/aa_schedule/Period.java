package doop.aa_schedule;
//used to store class periods
public class Period {
    protected int startTime;
    protected int endTime;
    protected String className;
    protected String room;
    protected int block; //stores which block the period occurs in. A is 0, B is 1, etc. Used for coloring and probably editing

    public Period(int start, int end, String name, String r, int b){ //constructor for normal class/study hall
        startTime = start;
        endTime = end;
        className = name;
        room = r;
        block = b;
    }

    public Period(int start, int end, String name, int b){ //constructor for free
        startTime = start;
        endTime = end;
        className = name;
        block = b;
    }
}
