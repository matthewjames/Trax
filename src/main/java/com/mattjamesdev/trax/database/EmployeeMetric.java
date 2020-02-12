package com.mattjamesdev.trax.database;

public class EmployeeMetric {
    private double payRate, factor1, factor2;

    public EmployeeMetric(double payRate, double factor1, double factor2){
        this.payRate = payRate;
        this.factor1 = factor1;
        this.factor2 = factor2;
    }

    public double returnIdeal(double payRate, double[] week, double multiplier){
        double value=0;
        for(int i=0;i<week.length;i++){
            value=value+week[i]*payRate*multiplier;
        }
        return value;
    }

    public double returnProduction(double production, double[] week){
        double value=0;
        for(int i=0;i<week.length;i++){
            value=value+week[i]*production;
        }
        return value;
    }

    public double returnDay(double[] week, int day, int payRate, int multiplier){
        //day is the index of the specific day according to week
        double value = week[day]*payRate*multiplier;
        return value;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    public double getFactor1() {
        return factor1;
    }

    public void setFactor1(double factor1) {
        this.factor1 = factor1;
    }

    public double getFactor2() {
        return factor2;
    }

    public void setFactor2(double factor2) {
        this.factor2 = factor2;
    }
}
