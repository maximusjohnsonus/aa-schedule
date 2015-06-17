package doop.aa_schedule;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class EventAdd extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view=inflater.inflate(R.layout.fragment_add_event,container,false);
 /*       feedbackText = (EditText) view.findViewById(R.id.feedbackText);
        feedbackName = (EditText) view.findViewById(R.id.feedbackName);
        feedbackButton = (Button) view.findViewById(R.id.feedbackButton);
        feedbackButton.setOnClickListener(this);
        feedbackType = (RadioGroup) view.findViewById(R.id.feedbackType);*/
        return view;
    }
}
