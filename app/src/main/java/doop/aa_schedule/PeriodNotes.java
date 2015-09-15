package doop.aa_schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class PeriodNotes extends Fragment {
    String text;
    int dayIndex;
    int perIndex;
    EditText notes;
    DayFragment df;
    int dayNum;
    View perView;
    Period p;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_notes,container,false);

        notes = (EditText) view.findViewById(R.id.per_notes);
        notes.setText(text);
        //notes.setOnEditorActionListener();
        return view;
    }

    @Override
    public void onPause(){
        super.onPause();

        Log.d("PN 23", "updating day " + dayIndex + " per " + perIndex + " to " + notes.getText().toString());
        CustomMethods.updateDayNotes(dayIndex, getActivity(), String.valueOf(perIndex), notes.getText().toString());
        //df.updateNotes(dayNum, perIndex, notes.getText().toString());
        p.setNotes(notes.getText().toString());
        TextView perMain = (TextView) perView.findViewById(R.id.per_main_text);
        perMain.setText(p.getMainText(true));

    }

    public void sendArgs(Period p, int dayIndex, int perIndex, View perView){
        this.text = p.getNotes();
        this.dayIndex = dayIndex;
        //this.dayNum = dayNum;
        this.perIndex = perIndex;
        //this.df = df;
        this.perView = perView;
        this.p = p;
    }
}
