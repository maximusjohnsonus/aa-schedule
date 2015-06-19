package doop.aa_schedule;

import java.util.ArrayList;

public interface FragmentCommunicator {
    void sendScheduleArray(ArrayList<ArrayList<Period>> schArr);
    //void sendDay(ArrayList<Period> day);
}
