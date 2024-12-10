package com.paylocity.utilities;

public class NetPayCalculator {

    public int dependents;

    public NetPayCalculator(int dependents){
        this.dependents = dependents;
    }

    public static final double defaultGross = 2000.0d;
    public static final short defaultPayPerPeriods = 26;

    public static final double defaultSalary = defaultGross * defaultPayPerPeriods;

    public static final double defaultEmployeeBenefitsCost = 1000.0f / defaultPayPerPeriods;
    public static final double defaultDependentsBenefitsCost = 500.0f / defaultPayPerPeriods;

    public static double grossPay;
    public static double benefitsCost;

    public double calculateNetPay(){
        grossPay = defaultSalary / defaultPayPerPeriods;
        benefitsCost = defaultEmployeeBenefitsCost + (defaultDependentsBenefitsCost * dependents);
        return grossPay - benefitsCost;
    }




}
