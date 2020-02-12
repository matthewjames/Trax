package com.mattjamesdev.trax.ui.calendar;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mattjamesdev.trax.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.threeten.bp.format.DateTimeFormatter;

import java.text.SimpleDateFormat;

public class CalendarFragment extends Fragment {

    private CalendarViewModel calendarViewModel;
    private FloatingActionButton fab;
    private ConstraintLayout bottomSheet;
    private BottomSheetBehavior sheetBehavior;
    private MaterialCalendarView calendarView;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE MMMM d, yyyy");
    private ImageView closeButton;
    private EditText startTime, endTime;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel =
                ViewModelProviders.of(this).get(CalendarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);

        bottomSheet = root.findViewById(R.id.event_bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(bottomSheet);
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                switch (sheetBehavior.getState()){
                    case BottomSheetBehavior.STATE_EXPANDED:
                    case BottomSheetBehavior.STATE_DRAGGING:
                        fab.setImageResource(R.drawable.ic_check_black_24dp);
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        fab.setImageResource(R.drawable.ic_add_black_24dp);
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

        startTime = root.findViewById(R.id.edit_text_event_start_time);
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        endTime = root.findViewById(R.id.edit_text_event_end_time);

        fab = root.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    TextView bottomSheetDate = (TextView)bottomSheet.getViewById(R.id.textview_bottom_sheet_date);
                    bottomSheetDate.setText(dateFormat.format(calendarView.getSelectedDate().getDate()));

                    startTime.setText("9:00 AM");
                    endTime.setText("10:00 AM");
                }
            }
        });

        calendarView = root.findViewById(R.id.calendarView);
        calendarView.setSelectedDate(CalendarDay.today());

        closeButton = root.findViewById(R.id.image_close_bottom_sheet);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        return root;
    }
}