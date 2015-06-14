package doop.aa_schedule;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Help extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_help,container,false);
        return view;
    }

    public void setText(String item){
        TextView view=(TextView) getView().findViewById(R.id.helpText);
        view.setText(item);
    }
}
