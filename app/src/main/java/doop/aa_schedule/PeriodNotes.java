package doop.aa_schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class PeriodNotes extends Fragment {
    Period period;
    EditText notes;

    //NOTE: period is not storing notes. Double check that this is as it should be
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_notes,container,false);

        notes = (EditText) view.findViewById(R.id.per_notes);
        notes.setText(period.getNotes());
        //notes.setOnEditorActionListener();
        return view;
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    public void sendArgs(Period p){
        period = p;
    }
}
