package doop.aa_schedule;

import android.content.Intent;
import android.os.Bundle;
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
        Button download = (Button) view.findViewById(R.id.get_sch_download);
        Button template10_12 = (Button) view.findViewById(R.id.get_sch_10_12);
        Button template8_9 = (Button) view.findViewById(R.id.get_sch_8_9);
        Button template6_7 = (Button) view.findViewById(R.id.get_sch_6_7);
        Button sample = (Button) view.findViewById(R.id.get_sch_sample);
        download.setOnClickListener(new View.OnClickListener() {
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
                startActivity(intent); */
            }
        });
        template10_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //TODO: deal with the 2 lunches thing
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

                Period AShort = new Period(480, 527, "A", 0, 0);
                Period ALong = new Period(480, 554, "A", 0, 0);

                Period BShort = new Period(534, 581, "B", 1, 0);
                Period BLong1 = new Period(480, 554, "B", 1, 0);
                Period BLong2 = new Period(561, 635, "B", 1, 0);

                Period CShort = new Period(588, 635, "C", 2, 0);
                Period CLong = new Period(561, 635, "C", 2, 0);

                //These are all the left ones
                Period DShort1 = new Period(642, 689, "D", 3, 0);
                Period DShort2 = new Period(676, 723, "D", 3, 0);
                Period DLong = new Period(642, 716, "D", 3, 0);

                Period EShort1 = new Period(750, 797, "E", 4, 0);
                Period EShort2 = new Period(777, 824, "E", 4, 0);
                Period ELong1 = new Period(750, 824, "E", 4, 0);
                Period ELong2 = new Period(777, 851, "E", 4, 0);

                Period FShort1 = new Period(804, 851, "F", 5, 0);
                Period FShort2 = new Period(831, 878, "F", 5, 0);
                Period FLong = new Period(804, 878, "F", 5, 0);

                Period GShort = new Period(885, 932, "G", 6, 0);
                Period GLong = new Period(858, 932, "G", 6, 0);

                Period Lunch1 = new Period(730, 770, "Lunch", 7, 1);
                Period Lunch2 = new Period(723, 797, "Lunch", 7, 1);
                Period Lunch4 = new Period(696, 743, "Lunch", 7, 1);
                Period Lunch6 = new Period(723, 770, "Lunch", 7, 1);
                Period Lunch0 = new Period(696, 770, "Lunch", 7, 1);

                Period Advisor = new Period(642, 669, "Advisor/Class Meeting", 8, 3);
                Period DivAss = new Period(642, 669, "Division Assembly", 8, 3);
                Period Club = new Period(642, 669, "Club", 8, 3);
                Period CommonTime = new Period(885, 932, "Common Time", 8, 3);
                Period Other = new Period(642, 689, "Other", 8, 3);

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
                day0.add(AShort); day0.add(BShort); day0.add(CShort); day0.add(DShort1); day0.add(Lunch0);
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
                schedule.add(day0);

                if (!customMethods.saveSchedule(schedule, getActivity(), "GSF 1012")) {
                    Log.e("GetScheduleFragment 309", "Error in saving sharedpreference");
                    Toast.makeText(getActivity(), "Unable to save changes. Please try again and report this bug. Sorry :(", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(getActivity(), getResources().getString(R.string.PSA_re_editing), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        template8_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomMethods customMethods = new CustomMethods();
                ArrayList<ArrayList<Period>> schedule = new ArrayList<>(0); //TODO: get an actual schedule and confirm all the weird ones

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

                Period AShort = new Period(480, 527, "A", 0, 0);
                Period ALong = new Period(480, 554, "A", 0, 0);

                Period BShort = new Period(534, 581, "B", 1, 0);
                Period BLong1 = new Period(480, 554, "B", 1, 0);
                Period BLong2 = new Period(561, 635, "B", 1, 0);

                Period CShort = new Period(588, 635, "C", 2, 0);
                Period CLong = new Period(561, 635, "C", 2, 0);

                Period DShort1 = new Period(696, 743, "D", 3, 0);
                Period DShort2 = new Period(723, 770, "D", 3, 0);
                Period DLong1 = new Period(696, 770, "D", 3, 0);
                Period DLong2 = new Period(723, 797, "D", 3, 0);

                Period EShort1 = new Period(750, 797, "E", 4, 0);
                Period EShort2 = new Period(777, 824, "E", 4, 0);
                Period ELong1 = new Period(750, 824, "E", 4, 0);
                Period ELong2 = new Period(777, 851, "E", 4, 0);

                Period FShort1 = new Period(804, 851, "F", 5, 0);
                Period FShort2 = new Period(831, 878, "F", 5, 0);
                Period FLong = new Period(804, 878, "F", 5, 0);

                Period GShort = new Period(885, 932, "G", 6, 0);

                Period Lunch = new Period(642, 682, "Lunch", 7, 1);

                Period Advisor = new Period(689, 716, "Advisor", 8, 3);
                Period Assembly = new Period(689, 716, "Assembly", 8, 3);
                Period Club = new Period(689, 716, "Club", 8, 3);
                Period CommonTime = new Period(689, 743, "Common Time", 8, 3);
                Period DinkyFree = new Period(682, 696, "Free", 8, 2);
                Period OtherFree = new Period(858, 878, "Free", 8, 2);

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
                day0.add(AShort); day0.add(BShort); day0.add(CShort); day0.add(Lunch); day0.add(DinkyFree); day0.add(DShort1); 
                            day0.add(EShort1); day0.add(FShort1); day0.add(OtherFree); day0.add(GShort);

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

                if (!customMethods.saveSchedule(schedule, getActivity(), "GSF 89")) {
                    Log.e("GetScheduleFragment 947", "Error in saving sharedpreference");
                    Toast.makeText(getActivity(), "Unable to save changes. Please try again and report this bug. Sorry :(", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(getActivity(), getResources().getString(R.string.PSA_re_editing), Toast.LENGTH_LONG).show();

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

                day1.add(new Period(480,932,"Free",0,2));
                day2.add(new Period(480,932,"Free",1,2));
                day3.add(new Period(480,932,"Free",2,2));
                day4.add(new Period(480,932,"Free",3,2));
                day5.add(new Period(480,932,"Free",4,2));
                day6.add(new Period(480,932,"Free",5,2));
                day7.add(new Period(480,932,"Free",6,2));
                day8.add(new Period(480,932,"Free",7,2));
                day9.add(new Period(480,932,"Free",8,2));
                day0.add(new Period(480,932,"Free",8,2));

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
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        sample.setOnClickListener(new View.OnClickListener() {
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
                Period GFree = new Period(885, 932, "Free", 6, 0);

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
        });
        return view;
    }

}
