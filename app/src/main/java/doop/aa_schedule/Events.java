package doop.aa_schedule;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Events extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    EventAdapter eAdapter;
    ListView eventList;

    Button addEvent;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view=inflater.inflate(R.layout.fragment_events,container,false);
        addEvent = (Button) view.findViewById(R.id.eventAdd);
        addEvent.setOnClickListener(this);
        eventList = (ListView) view.findViewById(R.id.eventList);
        eAdapter = new EventAdapter(inflater);
        eventList.setAdapter(eAdapter);
        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container,new EventAdd()).commit();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Resources r = view.getResources();
        JSONObject jsonObject = eAdapter.getItem(position);
        try {
            JSONArray Jwds = jsonObject.getJSONArray(r.getString(R.string.JSON_event_weekdays));
            boolean[] wds = new boolean[5];
            for(int i=0; i<5; i++) wds[i]=Jwds.getBoolean(i);
            JSONArray Jcds = jsonObject.getJSONArray(r.getString(R.string.JSON_event_cycledays));
            boolean[] cds = new boolean[10];
            for(int i=0; i<10; i++) cds[i]=Jcds.getBoolean(i);
            int sT = jsonObject.getInt(r.getString(R.string.JSON_event_start_time));
            int eT = jsonObject.getInt(r.getString(R.string.JSON_event_end_time));
            String t = jsonObject.getString(r.getString(R.string.JSON_event_title));
            // This line is definitely very readable
            String d = jsonObject.has(r.getString(R.string.JSON_event_description)) ? jsonObject.getString(r.getString(R.string.JSON_event_description)) : null;
            // TODO: Open view/edit event fragment
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
