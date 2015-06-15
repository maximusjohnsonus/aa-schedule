package doop.aa_schedule;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;


public class ViewSchedule extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view=inflater.inflate(R.layout.fragment_view_schedule,container,false);

        Calendar cal = Calendar.getInstance();
        int hour=cal.get(Calendar.HOUR);
        int min=cal.get(Calendar.MINUTE);
        setText("The time this frag was created was "+hour+":"+min+", or "+(60*hour+min)+" minutes");
        return view;
    }

    public void setText(String item){

        TextView Tview=(TextView) view.findViewById(R.id.viewSText);

        Tview.setText(item);
    }
}
