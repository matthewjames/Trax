package com.mattjamesdev.trax.ui.analytics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mattjamesdev.trax.R;
import com.mattjamesdev.trax.database.Employee;
import com.mattjamesdev.trax.database.EmployeeMetric;

import java.util.ArrayList;
import java.util.List;

public class AnalyticsFragment extends Fragment {

    private AnalyticsViewModel analyticsViewModel;
    private LineChart lineChart;
    private MaterialSpinner spinner;
    private ArrayList<Employee> data;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        analyticsViewModel =
                ViewModelProviders.of(this).get(AnalyticsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_analytics, container, false);

        data = createDummyData();
        lineChart = root.findViewById(R.id.chart);
        spinner = root.findViewById(R.id.spinner);
        prepareGraphData(0);
        setSpinner();

        return root;
    }

    private void prepareGraphData(int empIndex){
        ArrayList<Double> production = data.get(empIndex).getProduction();
        List<Entry> entries = new ArrayList<>();

        for(int i = 0; i < production.size(); i++){
            entries.add(new Entry(i, production.get(i).intValue()));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Employee Production");
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//        lineDataSet.setCubicIntensity(0.1f);
        dataSet.setLineWidth(2.0f);
        dataSet.setColor(getResources().getColor(R.color.colorPrimary, getActivity().getTheme()));
        dataSet.setHighLightColor(getResources().getColor(R.color.colorAccent, getActivity().getTheme()));
        dataSet.setDrawCircles(false);
        dataSet.setDrawHorizontalHighlightIndicator(false);
        dataSet.setDrawVerticalHighlightIndicator(false);

        LineData lineData = new LineData(dataSet);

        lineChart.setData(lineData);
        buildGraph(lineData);
    }

    private void buildGraph(LineData lineData){

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setAxisLineWidth(2.0f);
        xAxis.setLabelCount(12);
//        xAxis.setValueFormatter(xFormatter);
        xAxis.setDrawGridLines(false);

        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setAxisLineWidth(2.0f);
//        yAxis.setValueFormatter(yFormatter);
//        yAxis.setAxisMaximum(10f);

        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getDescription().setEnabled(false);
        lineChart.getLegend().setEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.animateXY(500, 500);
        lineChart.invalidate();
    }

    private ArrayList<Employee> createDummyData(){
        ArrayList<Employee> data = new ArrayList<>();
        EmployeeMetric employeeMetric = new EmployeeMetric(30, 1.5, 2);

        //Employee 1:
        Employee emp1 = new Employee("John Waller");
        emp1.setEmployeeMetric(employeeMetric);
        emp1.addInput(new double[]{6,7,7,8,6});
        emp1.addInput(new double[]{6,7,8,8,8});
        emp1.addInput(new double[]{5,7,6,8,9});
        emp1.addInput(new double[]{8,7,6,7,7});
        emp1.addInput(new double[]{6,7,6,7,8});
        emp1.addInput(new double[]{7,8,8,8,8});
        data.add(emp1);

        //Employee 2:
        Employee emp2 = new Employee("David Torres");
        emp2.setEmployeeMetric(employeeMetric);
        emp2.addInput(new double[]{6,8,6,8,8});
        emp2.addInput(new double[]{7,8,7,8,6});
        emp2.addInput(new double[]{7,8,6,6,6});
        emp2.addInput(new double[]{7,8,7,6,7});
        emp2.addInput(new double[]{8,6,8,7,7});
        emp2.addInput(new double[]{8,6,7,8,6});
        data.add(emp2);

        //Employee 3:
        Employee emp3 = new Employee("Sarah Lee");
        emp3.setEmployeeMetric(employeeMetric);
        emp3.addInput(new double[]{6,7,6,8,8});
        emp3.addInput(new double[]{7,7,8,6,6});
        emp3.addInput(new double[]{7,8,7,6,7});
        emp3.addInput(new double[]{8,6,7,8,6});
        emp3.addInput(new double[]{7,8,7,8,6});
        emp3.addInput(new double[]{7,8,8,8,6});
        data.add(emp3);

        //Employee 4:
        Employee emp4 = new Employee("Janet Greene");
        emp4.setEmployeeMetric(employeeMetric);
        emp4.addInput(new double[]{7,7,8,6,6});
        emp4.addInput(new double[]{8,7,6,7,7});
        emp4.addInput(new double[]{8,6,7,8,6});
        emp4.addInput(new double[]{6,7,6,7,8});
        emp4.addInput(new double[]{7,8,8,8,6});
        emp4.addInput(new double[]{6,7,8,8,8});
        data.add(emp4);

        //Employee 5:
        Employee emp5 = new Employee("Michael Dodds");
        emp5.setEmployeeMetric(employeeMetric);
        emp5.addInput(new double[]{8,8,6,8,7});
        emp5.addInput(new double[]{6,7,7,8,6});
        emp5.addInput(new double[]{5,7,6,8,9});
        emp5.addInput(new double[]{7,8,8,8,8});
        emp5.addInput(new double[]{6,7,8,7,7});
        emp5.addInput(new double[]{8,8,8,7,6});
        data.add(emp5);

        return data;
    }

    private void setSpinner(){
        ArrayList<String> names = new ArrayList<>();

        for(Employee emp : data){
            names.add(emp.getName());
        }

        spinner.setItems(names);

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                switch(item){
                    case "John Waller":
                        prepareGraphData(0);
                        break;

                    case "David Torres":
                        prepareGraphData(1);
                        break;

                    case "Sarah Lee":
                        prepareGraphData(2);
                        break;

                    case "Janet Greene":
                        prepareGraphData(3);
                        break;

                    case "Michael Dodds":
                        prepareGraphData(4);
                        break;
                }
            }
        });
    }
}