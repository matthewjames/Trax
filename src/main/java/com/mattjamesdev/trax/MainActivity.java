package com.mattjamesdev.trax;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mattjamesdev.trax.ui.calendar.CalendarFragment;
import com.mattjamesdev.trax.ui.time_clock.TimeClockFragment;
import com.mattjamesdev.trax.ui.analytics.AnalyticsFragment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;
    private TimeClockFragment timeClockFragment;
    private CalendarFragment calendarFragment;
    private AnalyticsFragment analyticsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        toolbar = getSupportActionBar();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_time_clock, R.id.navigation_calender, R.id.navigation_analytics)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
//        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        toolbar.setTitle(R.string.title_time_clock);
//        loadFragment(new TimeClockFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = (item) -> {
        switch(item.getItemId()){
            case R.id.navigation_time_clock:
                toolbar.setTitle(R.string.title_time_clock);
                if(timeClockFragment == null){
                    timeClockFragment = new TimeClockFragment();
                }

                loadFragment(timeClockFragment);
                return true;
            case R.id.navigation_calender:
                toolbar.setTitle(R.string.title_calendar);
                if(calendarFragment == null){
                    calendarFragment= new CalendarFragment();
                }
                loadFragment(calendarFragment);
                return true;
            case R.id.navigation_analytics:
                toolbar.setTitle(R.string.title_analytics);

                if(analyticsFragment == null){
                    analyticsFragment = new AnalyticsFragment();
                }

                loadFragment(analyticsFragment);
                return true;
        }
        return false;
    };

    private void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
