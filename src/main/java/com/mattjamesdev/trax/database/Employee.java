package com.mattjamesdev.trax.database;

import java.util.ArrayList;

public class Employee {
    ArrayList<double[]> workerInput;
    EmployeeMetric employeeMetric;
    String name;

    public Employee(String name){
        workerInput = new ArrayList<>();
        this.name = name;
    }

    public void addInput(double[] input){
        workerInput.add(input);
    }

    public ArrayList<Double> getProduction(){
        ArrayList<Double> productionList = new ArrayList<>();

        for(int i = 0; i < workerInput.size(); i++){
            double[] week = workerInput.get(i);
            double totalHours = 0;

            for(int j = 0; j < week.length; j++){
                totalHours += week[j];
            }

            productionList.add((totalHours*employeeMetric.getFactor1()
                    *employeeMetric.getFactor2()
                    *employeeMetric.getPayRate()));
        }

        return productionList;
    }

    public void setEmployeeMetric(EmployeeMetric employeeMetric) {
        this.employeeMetric = employeeMetric;
    }

    public String getName() {
        return name;
    }
}
