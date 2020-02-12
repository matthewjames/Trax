package com.mattjamesdev.trax.ui.time_clock;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.mattjamesdev.trax.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeClockFragment extends Fragment {
    private static final String TAG = "TimeClockFragment";

    private TimeClockViewModel timeClockViewModel;
    private Button buttonTimeClock;
    private TextView textViewTime;
    private TextClock textClock;
    private boolean clockedIn;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        timeClockViewModel =
                ViewModelProviders.of(this).get(TimeClockViewModel.class);
        View root = inflater.inflate(R.layout.fragment_time_clock, container, false);
        textClock = root.findViewById(R.id.textclock);
        textViewTime = root.findViewById(R.id.textview_time);
        buttonTimeClock = root.findViewById(R.id.button_clock_in);
        buttonTimeClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clock in button pressed ");
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM, d, yyyy");

                if(!isClockedIn()) {
                    textViewTime.setText("You clocked in at " + textClock.getText() + " on " + formatter.format(now));
                    textViewTime.setVisibility(View.VISIBLE);
                    textClock.setTextColor(getResources().getColor(R.color.colorClockIn, getActivity().getTheme()));
                    buttonTimeClock.setBackgroundColor(getResources().getColor(R.color.colorClockOut, getActivity().getTheme()));
                    buttonTimeClock.setText("Clock Out");
                } else {
                    textViewTime.setText("You clocked out at " + textClock.getText() + " on " + formatter.format(now));
                    textViewTime.setTextColor(getResources().getColor(R.color.colorClockOut, getActivity().getTheme()));
                    buttonTimeClock.setBackgroundColor(getResources().getColor(R.color.colorClockIn, getActivity().getTheme()));
                    buttonTimeClock.setText("Clock In");
                    textClock.setTextColor(getResources().getColor(R.color.colorDefaultText, getActivity().getTheme()));
                }
            }
        });
        timeClockViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textViewTime.setText(s);
            }
        });
        return root;
    }

    private boolean isClockedIn(){
        clockedIn = textViewTime.getVisibility() == View.VISIBLE;

        if(clockedIn){
            return true;
        }
        return false;
    }
}