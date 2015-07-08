package doop.aa_schedule;

import java.util.ArrayList;

public class Day {
    private int cycleDay;
    private ArrayList<String> notes;

    public Day (int cycleDay, ArrayList<String> notes){
        this.cycleDay = cycleDay;
        this.notes = notes;
    }
}
