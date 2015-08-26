package doop.aa_schedule;

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


public class EditBlockFragment extends Fragment implements ColorPickerDialog.OnColorChangedListener {
    Period period;
    int block;
    EditText classEdit;
    EditText roomEdit;
    CheckBox freeEdit;
    Button colorButton;
    CustomMethods customMethods = new CustomMethods();
    PauseViewPager mViewPager;

    int newColor;
    boolean setColor = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view=inflater.inflate(R.layout.fragment_edit_block,container,false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Edit Block");

        classEdit = (EditText) view.findViewById(R.id.class_edit);
        classEdit.setText(period.getClassName());

        roomEdit = (EditText) view.findViewById(R.id.room_edit);
        roomEdit.setText(period.getRoom());

        freeEdit = (CheckBox) view.findViewById(R.id.free_edit);
        freeEdit.setChecked(period.isFree());
        freeEdit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (classEdit.getText().toString().equals(period.getClassName()))
                        classEdit.setText("Free");
                    if (roomEdit.getText().toString().equals(period.getRoom()))
                        roomEdit.setText("");
                    if (!setColor && !period.hasColor())
                        colorButton.setBackgroundColor(customMethods.getPerColor(new Period(0, 0, "", period.getBlock(), true)));
                } else {
                    if (classEdit.getText().toString().equals("Free"))
                        classEdit.setText(period.getClassName());
                    if (roomEdit.getText().toString().equals(""))
                        roomEdit.setText(period.getRoom());
                    if (!setColor && !period.hasColor())
                        colorButton.setBackgroundColor(customMethods.getPerColor(new Period(0, 0, "", period.getBlock(), false)));
                }
            }
        });

        colorButton= (Button) view.findViewById(R.id.color_edit);
        colorButton.setBackgroundColor(customMethods.getPerColor(period));
        colorButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new ColorPickerDialog(getActivity(), EditBlockFragment.this, "key", customMethods.getPerColor(period), customMethods.getPerColor(period)).show();
            }
        });

        Button save = (Button) view.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                Resources r = getResources();
                if(period.getBlock()!=8) {
                    b.putString(r.getString(R.string.bdl_name), classEdit.getText().toString());
                    b.putString(r.getString(R.string.bdl_room), roomEdit.getText().toString());
                    b.putBoolean(r.getString(R.string.bdl_free), freeEdit.isChecked());
                    b.putBoolean(r.getString(R.string.bdl_free_changed), freeEdit.isChecked() != period.isFree());
                } else {
                    b.putBoolean(r.getString(R.string.bdl_free_changed), false);
                }
                b.putBoolean(r.getString(R.string.bdl_set_color), setColor);
                b.putInt(r.getString(R.string.bdl_color), newColor);

                EditBlocksPage.updateBlock(block, b, r);
                ((MainActivity) getActivity()).getSupportActionBar().setTitle("Edit Blocks");
                mViewPager.setPagingAllowed(true);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        Button cancel = (Button) view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).getSupportActionBar().setTitle("Edit Blocks");
                mViewPager.setPagingAllowed(true);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }

    public void sendArgs(Period p, int block, PauseViewPager vp){
        this.period = p;
        this.block = block;
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
