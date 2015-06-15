package doop.aa_schedule;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;




public class Feedback extends Fragment implements View.OnClickListener {

    EditText feedbackText;
    Button feedbackButton;
    RadioGroup feedbackType;
    RadioButton selectedButton;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view=inflater.inflate(R.layout.fragment_feedback,container,false);
        feedbackText = (EditText) view.findViewById(R.id.feedbackText);
        feedbackButton = (Button) view.findViewById(R.id.feedbackButton);
        feedbackButton.setOnClickListener(this);
        feedbackType = (RadioGroup) view.findViewById(R.id.feedbackType);
        return view;
    }

    @Override
    public void onClick(View v) {
        int selected = feedbackType.getCheckedRadioButtonId();
        selectedButton = (RadioButton) view.findViewById(selected);
        String message = feedbackText.getText().toString();

        if(selectedButton==null) {
            Toast.makeText(getActivity(), "Must Select Type", Toast.LENGTH_SHORT).show();
        }else if(message.equals("")) {
            Toast.makeText(getActivity(), "Must Enter Feedback", Toast.LENGTH_SHORT).show();
        }else{

            try {
                feedbackSender sender = new feedbackSender("aascheduleapp@gmail.com", "pizzapizza2");
                sender.sendMail(selectedButton.getText().toString(),
                        message);
                Toast.makeText(getActivity(), "Feedback Sent", Toast.LENGTH_SHORT).show();
                // Clear inputs
                feedbackText.setText("");
                feedbackType.clearCheck();
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
                Toast.makeText(getActivity(), "Feedback Failed to Send", Toast.LENGTH_SHORT).show();
            }
            // Hide the keyboard
            InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
