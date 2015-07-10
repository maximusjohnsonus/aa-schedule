package doop.aa_schedule;

import android.app.TimePickerDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;


public class EditPeriodFragment extends Fragment implements ColorPickerDialog.OnColorChangedListener {
    Period period;
    int dayIndex;
    int perIndex;
    EditText classEdit;
    EditText roomEdit;
    Button startButton;
    Button endButton;
    Spinner typeSpinner;
    Button colorButton;
    CustomMethods customMethods = new CustomMethods();

    /*String newClass;
    String newRoom;*/
    int newStart;
    int newEnd;
    int newType;
    int newColor;
    boolean setColor = false;

    boolean time24=false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        time24 = customMethods.time24(getActivity());
        newStart = period.getStart();
        newEnd = period.getEnd();
        View view=inflater.inflate(R.layout.fragment_edit_period,container,false);

        classEdit = (EditText) view.findViewById(R.id.class_edit);
        classEdit.setText(period.getClassName());

        roomEdit = (EditText) view.findViewById(R.id.room_edit);
        roomEdit.setText(period.getRoom());

        startButton = (Button) view.findViewById(R.id.start_edit);
        startButton.setText(period.getStartString(getActivity()));
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //newStart = period.getStart();
                int hour = newStart / 60;
                int minute = newStart % 60;
                TimePickerDialog startTimePicker;
                startTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        int oldNewStart = newStart;
                        newStart = selectedHour * 60 + selectedMinute;
                        if(newStart<8*60 || newStart>15*60+32){
                            Toast.makeText(getActivity(), getResources().getString(time24 ? R.string.insane_time24 : R.string.insane_time12), Toast.LENGTH_LONG).show();
                            newStart = oldNewStart;
                        }
                        if(newStart>newEnd){
                            Toast.makeText(getActivity(), getResources().getString(R.string.misordered_time), Toast.LENGTH_LONG).show();
                            newStart = oldNewStart;
                        }
                        startButton.setText( (((newStart/60 - 1) % (time24 ? 24:12) ) + 1) + ":" + (newStart%60<10 ? "0" : "") + newStart%60);
                    }
                }, hour, minute, time24);
                startTimePicker.setTitle(getResources().getString(R.string.select_start));
                startTimePicker.show();
            }
        });

        endButton = (Button) view.findViewById(R.id.end_edit);
        endButton.setText(period.getEndString(getActivity()));
        endButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //newEnd = period.getEnd();
                int hour = newEnd / 60; //TODO: 12 hr
                int minute = newEnd % 60;
                TimePickerDialog endTimePicker;
                endTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        int oldNewEnd = newEnd;
                        newEnd = selectedHour * 60 + selectedMinute;
                        if(newEnd<8*60 || newEnd>15*60+32){
                            Toast.makeText(getActivity(), getResources().getString(time24 ? R.string.insane_time24 : R.string.insane_time12), Toast.LENGTH_LONG).show();
                            newEnd = oldNewEnd;
                        }
                        if(newStart>newEnd){
                            Toast.makeText(getActivity(), getResources().getString(R.string.misordered_time), Toast.LENGTH_LONG).show(); //TODO: 24hr compat
                            newEnd = oldNewEnd;
                        }
                        endButton.setText( (((newEnd/60 - 1) % (time24 ? 24 : 12)) + 1) + ":" + (newEnd%60<10 ? "0" : "") + newEnd%60);
                    }
                }, hour, minute, time24);
                endTimePicker.setTitle(getResources().getString(R.string.select_end));
                endTimePicker.show();
            }
        });

        typeSpinner = (Spinner) view.findViewById(R.id.type_edit);
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.types, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);
        typeSpinner.setSelection(period.getType());
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newType = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        colorButton= (Button) view.findViewById(R.id.color_edit);
        colorButton.setBackgroundColor(customMethods.getPerColor(period));
        colorButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new ColorPickerDialog(getActivity(), EditPeriodFragment.this, "key", customMethods.getPerColor(period), customMethods.getPerColor(period)).show();
                //colorButton.setBackgroundColor();
                //TODO: update color of button
            }
        });

        Button save = (Button) view.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() { //TODO: check time is in day AND start is before end
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                Resources r = getResources();
                b.putString(r.getString(R.string.bdl_name),classEdit.getText().toString());
                b.putString(r.getString(R.string.bdl_room),roomEdit.getText().toString());
                b.putInt(r.getString(R.string.bdl_start), newStart);
                b.putInt(r.getString(R.string.bdl_end), newEnd);
                b.putInt(r.getString(R.string.bdl_type), newType);
                b.putBoolean(r.getString(R.string.bdl_set_color),setColor);
                b.putInt(r.getString(R.string.bdl_color),newColor);

                Log.d("EPF 132","end "+newEnd+" bundle "+b.toString());
                EditDayViewFragment.updatePeriod(dayIndex, perIndex, b, r);

                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        Button cancel = (Button) view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }

    public void sendArgs(Period p, int dayIndex, int perIndex){
        period = p;
        this.dayIndex = dayIndex;
        this.perIndex = perIndex;
    }

    @Override
    public void colorChanged(String key, int color) {
        newColor = color;
        setColor = true;
        colorButton.setBackgroundColor(color);
        //Log.d("EditPeriodFragment 228",key+": "+color);
    }
}
