package org.example;

public class Employee {
    String fName,Position,Salary,lName,present, absent;


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

    public Employee(String lName, String fName, String position, String salary, String present, String absent) {
        this.fName = fName;
        this.Position = position;
        this.Salary = salary;
        this.lName = lName;
        this.present = present;
        this.absent = absent;
    }
}
