package doop.aa_schedule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class GetScheduleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_get_schedule,container,false);

        getActivity().setTitle(R.string.get_sch_title);

        Button template10_12 = (Button) view.findViewById(R.id.get_sch_10_12);
        Button template10_12_offset = (Button) view.findViewById(R.id.get_sch_10_12_offset);
        Button template8_9 = (Button) view.findViewById(R.id.get_sch_8_9);
        Button template6_7 = (Button) view.findViewById(R.id.get_sch_6_7);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String key="retUser";
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean(key, false);
        editor.apply();

        template10_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomMethods customMethods = new CustomMethods();
                if (!customMethods.saveJSONSchedule(
                    "[[{\"per_start_time\":480,\"per_end_time\":554,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":561,\"per_end_time\":635,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":675,\"per_class_name\":\"Club\",\"per_block\":8,\"per_is_free\":false}," +
                    "{\"per_start_time\":682,\"per_end_time\":729,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":736,\"per_end_time\":770,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":777,\"per_end_time\":824,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":831,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":527,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":534,\"per_end_time\":581,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":588,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":689,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":696,\"per_end_time\":743,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":750,\"per_end_time\":797,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":804,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":527,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":534,\"per_end_time\":581,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":588,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":645,\"per_end_time\":675,\"per_class_name\":\"Assembly/Class Meeting\",\"per_block\":8,\"per_is_free\":false}," +
                    "{\"per_start_time\":682,\"per_end_time\":756,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":763,\"per_end_time\":797,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":804,\"per_end_time\":851,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":858,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":554,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":561,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":689,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":696,\"per_end_time\":770,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":777,\"per_end_time\":824,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":831,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":527,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":534,\"per_end_time\":581,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":588,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":689,\"per_class_name\":\"Common Time\",\"per_block\":8,\"per_is_free\":false}," +
                    "{\"per_start_time\":696,\"per_end_time\":743,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":750,\"per_end_time\":824,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":831,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":527,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":534,\"per_end_time\":581,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":588,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":689,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":696,\"per_end_time\":743,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":750,\"per_end_time\":797,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":804,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"Learning Seminar\",\"per_block\":8,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":554,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":561,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":675,\"per_class_name\":\"Club\",\"per_block\":8,\"per_is_free\":false}," +
                    "{\"per_start_time\":682,\"per_end_time\":729,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":736,\"per_end_time\":770,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":777,\"per_end_time\":824,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":831,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":527,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":534,\"per_end_time\":581,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":588,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":716,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":723,\"per_end_time\":770,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":777,\"per_end_time\":824,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":831,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":527,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":534,\"per_end_time\":581,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":588,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":675,\"per_class_name\":\"Advisor Time\",\"per_block\":8,\"per_is_free\":false}," +
                    "{\"per_start_time\":682,\"per_end_time\":729,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":736,\"per_end_time\":770,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":777,\"per_end_time\":851,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":858,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":527,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":534,\"per_end_time\":581,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":588,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":689,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":696,\"per_end_time\":770,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":777,\"per_end_time\":824,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":831,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]]"
                        , getActivity())) {

                    Log.e("GetScheduleFragment 309", "Error in saving sharedpreference");
                    Toast.makeText(getActivity(), "Unable to save changes. Please try again and report this bug. Sorry :(", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(getActivity(), getResources().getString(R.string.PSA_re_editing), Toast.LENGTH_LONG).show();

                CustomMethods.clearDayNotes(getActivity());
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        template10_12_offset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomMethods customMethods = new CustomMethods();
                if (!customMethods.saveJSONSchedule(
                    "[[{\"per_start_time\":480,\"per_end_time\":554,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":561,\"per_end_time\":635,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":675,\"per_class_name\":\"Club\",\"per_block\":8,\"per_is_free\":false}," +
                    "{\"per_start_time\":682,\"per_end_time\":716,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":723,\"per_end_time\":770,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":777,\"per_end_time\":824,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":831,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":527,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":534,\"per_end_time\":581,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":588,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":635,\"per_end_time\":652,\"per_class_name\":\"Break\",\"per_block\":8,\"per_is_free\":true}," +
                    "{\"per_start_time\":652,\"per_end_time\":699,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":706,\"per_end_time\":743,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":750,\"per_end_time\":797,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":804,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":527,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":534,\"per_end_time\":581,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":588,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":645,\"per_end_time\":675,\"per_class_name\":\"Assembly/Class Meeting\",\"per_block\":8,\"per_is_free\":false}," +
                    "{\"per_start_time\":682,\"per_end_time\":716,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":723,\"per_end_time\":797,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":804,\"per_end_time\":851,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":858,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":554,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":561,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":635,\"per_end_time\":652,\"per_class_name\":\"Break\",\"per_block\":8,\"per_is_free\":true}," +
                    "{\"per_start_time\":652,\"per_end_time\":699,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":706,\"per_end_time\":770,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":777,\"per_end_time\":824,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":831,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":527,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":534,\"per_end_time\":581,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":588,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":689,\"per_class_name\":\"Common Time\",\"per_block\":8,\"per_is_free\":false}," +
                    "{\"per_start_time\":696,\"per_end_time\":743,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":750,\"per_end_time\":824,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":831,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":527,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":534,\"per_end_time\":581,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":588,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":635,\"per_end_time\":652,\"per_class_name\":\"Break\",\"per_block\":8,\"per_is_free\":true}," +
                    "{\"per_start_time\":652,\"per_end_time\":699,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":706,\"per_end_time\":743,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":750,\"per_end_time\":797,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":804,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"Learning Seminar\",\"per_block\":8,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":554,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":561,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":675,\"per_class_name\":\"Club\",\"per_block\":8,\"per_is_free\":false}," +
                    "{\"per_start_time\":682,\"per_end_time\":716,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":723,\"per_end_time\":770,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":777,\"per_end_time\":824,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":831,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":527,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":534,\"per_end_time\":581,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":588,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":635,\"per_end_time\":652,\"per_class_name\":\"Break\",\"per_block\":8,\"per_is_free\":true}," +
                    "{\"per_start_time\":652,\"per_end_time\":726,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":733,\"per_end_time\":770,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":777,\"per_end_time\":824,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":831,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":527,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":534,\"per_end_time\":581,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":588,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":675,\"per_class_name\":\"Advisor Time\",\"per_block\":8,\"per_is_free\":false}," +
                    "{\"per_start_time\":682,\"per_end_time\":716,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":723,\"per_end_time\":770,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":777,\"per_end_time\":851,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":858,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":527,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":534,\"per_end_time\":581,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":588,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":689,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":696,\"per_end_time\":770,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":777,\"per_end_time\":824,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":831,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]]"
                        , getActivity())) {
                    Log.e("GetScheduleFragment 309", "Error in saving sharedpreference");
                    Toast.makeText(getActivity(), "Unable to save changes. Please try again and report this bug. Sorry :(", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(getActivity(), getResources().getString(R.string.PSA_re_editing), Toast.LENGTH_LONG).show();

                CustomMethods.clearDayNotes(getActivity());
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        template8_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomMethods customMethods = new CustomMethods();
                if(!customMethods.saveJSONSchedule(
                    "[[{\"per_start_time\":480,\"per_end_time\":554,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":561,\"per_end_time\":635,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":682,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":689,\"per_end_time\":716,\"per_class_name\":\"Club\",\"per_block\":8,\"per_is_free\":false}," +
                    "{\"per_start_time\":723,\"per_end_time\":770,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":777,\"per_end_time\":824,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":831,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":527,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":534,\"per_end_time\":581,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":588,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":682,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":682,\"per_end_time\":696,\"per_class_name\":\"Free\",\"per_block\":8,\"per_is_free\":true}," +
                    "{\"per_start_time\":696,\"per_end_time\":743,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":750,\"per_end_time\":797,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":804,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":527,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":534,\"per_end_time\":581,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":588,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":682,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":689,\"per_end_time\":716,\"per_class_name\":\"Advisor Time\",\"per_block\":8,\"per_is_free\":false}," +
                    "{\"per_start_time\":723,\"per_end_time\":797,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":804,\"per_end_time\":851,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":858,\"per_end_time\":878,\"per_class_name\":\"Free\",\"per_block\":8,\"per_is_free\":true}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":554,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":561,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":682,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":689,\"per_end_time\":716,\"per_class_name\":\"Club\",\"per_block\":8,\"per_is_free\":false}," +
                    "{\"per_start_time\":723,\"per_end_time\":770,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":777,\"per_end_time\":824,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":831,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":527,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":534,\"per_end_time\":581,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":588,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":682,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":682,\"per_end_time\":696,\"per_class_name\":\"Free\",\"per_block\":8,\"per_is_free\":true}," +
                    "{\"per_start_time\":696,\"per_end_time\":743,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":750,\"per_end_time\":824,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":831,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":527,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":534,\"per_end_time\":581,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":588,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":682,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":689,\"per_end_time\":743,\"per_class_name\":\"Common Time\",\"per_block\":8,\"per_is_free\":false}," +
                    "{\"per_start_time\":750,\"per_end_time\":797,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":804,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":554,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":561,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":682,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":689,\"per_end_time\":716,\"per_class_name\":\"Club\",\"per_block\":8,\"per_is_free\":false}," +
                    "{\"per_start_time\":723,\"per_end_time\":770,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":777,\"per_end_time\":824,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":831,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":527,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":534,\"per_end_time\":581,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":588,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":682,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":682,\"per_end_time\":696,\"per_class_name\":\"Free\",\"per_block\":8,\"per_is_free\":true}," +
                    "{\"per_start_time\":696,\"per_end_time\":770,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":777,\"per_end_time\":824,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":831,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":527,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":534,\"per_end_time\":581,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":588,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":682,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":689,\"per_end_time\":716,\"per_class_name\":\"Grade Assembly\",\"per_block\":8,\"per_is_free\":false}," +
                    "{\"per_start_time\":723,\"per_end_time\":770,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":777,\"per_end_time\":851,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":858,\"per_end_time\":878,\"per_class_name\":\"Free\",\"per_block\":8,\"per_is_free\":true}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"Advisor Study Hall\",\"per_block\":6,\"per_is_free\":false}]," +

                    "[{\"per_start_time\":480,\"per_end_time\":527,\"per_class_name\":\"A\",\"per_block\":0,\"per_is_free\":false}," +
                    "{\"per_start_time\":534,\"per_end_time\":581,\"per_class_name\":\"B\",\"per_block\":1,\"per_is_free\":false}," +
                    "{\"per_start_time\":588,\"per_end_time\":635,\"per_class_name\":\"C\",\"per_block\":2,\"per_is_free\":false}," +
                    "{\"per_start_time\":642,\"per_end_time\":682,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
                    "{\"per_start_time\":689,\"per_end_time\":716,\"per_class_name\":\"Common Time\",\"per_block\":8,\"per_is_free\":false}," +
                    "{\"per_start_time\":723,\"per_end_time\":770,\"per_class_name\":\"D\",\"per_block\":3,\"per_is_free\":false}," +
                    "{\"per_start_time\":777,\"per_end_time\":824,\"per_class_name\":\"E\",\"per_block\":4,\"per_is_free\":false}," +
                    "{\"per_start_time\":831,\"per_end_time\":878,\"per_class_name\":\"F\",\"per_block\":5,\"per_is_free\":false}," +
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"G\",\"per_block\":6,\"per_is_free\":false}]]"

                        ,getActivity())){
                    Log.e("GetScheduleFragment 947", "Error in saving sharedpreference");
                    Toast.makeText(getActivity(), "Unable to save changes. Please try again and report this bug. Sorry :(", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(getActivity(), getResources().getString(R.string.PSA_re_editing), Toast.LENGTH_LONG).show();

                CustomMethods.clearDayNotes(getActivity());
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        template6_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomMethods customMethods = new CustomMethods();
                ArrayList<ArrayList<Period>> schedule = new ArrayList<>(0);
                ArrayList<Period> day1 = new ArrayList<>();
                ArrayList<Period> day2 = new ArrayList<>();
                ArrayList<Period> day3 = new ArrayList<>();
                ArrayList<Period> day4 = new ArrayList<>();
                ArrayList<Period> day5 = new ArrayList<>();
                ArrayList<Period> day6 = new ArrayList<>();
                ArrayList<Period> day7 = new ArrayList<>();
                ArrayList<Period> day8 = new ArrayList<>();
                ArrayList<Period> day9 = new ArrayList<>();
                ArrayList<Period> day0 = new ArrayList<>();

                day1.add(new Period(480, 932, "Free", 0, true));
                day2.add(new Period(480, 932, "Free", 1, true));
                day3.add(new Period(480, 932, "Free", 2, true));
                day4.add(new Period(480, 932, "Free", 3, true));
                day5.add(new Period(480, 932, "Free", 4, true));
                day6.add(new Period(480, 932, "Free", 5, true));
                day7.add(new Period(480, 932, "Free", 6, true));
                day8.add(new Period(480, 932, "Free", 7, true));
                day9.add(new Period(480, 932, "Free", 8, true));
                day0.add(new Period(480, 932, "Free", 8, true));

                schedule.add(day1);
                schedule.add(day2);
                schedule.add(day3);
                schedule.add(day4);
                schedule.add(day5);
                schedule.add(day6);
                schedule.add(day7);
                schedule.add(day8);
                schedule.add(day9);
                schedule.add(day0);


                Toast.makeText(getActivity(), "Lol :P", Toast.LENGTH_SHORT).show();
                if (!customMethods.saveSchedule(schedule, getActivity(), "GSF 67")) {
                    Log.e("GetScheduleFragment 947", "Error in saving sharedpreference");
                    Toast.makeText(getActivity(), "Unable to save changes. Please try again and report this bug. Sorry :(", Toast.LENGTH_LONG).show();
                }

                CustomMethods.clearDayNotes(getActivity());
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
