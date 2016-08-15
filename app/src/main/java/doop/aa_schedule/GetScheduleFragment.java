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

        //Button download = (Button) view.findViewById(R.id.get_sch_download);
        Button template10_12 = (Button) view.findViewById(R.id.get_sch_10_12);
        Button template10_12_offset = (Button) view.findViewById(R.id.get_sch_10_12_offset);
        Button template8_9 = (Button) view.findViewById(R.id.get_sch_8_9);
        Button template6_7 = (Button) view.findViewById(R.id.get_sch_6_7);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String key="retUser";
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean(key, false);
        editor.apply();

        //Button sample = (Button) view.findViewById(R.id.get_sch_sample);
        /*download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "This button does nothing. Don't tell anyone.", Toast.LENGTH_SHORT).show();
                /*CustomMethods customMethods = new CustomMethods();
                ArrayList<ArrayList<Period>> schedule = new ArrayList<>(0);

                if (!customMethods.saveSchedule(schedule, getActivity())) {
                    Log.e("EditBlocksPage 142", "Error in saving sharedpreference");
                    Toast.makeText(getActivity(), "Unable to save changes. Please try again and report this bug. Sorry :(", Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent); * /
            }
        });*/
        template10_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomMethods customMethods = new CustomMethods();
                /*ArrayList<ArrayList<Period>> schedule = new ArrayList<>(0);

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

                Period AShort = new Period(480, 527, "A", 0, false);
                Period ALong = new Period(480, 554, "A", 0, false);

                Period BShort = new Period(534, 581, "B", 1, false);
                Period BLong1 = new Period(480, 554, "B", 1, false);
                Period BLong2 = new Period(561, 635, "B", 1, false);

                Period CShort = new Period(588, 635, "C", 2, false);
                Period CLong = new Period(561, 635, "C", 2, false);

                //These are all the left ones
                Period DShort1 = new Period(642, 689, "D", 3, false);
                Period DShort2 = new Period(682, 729, "D", 3, false);
                Period DLong = new Period(642, 716, "D", 3, false);

                Period EShort1 = new Period(750, 797, "E", 4, false);
                Period EShort2 = new Period(777, 824, "E", 4, false);
                Period ELong1 = new Period(750, 824, "E", 4, false);
                Period ELong2 = new Period(777, 851, "E", 4, false);

                Period FShort1 = new Period(804, 851, "F", 5, false);
                Period FShort2 = new Period(831, 878, "F", 5, false);
                Period FLong = new Period(804, 878, "F", 5, false);

                Period GShort = new Period(885, 932, "G", 6, false);
                Period GLong = new Period(858, 932, "G", 6, false);

                Period Lunch1 = new Period(736, 770, "Lunch", 7, false);
                Period Lunch2 = new Period(723, 797, "Lunch", 7, false);
                Period Lunch4 = new Period(696, 743, "Lunch", 7, false);
                Period Lunch6 = new Period(723, 770, "Lunch", 7, false);

                Period DShort0 = new Period(655, 702, "D", 3, false);
                Period Break = new Period(635, 655, "Break", 8, true);
                Period Lunch0 = new Period(709, 770, "Lunch", 7, false);

                Period Advisor = new Period(645, 675, "Common Time", 8, false);
                Period DivAss = new Period(645, 675, "Common Time", 8, false);
                Period Club = new Period(642, 675, "Club", 8, false);
                Period CommonTime = new Period(885, 932, "Common Time", 8, false);
                Period Other = new Period(642, 689, "Other", 8, false);

                day1.add(ALong); day1.add(BLong2); day1.add(Club); day1.add(DShort2); day1.add(Lunch1);
                day1.add(EShort2); day1.add(FShort2); day1.add(GShort);
                day2.add(AShort); day2.add(BShort); day2.add(CShort); day2.add(DLong); day2.add(Lunch2);
                day2.add(FLong); day2.add(GShort);
                day3.add(AShort); day3.add(BShort); day3.add(CShort); day3.add(DivAss); day3.add(DShort2);
                day3.add(Lunch1); day3.add(EShort2); day3.add(FShort2); day3.add(GShort);
                day4.add(ALong); day4.add(CLong); day4.add(DShort1); day4.add(Lunch4);
                day4.add(ELong1); day4.add(FShort2); day4.add(GShort);
                day5.add(AShort); day5.add(BShort); day5.add(CShort); day5.add(Other); day5.add(Lunch4);
                day5.add(EShort1); day5.add(FShort1); day5.add(GLong);
                day6.add(AShort); day6.add(BShort); day6.add(CShort); day6.add(DLong); day6.add(Lunch6);
                day6.add(EShort2); day6.add(FShort2); day6.add(GShort);
                day7.add(BLong1); day7.add(CLong); day7.add(Club); day7.add(DShort2); day7.add(Lunch1);
                day7.add(EShort2); day7.add(FShort2); day7.add(GShort);
                day8.add(AShort); day8.add(BShort); day8.add(CShort); day8.add(DShort1); day8.add(Lunch4);
                day8.add(EShort1); day8.add(FLong); day8.add(CommonTime);
                day9.add(AShort); day9.add(BShort); day9.add(CShort); day9.add(Advisor); day9.add(DShort2);
                day9.add(Lunch1); day9.add(ELong2); day9.add(GLong);
                day0.add(AShort); day0.add(BShort); day0.add(CShort); day0.add(Break); day0.add(DShort0); day0.add(Lunch0);
                day0.add(EShort2); day0.add(FShort2); day0.add(GShort);

                schedule.add(day1);
                schedule.add(day2);
                schedule.add(day3);
                schedule.add(day4);
                schedule.add(day5);
                schedule.add(day6);
                schedule.add(day7);
                schedule.add(day8);
                schedule.add(day9);
                schedule.add(day0);*/

                //if (!customMethods.saveSchedule(schedule, getActivity(), "GSF 1012")) {
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
                    "{\"per_start_time\":645,\"per_end_time\":675,\"per_class_name\":\"Common Time\",\"per_block\":8,\"per_is_free\":false}," +
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
                    "{\"per_start_time\":635,\"per_end_time\":652,\"per_class_name\":\"Break\",\"per_block\":8,\"per_is_free\":true}," +
                    "{\"per_start_time\":652,\"per_end_time\":699,\"per_class_name\":\"Other\",\"per_block\":8,\"per_is_free\":false}," +
                    "{\"per_start_time\":706,\"per_end_time\":743,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
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
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"Common Time\",\"per_block\":8,\"per_is_free\":false}]," +

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
                    "{\"per_start_time\":645,\"per_end_time\":675,\"per_class_name\":\"Common Time\",\"per_block\":8,\"per_is_free\":false}," +
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
                /*ArrayList<ArrayList<Period>> schedule = new ArrayList<>(0);

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

                Period AShort = new Period(480, 527, "A", 0, false);
                Period ALong = new Period(480, 554, "A", 0, false);

                Period BShort = new Period(534, 581, "B", 1, false);
                Period BLong1 = new Period(480, 554, "B", 1, false);
                Period BLong2 = new Period(561, 635, "B", 1, false);

                Period CShort = new Period(588, 635, "C", 2, false);
                Period CLong = new Period(561, 635, "C", 2, false);

                //These are all the right ones
                Period DShort1 = new Period(655, 702, "D", 3, false);
                Period DShort2 = new Period(723, 770, "D", 3, false);
                Period DLong = new Period(655, 729, "D", 3, false);

                Period EShort1 = new Period(750, 797, "E", 4, false);
                Period EShort2 = new Period(777, 824, "E", 4, false);
                Period ELong1 = new Period(750, 824, "E", 4, false);
                Period ELong2 = new Period(777, 851, "E", 4, false);

                Period FShort1 = new Period(804, 851, "F", 5, false);
                Period FShort2 = new Period(831, 878, "F", 5, false);
                Period FLong = new Period(804, 878, "F", 5, false);

                Period GShort = new Period(885, 932, "G", 6, false);
                Period GLong = new Period(858, 932, "G", 6, false);

                Period Lunch1 = new Period(682, 716, "Lunch", 7, false);
                Period Lunch2 = new Period(736, 797, "Lunch", 7, false);
                Period Lunch4 = new Period(709, 743, "Lunch", 7, false);
                Period Lunch6 = new Period(736, 770, "Lunch", 7, false);
                Period Lunch0 = new Period(709, 770, "Lunch", 7, false);

                Period Advisor = new Period(645, 675, "Common Time", 8, false);
                Period Break = new Period(635, 655, "Break", 8, true);
                Period DivAss = new Period(645, 675, "Common Time", 8, false);
                Period Club = new Period(642, 675, "Club", 8, false);
                Period CommonTime = new Period(885, 932, "Common Time", 8, false);
                Period Other = new Period(655, 702, "Other", 8, false);

                day1.add(ALong); day1.add(BLong2); day1.add(Club); day1.add(Lunch1); day1.add(DShort2);
                day1.add(EShort2); day1.add(FShort2); day1.add(GShort);
                day2.add(AShort); day2.add(BShort); day2.add(CShort); day2.add(Break); day2.add(DLong); day2.add(Lunch2);
                day2.add(FLong); day2.add(GShort);
                day3.add(AShort); day3.add(BShort); day3.add(CShort); day3.add(DivAss); day3.add(Lunch1); day3.add(DShort2);
                day3.add(EShort2); day3.add(FShort2); day3.add(GShort);
                day4.add(ALong); day4.add(CLong); day4.add(Break); day4.add(DShort1); day4.add(Lunch4);
                day4.add(ELong1); day4.add(FShort2); day4.add(GShort);
                day5.add(AShort); day5.add(BShort); day5.add(CShort); day5.add(Break); day5.add(Other); day5.add(Lunch4);
                day5.add(EShort1); day5.add(FShort1); day5.add(GLong);
                day6.add(AShort); day6.add(BShort); day6.add(CShort); day6.add(Break); day6.add(DLong); day6.add(Lunch6);
                day6.add(EShort2); day6.add(FShort2); day6.add(GShort);
                day7.add(BLong1); day7.add(CLong); day7.add(Club); day7.add(Lunch1); day7.add(DShort2);
                day7.add(EShort2); day7.add(FShort2); day7.add(GShort);
                day8.add(AShort); day8.add(BShort); day8.add(CShort); day8.add(Break); day8.add(DShort1); day8.add(Lunch4);
                day8.add(EShort1); day8.add(FLong); day8.add(CommonTime);
                day9.add(AShort); day9.add(BShort); day9.add(CShort); day9.add(Advisor); day9.add(Lunch1); day9.add(DShort2);
                day9.add(ELong2); day9.add(GLong);
                day0.add(AShort); day0.add(BShort); day0.add(CShort); day0.add(Break); day0.add(DShort1); day0.add(Lunch0);
                day0.add(EShort2); day0.add(FShort2); day0.add(GShort);

                schedule.add(day1);
                schedule.add(day2);
                schedule.add(day3);
                schedule.add(day4);
                schedule.add(day5);
                schedule.add(day6);
                schedule.add(day7);
                schedule.add(day8);
                schedule.add(day9);
                schedule.add(day0);*/

                //if (!customMethods.saveSchedule(schedule, getActivity(), "GSF 1012")) {
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
                    "{\"per_start_time\":645,\"per_end_time\":675,\"per_class_name\":\"Common Time\",\"per_block\":8,\"per_is_free\":false}," +
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
                    "{\"per_start_time\":635,\"per_end_time\":652,\"per_class_name\":\"Break\",\"per_block\":8,\"per_is_free\":true}," +
                    "{\"per_start_time\":652,\"per_end_time\":699,\"per_class_name\":\"Other\",\"per_block\":8,\"per_is_free\":false}," +
                    "{\"per_start_time\":706,\"per_end_time\":743,\"per_class_name\":\"Lunch\",\"per_block\":7,\"per_is_free\":false}," +
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
                    "{\"per_start_time\":885,\"per_end_time\":932,\"per_class_name\":\"Common Time\",\"per_block\":8,\"per_is_free\":false}]," +

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
                    "{\"per_start_time\":645,\"per_end_time\":675,\"per_class_name\":\"Common Time\",\"per_block\":8,\"per_is_free\":false}," +
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
                /*ArrayList<ArrayList<Period>> schedule = new ArrayList<>(0); //TODO: get an actual schedule and confirm all the weird ones

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

                Period AShort = new Period(480, 527, "A", 0, false);
                Period ALong = new Period(480, 554, "A", 0, false);

                Period BShort = new Period(534, 581, "B", 1, false);
                Period BLong1 = new Period(480, 554, "B", 1, false);
                Period BLong2 = new Period(561, 635, "B", 1, false);

                Period CShort = new Period(588, 635, "C", 2, false);
                Period CLong = new Period(561, 635, "C", 2, false);

                Period DShort1 = new Period(696, 743, "D", 3, false);
                Period DShort2 = new Period(723, 770, "D", 3, false);
                Period DLong1 = new Period(696, 770, "D", 3, false);
                Period DLong2 = new Period(723, 797, "D", 3, false);

                Period EShort1 = new Period(750, 797, "E", 4, false);
                Period EShort2 = new Period(777, 824, "E", 4, false);
                Period ELong1 = new Period(750, 824, "E", 4, false);
                Period ELong2 = new Period(777, 851, "E", 4, false);

                Period FShort1 = new Period(804, 851, "F", 5, false);
                Period FShort2 = new Period(831, 878, "F", 5, false);
                Period FLong = new Period(804, 878, "F", 5, false);

                Period GShort = new Period(885, 932, "G", 6, false);

                Period Lunch = new Period(642, 682, "Lunch", 7, false);
                Period Advisor = new Period(689, 716, "Advisory Time", 8, false);
                Period Assembly = new Period(689, 716, "Grade Assembly", 8, false);
                Period Club = new Period(689, 716, "Club", 8, false);
                Period CommonTime = new Period(689, 743, "Common Time", 8, false);
                Period DinkyFree = new Period(682, 696, "Free", 8, true);
                Period OtherFree = new Period(858, 878, "Free", 8, true);
                Period Free0 = new Period(689, 716, "Free", 8, true);

                day1.add(ALong); day1.add(BLong2); day1.add(Lunch); day1.add(Advisor); day1.add(DShort2);
                            day1.add(EShort2); day1.add(FShort2); day1.add(GShort);
                day2.add(AShort); day2.add(BShort); day2.add(CShort); day2.add(Lunch); day2.add(Assembly);
                            day2.add(DLong2); day2.add(FLong); day2.add(GShort);
                day3.add(AShort); day3.add(BShort); day3.add(CShort); day3.add(Lunch); day3.add(Club);
                            day3.add(DShort2); day3.add(EShort2); day3.add(FShort2); day3.add(GShort);
                day4.add(ALong); day4.add(CLong); day4.add(Lunch); day4.add(DinkyFree); day4.add(DShort1);
                            day4.add(ELong1); day4.add(FShort2); day4.add(GShort);
                day5.add(AShort); day5.add(BShort); day5.add(CShort); day5.add(Lunch); day5.add(DinkyFree); day5.add(DShort1);
                            day5.add(EShort1); day5.add(FShort1); day5.add(OtherFree); day5.add(GShort);
                day6.add(AShort); day6.add(BShort); day6.add(CShort); day6.add(Lunch); day6.add(DinkyFree); day6.add(DLong1);
                            day6.add(EShort2); day6.add(FShort2); day6.add(GShort);
                day7.add(BLong1); day7.add(CLong); day7.add(Lunch); day7.add(Club); day7.add(DShort2);
                            day7.add(EShort2); day7.add(FShort2); day7.add(GShort);
                day8.add(AShort); day8.add(BShort); day8.add(CShort); day8.add(Lunch); day8.add(CommonTime);
                            day8.add(EShort1); day8.add(FLong); day8.add(GShort);
                day9.add(AShort); day9.add(BShort); day9.add(CShort); day9.add(Lunch); day9.add(Club);
                            day9.add(DShort2); day9.add(ELong2); day9.add(OtherFree); day9.add(GShort);
                day0.add(AShort); day0.add(BShort); day0.add(CShort); day0.add(Lunch); day0.add(Free0); day0.add(DShort2);
                            day0.add(EShort2); day0.add(FShort2); day0.add(GShort);

                schedule.add(day1);
                schedule.add(day2);
                schedule.add(day3);
                schedule.add(day4);
                schedule.add(day5);
                schedule.add(day6);
                schedule.add(day7);
                schedule.add(day8);
                schedule.add(day9);
                schedule.add(day0);*/

                //if (!customMethods.saveSchedule(schedule, getActivity(), "GSF 89")) {
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
                    "{\"per_start_time\":689,\"per_end_time\":716,\"per_class_name\":\"Advisory Time\",\"per_block\":8,\"per_is_free\":false}," +
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

                //Log.d("GSF 488", schedule.toString());


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
        /*sample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomMethods customMethods = new CustomMethods();
                ArrayList<ArrayList<Period>> schedule = new ArrayList<>(0);

                Period AFreeShort = new Period(480, 527, "Free", 0, 2);
                Period AFreeShortCopy = new Period(480, 527, "Free", 0, 2);
                Period AFreeLong = new Period(480, 554, "Free", 0, 2);

                Period BShort1 = new Period(507, 554, "AP Calc AB", 1, 0, "S101");
                Period BShort2 = new Period(534, 581, "AP Calc AB", 1, 0, "S101");
                Period BLong = new Period(561, 635, "AP Calc AB", 1, 0, "S101");
                Period BFree = new Period(480, 507, "Free", 1, 2);

                Period CShort = new Period(588, 635, "Symph Band", 2, 0, "M154");
                Period CFree = new Period(561, 588, "Free", 2, 2);

                Period DFreeShort1 = new Period(642, 689, "Free", 3, 2);
                Period DFreeShort2 = new Period(676, 723, "Free", 3, 2);
                Period DFreeLong = new Period(643, 716, "Free", 3, 2);

                Period EShort1 = new Period(750, 797, "Hums", 4, 0, "M16");
                Period EShort2 = new Period(777, 824, "Hums", 4, 0, "M16");
                Period ELong1 = new Period(750, 824, "Hums", 4, 0, "M16");
                Period ELong2 = new Period(777, 851, "Hums", 4, 0, "M16");
                Period EGroup = new Period(655, 702, "Large Group Hums", 4, 0);
                Period EFree1 = new Period(750, 797, "Free", 4, 2);
                Period EFree2 = new Period(777, 824, "Free", 4, 2);

                Period FShort1 = new Period(804, 851, "AP Physics I", 5, 0, "S212");
                Period FShort2 = new Period(831, 878, "AP Physics I", 5, 0, "S212");
                Period FLong = new Period(804, 878, "AP Physics I", 5, 0, "S212");
                Period FFree = new Period(831, 878, "Free", 5, 2);

                Period GShort = new Period(885, 932, "English IV", 6, 0, "B404");
                Period GLong = new Period(858, 932, "English IV", 6, 0, "B404");
                Period GFree = new Period(885, 932, "Free", 6, 2);

                Period Lunch1 = new Period(696, 743, "Lunch", 7, 1);
                Period Lunch2 = new Period(709, 743, "Lunch", 7, 1);
                Period Lunch3Short = new Period(723, 770, "Lunch", 7, 1);
                Period Lunch3Long = new Period(723, 797, "Lunch", 7, 1);
                Period Lunch4 = new Period(730, 770, "Lunch", 7, 1);
                Period Lunch0 = new Period(696, 770, "Lunch", 7, 1);


                Period Club = new Period(642, 669, "Club", 8, 3); //block 8 and type 3 are both "other"
                Period DivAss = new Period(642, 669, "Division Assembly", 8, 3);
                Period Common = new Period(885, 932, "Common Time", 8, 3);
                Period DinkyFree = new Period(635, 655, "Free", 8, 2);

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

                day1.add(AFreeLong);
                day1.add(BLong);
                day1.add(DivAss);
                day1.add(DFreeShort2);
                day1.add(Lunch4);
                day1.add(EShort2);
                day1.add(FShort2);
                day1.add(GShort);

                day2.add(AFreeShortCopy);
                day2.add(BShort2);
                day2.add(CShort);
                day2.add(DFreeLong);
                day2.add(Lunch3Long);
                day2.add(FLong);
                day2.add(GShort);

                day3.add(AFreeShort);
                day3.add(BShort2);
                day3.add(CShort);
                day3.add(Club);
                day3.add(DFreeShort2);
                day3.add(Lunch4);
                day3.add(EShort2);
                day3.add(FShort2);
                day3.add(GFree);

                day4.add(AFreeLong);
                day4.add(CFree);
                day4.add(CShort);
                day4.add(DFreeShort1);
                day4.add(Lunch1);
                day4.add(ELong1);
                day4.add(FFree);
                day4.add(GShort);

                day5.add(AFreeShort);
                day5.add(BShort2);
                day5.add(CShort);
                day5.add(DinkyFree);
                day5.add(EGroup);
                day5.add(Lunch2);
                day5.add(EShort1);
                day5.add(FShort1);
                day5.add(GLong);

                day6.add(AFreeShort);
                day6.add(BShort2);
                day6.add(CShort);
                day6.add(DFreeLong);
                day6.add(Lunch3Short);
                day6.add(EFree2);
                day6.add(FShort2);
                day6.add(GShort);

                day7.add(BFree);
                day7.add(BShort1);
                day7.add(CFree);
                day7.add(CShort);
                day7.add(DivAss);
                day7.add(DFreeShort2);
                day7.add(Lunch4);
                day7.add(EShort2);
                day7.add(FShort2);
                day7.add(GShort);

                day8.add(AFreeShort);
                day8.add(BShort2);
                day8.add(CShort);
                day8.add(DFreeShort1);
                day8.add(Lunch1);
                day8.add(EFree1);
                day8.add(FLong);
                day8.add(Common);

                day9.add(AFreeShort);
                day9.add(BShort2);
                day9.add(CShort);
                day9.add(Club);
                day9.add(DFreeShort2);
                day9.add(Lunch4);
                day9.add(ELong2);
                day9.add(GLong);

                day0.add(AFreeShort);
                day0.add(BShort2);
                day0.add(CShort);
                day0.add(DFreeShort1);
                day0.add(Lunch0);
                day0.add(EShort2);
                day0.add(FShort2);
                day0.add(GShort);

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

                if (!customMethods.saveSchedule(schedule, getActivity(), "GSF sample")) {
                    Log.e("GetScheduleFragment 468", "Error in saving sharedpreference");
                    Toast.makeText(getActivity(), "Unable to save changes. Please try again and report this bug. Sorry :(", Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });*/

        return view;
    }

}
