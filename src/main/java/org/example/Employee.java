package org.example;

public class Employee {
    String fName,Position,Salary,lName,present, absent;
    Double SSS,PagIBIG,GrossPay,NetPay,IncomeTax,TotalContribution,TotalDeduction,PhilHealth;


    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }
    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAbsent() {
        return absent;
    }

    public void setAbsent(String absent) {
        this.absent = absent;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    public Double getTotalDeduction() {
        return TotalDeduction;
    }

    public void setTotalDeduction(Double totalDeduction) {
        TotalDeduction = totalDeduction;
    }

    public Double getTotalContribution() {
        return TotalContribution;
    }

    public void setTotalContribution(Double totalContribution) {
        TotalContribution = totalContribution;
    }

    public Double getIncomeTax() {
        return IncomeTax;
    }

    public void setIncomeTax(Double incomeTax) {
        IncomeTax = incomeTax;
    }

    public Double getNetPay() {
        return NetPay;
    }

    public void setNetPay(Double netPay) {
        NetPay = netPay;
    }

    public Double getGrossPay() {
        return GrossPay;
    }

    public void setGrossPay(Double grossPay) {
        GrossPay = grossPay;
    }

    public Double getPagIBIG() {
        return PagIBIG;
    }

    public void setPagIBIG(Double pagIBIG) {
        PagIBIG = pagIBIG;
    }

    public Double getSSS() {
        return SSS;
    }

    public void setSSS(Double SSS) {
        this.SSS = SSS;
    }

    public Double getPhilHealth() {
        return PhilHealth;
    }

    public void setPhilHealth(Double philHealth) {
        PhilHealth = philHealth;
    }

    public Employee(String lName, String fName, String position, String salary, String present, String absent) {
        this.fName = fName;
        this.Position = position;
        this.Salary = salary;
        this.lName = lName;
        this.present = present;
        this.absent = absent;
    }
}
