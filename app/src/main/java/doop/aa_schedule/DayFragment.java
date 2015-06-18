package doop.aa_schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DayFragment extends Fragment {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    
    public static final DayFragment newInstance(String message) {
        DayFragment f = new DayFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, message);
        f.setArguments(bdl);
        return f;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String message = getArguments().getString(EXTRA_MESSAGE);
        View v = inflater.inflate(R.layout.view_day, container, false);
        TextView messageTextView = (TextView) v.findViewById(R.id.dayText);
        messageTextView.setText(message);
        LinearLayout ll = (LinearLayout) v.findViewById(R.id.day_layout);
        LinearLayout.LayoutParams params;

        View p1 = inflater.inflate(R.layout.view_period, container, false);
        TextView perStart1 = (TextView) p1.findViewById(R.id.per_start_text);
        TextView perEnd1 = (TextView) p1.findViewById(R.id.per_end_text);
        TextView perMain1 = (TextView) p1.findViewById(R.id.per_main_text);
        perStart1.setText("11:24");
        perEnd1.setText("12:02");
        perMain1.setText("AP Biology (S123)");
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,74f);
        ll.addView(p1, params);

        View p2 = inflater.inflate(R.layout.view_period, container, false);
        TextView perStart2 = (TextView) p2.findViewById(R.id.per_start_text);
        TextView perEnd2 = (TextView) p2.findViewById(R.id.per_end_text);
        TextView perMain2 = (TextView) p2.findViewById(R.id.per_main_text);
        perStart2.setText("12:15");
        perEnd2.setText("1:06");
        perMain2.setText("Lunch");
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,56f);
        ll.addView(p2, params);

        View p3 = inflater.inflate(R.layout.view_period, container, false);
        TextView perStart3 = (TextView) p3.findViewById(R.id.per_start_text);
        TextView perEnd3 = (TextView) p3.findViewById(R.id.per_end_text);
        TextView perMain3 = (TextView) p3.findViewById(R.id.per_main_text);
        perStart3.setText("1:11");
        perEnd3.setText("1:45");
        perMain3.setText("Free");
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,32f);
        ll.addView(p3, params);

        View p4 = inflater.inflate(R.layout.view_period, container, false);
        TextView perStart4 = (TextView) p4.findViewById(R.id.per_start_text);
        TextView perEnd4 = (TextView) p4.findViewById(R.id.per_end_text);
        TextView perMain4 = (TextView) p4.findViewById(R.id.per_main_text);
        perStart4.setText("1:52");
        perEnd4.setText("3:32");
        perMain4.setText("English IV (B404)");
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,47f);
        ll.addView(p4,params);



        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

    }
}