package doop.aa_schedule;

import android.app.TimePickerDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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
    CheckBox freeEdit;
    Button colorButton;
    CustomMethods customMethods = new CustomMethods();
    PauseViewPager mViewPager;

    /*String newClass;
    String newRoom;*/
    int newStart;
    int newEnd;
    int newColor;
    boolean setColor = false;
    boolean time24=false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        time24 = customMethods.time24(getActivity());
        newStart = period.getStart();
        newEnd = period.getEnd();
        View view=inflater.inflate(R.layout.fragment_edit_period,container,false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Edit Period");

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
                int hour = newEnd / 60;
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
                            Toast.makeText(getActivity(), getResources().getString(R.string.misordered_time), Toast.LENGTH_LONG).show();
                            newEnd = oldNewEnd;
                        }
                        endButton.setText( (((newEnd/60 - 1) % (time24 ? 24 : 12)) + 1) + ":" + (newEnd%60<10 ? "0" : "") + newEnd%60);
                    }
                }, hour, minute, time24);
                endTimePicker.setTitle(getResources().getString(R.string.select_end));
                endTimePicker.show();
            }
        });

        freeEdit = (CheckBox) view.findViewById(R.id.free_edit);
        freeEdit.setChecked(period.isFree());
        freeEdit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(classEdit.getText().toString().equals(period.getClassName()))
                        classEdit.setText("Free");
                    if(roomEdit.getText().toString().equals(period.getRoom()))
                        roomEdit.setText("");
                    if(!setColor && !period.hasColor())
                        colorButton.setBackgroundColor(customMethods.getPerColor(new Period(0,0,"",period.getBlock(),true)));
                } else {
                    if(classEdit.getText().toString().equals("Free"))
                        classEdit.setText(period.getClassName());
                    if(roomEdit.getText().toString().equals(""))
                        roomEdit.setText(period.getRoom());
                    if(!setColor && !period.hasColor())
                        colorButton.setBackgroundColor(customMethods.getPerColor(new Period(0,0,"",period.getBlock(),false)));
                }
            }
        });

        colorButton= (Button) view.findViewById(R.id.color_edit);
        colorButton.setBackgroundColor(customMethods.getPerColor(period));
        colorButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new ColorPickerDialog(getActivity(), EditPeriodFragment.this, "key", customMethods.getPerColor(period), customMethods.getPerColor(period)).show();
            }
        });

        Button save = (Button) view.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                Resources r = getResources();
                b.putString(r.getString(R.string.bdl_name),classEdit.getText().toString());
                b.putString(r.getString(R.string.bdl_room),roomEdit.getText().toString());
                b.putInt(r.getString(R.string.bdl_start), newStart);
                b.putInt(r.getString(R.string.bdl_end), newEnd);
                b.putBoolean(r.getString(R.string.bdl_free), freeEdit.isChecked());
                b.putBoolean(r.getString(R.string.bdl_set_color), setColor);
                b.putInt(r.getString(R.string.bdl_color), newColor);

                EditDayViewFragment.updatePeriod(dayIndex, perIndex, b, getActivity());

                ((MainActivity) getActivity()).getSupportActionBar().setTitle("Edit Day "+(dayIndex+1)%10);
                mViewPager.setPagingAllowed(true);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        Button cancel = (Button) view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).getSupportActionBar().setTitle("Edit Day "+(dayIndex+1)%10);
                mViewPager.setPagingAllowed(true);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }

    public void sendArgs(Period p, int dayIndex, int perIndex, PauseViewPager vp){
        this.period = p;
        this.dayIndex = dayIndex;
        this.perIndex = perIndex;
        this.mViewPager = vp;
    }

    @Override
    public void colorChanged(String key, int color) {
        newColor = color;
        setColor = true;
        colorButton.setBackgroundColor(color);
        //Log.d("EditPeriodFragment 228",key+": "+color);
    }
}
