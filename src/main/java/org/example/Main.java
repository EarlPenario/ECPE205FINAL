package org.example;

import org.checkerframework.checker.units.qual.A;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Frame frame=new Frame();
        FireStoreConnection fireStoreConnection=new FireStoreConnection();
        ArrayList<Employee>tableData=fireStoreConnection.getAllEmployees();

        for(int i=0;i<tableData.size();i++){
            frame.table.addEmployee(tableData.get(i));
        }

        frame.add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lName=frame.lField.getText();
                String fName=frame.fField.getText();
                String position=frame.positionField.getText();
                String salary=frame.salaryField.getText();
                String present=frame.presentField.getText();
                String absent=frame.absentField.getText();
                String dateJoined=frame.dateField.getText();



                if (fireStoreConnection.employeeExists(lName, fName)) {
                    JOptionPane.showMessageDialog(null, "This employee already exists!");
                    return;
                }

                if (lName.isEmpty() || fName.isEmpty() || position.isEmpty() ||
                        salary.isEmpty() || present.isEmpty() || absent.isEmpty()|| dateJoined.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                    return;
                }
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                    LocalDate.parse(dateJoined, formatter);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date format! Please use dd-MMM-yyyy " +
                            "(Example: 01-Jan-2023)");
                    return;
                }

                Employee employee=new Employee(lName,fName,position,salary,present,absent,dateJoined);
                frame.table.addEmployee(employee);

                frame.lField.setText("");
                frame.fField.setText("");
                frame.positionField.setText("");
                frame.salaryField.setText("");
                frame.presentField.setText("");
                frame.absentField.setText("");
                frame.dateField.setText("");

                double Gross_Pay = Integer.parseInt(present) * Integer.parseInt(salary);
                double Net_Pay = Gross_Pay;
                double Total_Deductions = 0;
                double Total_Contribution = 0;

                /// SSS Contribution
                double SSS_contribution = 0;
                if(Gross_Pay > 34750){

                    SSS_contribution = 1750;

                }else if (Gross_Pay < 5250){

                    SSS_contribution = 250;

                }else {

                    /// MPF Calculator
                    int MPFCounter = 20250;
                    int cutter = 2;
                    int MPF = 0;

                    if(Gross_Pay >= 20250){

                        do{

                            if(cutter == 2){
                                MPF += 25;
                                cutter = 0;
                            }

                            MPFCounter += 250;
                            cutter++;

                        }while(MPFCounter <= Gross_Pay);

                    }
                    /// Payment
                    int PaymentCounter = 5250;
                    int cutters = 2;
                    int Payment = 250;

                    if(Gross_Pay < 20250){

                        if(Gross_Pay >= 5250){

                            do{

                                if(cutters == 2){
                                    Payment += 25;
                                    cutters = 0;
                                }

                                PaymentCounter += 250;
                                cutters++;

                            }while(PaymentCounter <= Gross_Pay);

                        }

                    }else{
                        Payment = 1000;
                    }

                    SSS_contribution = MPF + Payment;
                }
                Net_Pay -= SSS_contribution;

                /// PhilHealth Contribution
                double PhilHealth_contribution = Gross_Pay * 0.05;

                if(PhilHealth_contribution < 500){
                    PhilHealth_contribution = 500;
                    PhilHealth_contribution = PhilHealth_contribution / 2;
                } else if(PhilHealth_contribution > 4500) {
                    PhilHealth_contribution = 4500;
                    PhilHealth_contribution = PhilHealth_contribution / 2;
                }else {
                    PhilHealth_contribution = PhilHealth_contribution / 2;
                }

                Net_Pay = Net_Pay - PhilHealth_contribution;

                /// Pag-IBIG Contribution
                double PagIBIG = 200;
                Net_Pay -= 200;

                /// Income Tax
                double incomeTax = 0;
                if(Gross_Pay < 20833){
                    incomeTax = 0;
                } else if (Gross_Pay >= 20833 && Gross_Pay <= 33332) {
                    incomeTax = (Gross_Pay - 20833) * 0.15;
                } else if (Gross_Pay >= 33333 && Gross_Pay <= 66666){
                    incomeTax = 1875 + (Gross_Pay - 33333) * 0.2;
                } else if (Gross_Pay >= 66667 && Gross_Pay <= 166666) {
                    incomeTax = 8541.8 + (Gross_Pay - 66667) * 0.25;
                } else if (Gross_Pay >= 166667 && Gross_Pay <= 666666) {
                    incomeTax = 33541.8 + (Gross_Pay - 166667) * 0.3;
                }else {
                    incomeTax = 183541.8 + (Gross_Pay - 666667) * 0.35;
                }

                Net_Pay = Net_Pay - incomeTax;

                Total_Contribution=SSS_contribution + PhilHealth_contribution + PagIBIG;

                Total_Deductions = SSS_contribution + PhilHealth_contribution + PagIBIG + incomeTax;

                fireStoreConnection.addEmployee(lName,fName,position,salary,present,absent,Gross_Pay,
                        SSS_contribution,PagIBIG, PhilHealth_contribution,
                        Total_Contribution,incomeTax,Total_Deductions,Net_Pay , dateJoined);

            }
        });

        frame.update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow=frame.employeeTable.getSelectedRow();
                if(selectedRow>=0){
                    Employee employee=frame.table.employees.get(selectedRow);
                    String lName=frame.lField.getText();
                    String fName=frame.fField.getText();
                    String position=frame.positionField.getText();
                    String salary=frame.salaryField.getText();
                    String present=frame.presentField.getText();
                    String absent=frame.absentField.getText();
                    String date=frame.dateField.getText();

                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                        LocalDate.parse(date, formatter);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Invalid date format! Please use dd-MMM-yyyy " +
                                "(Example: 01-Jan-2023)");
                        return;
                    }

                    if (lName.isEmpty() || fName.isEmpty() || position.isEmpty() ||
                            salary.isEmpty() || present.isEmpty() || absent.isEmpty()|| date.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                        return;
                    }

                    if (!employee.getLName().equals(lName) || !employee.getFName().equals(fName)) {
                        if (fireStoreConnection.employeeExists(lName, fName)) {
                            JOptionPane.showMessageDialog(null, "Another employee with this name already exists!");
                            return;
                        }
                    }

                    employee.setlName(lName);
                    employee.setFName(fName);
                    employee.setPosition(position);
                    employee.setSalary(salary);
                    employee.setPresent(present);
                    employee.setAbsent(absent);
                    employee.setDateJoined(date);
                    frame.table.employees.set(selectedRow,employee);
                    frame.table.fireTableDataChanged();
                    fireStoreConnection.updateEmployee(employee,selectedRow);

                    frame.lField.setText("");
                    frame.fField.setText("");
                    frame.positionField.setText("");
                    frame.salaryField.setText("");
                    frame.presentField.setText("");
                    frame.absentField.setText("");
                    frame.dateField.setText("");

                    double Gross_Pay = Integer.parseInt(present) * Integer.parseInt(salary);
                    double Net_Pay = Gross_Pay;
                    double Total_Deductions = 0;
                    double Total_Contribution = 0;

                    /// SSS Contribution
                    double SSS_contribution = 0;
                    if(Gross_Pay > 34750){

                        SSS_contribution = 1750;

                    }else if (Gross_Pay < 5250){

                        SSS_contribution = 250;

                    }else {

                        /// MPF Calculator
                        int MPFCounter = 20250;
                        int cutter = 2;
                        int MPF = 0;

                        if(Gross_Pay >= 20250){

                            do{

                                if(cutter == 2){
                                    MPF += 25;
                                    cutter = 0;
                                }

                                MPFCounter += 250;
                                cutter++;

                            }while(MPFCounter <= Gross_Pay);

                        }
                        /// Payment
                        int PaymentCounter = 5250;
                        int cutters = 2;
                        int Payment = 250;

                        if(Gross_Pay < 20250){

                            if(Gross_Pay >= 5250){

                                do{

                                    if(cutters == 2){
                                        Payment += 25;
                                        cutters = 0;
                                    }

                                    PaymentCounter += 250;
                                    cutters++;

                                }while(PaymentCounter <= Gross_Pay);

                            }

                        }else{
                            Payment = 1000;
                        }

                        SSS_contribution = MPF + Payment;
                    }
                    Net_Pay -= SSS_contribution;

                    /// PhilHealth Contribution
                    double PhilHealth_contribution = Gross_Pay * 0.05;

                    if(PhilHealth_contribution < 500){
                        PhilHealth_contribution = 500;
                        PhilHealth_contribution = PhilHealth_contribution / 2;
                    } else if(PhilHealth_contribution > 4500) {
                        PhilHealth_contribution = 4500;
                        PhilHealth_contribution = PhilHealth_contribution / 2;
                    }else {
                        PhilHealth_contribution = PhilHealth_contribution / 2;
                    }

                    Net_Pay = Net_Pay - PhilHealth_contribution;

                    /// Pag-IBIG Contribution
                    double PagIBIG = 200;
                    Net_Pay -= 200;

                    /// Income Tax
                    double incomeTax = 0;
                    if(Gross_Pay < 20833){
                        incomeTax = 0;
                    } else if (Gross_Pay >= 20833 && Gross_Pay <= 33332) {
                        incomeTax = (Gross_Pay - 20833) * 0.15;
                    } else if (Gross_Pay >= 33333 && Gross_Pay <= 66666){
                        incomeTax = 1875 + (Gross_Pay - 33333) * 0.2;
                    } else if (Gross_Pay >= 66667 && Gross_Pay <= 166666) {
                        incomeTax = 8541.8 + (Gross_Pay - 66667) * 0.25;
                    } else if (Gross_Pay >= 166667 && Gross_Pay <= 666666) {
                        incomeTax = 33541.8 + (Gross_Pay - 166667) * 0.3;
                    }else {
                        incomeTax = 183541.8 + (Gross_Pay - 666667) * 0.35;
                    }

                    Net_Pay = Net_Pay - incomeTax;

                    Total_Contribution=SSS_contribution + PhilHealth_contribution + PagIBIG;

                    Total_Deductions = SSS_contribution + PhilHealth_contribution + PagIBIG + incomeTax;
                    employee.setSSS(SSS_contribution);
                    employee.setNetPay(Net_Pay);
                    employee.setPagIBIG(PagIBIG);
                    employee.setGrossPay(Gross_Pay);
                    employee.setIncomeTax(incomeTax);
                    employee.setPhilHealth(PhilHealth_contribution);
                    employee.setTotalContribution(Total_Contribution);
                    employee.setTotalDeduction(Total_Deductions);

                    fireStoreConnection.updateEmployee(employee,selectedRow);
                }else {
                    JOptionPane.showMessageDialog(null,"Please select an employee you want to update!");
                }
            }
        });

        frame.delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int []index=frame.employeeTable.getSelectedRows();
                if(index.length>0){
                    for(int i=index.length-1;i>=0;i--){
                        int row=index[i];
                        Employee employee=frame.table.employees.get(row);
                        fireStoreConnection.deleteEmployee(row);
                        frame.table.removeEmployee(row);
                    }
                }
            }
        });

        frame.payslip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int index = frame.employeeTable.getSelectedRow();
                if(index>=0) {
                    Employee employee = frame.table.employees.get(index);

                    if (employee.getGrossPay() == null) {
                        employee = fireStoreConnection.getEmployee(employee.getFName(), employee.getLName());
                    }

                    LocalDate currentDate = LocalDate.now();
                    DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                    String dateFormatted = currentDate.format(dateformatter);
                    PayslipGUI frame = new PayslipGUI(employee, dateFormatted);
                }else {
                    JOptionPane.showMessageDialog(null,"Please select an employee you want to generate a payslip for!");
                }
            }
        });

        frame.yearEndReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Employee> employee = fireStoreConnection.getAllEmployees();
                ArrayList<Employee> YrEndEmployee = new ArrayList<>();
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                String dateFormatted = currentDate.format(dateformatter);

                for(int i = 0; i < employee.size(); i++){
                    double SSS , Phil_Health , Pag_IBIG , TotalContribution , TotalIncomeTax , GrossPay , NetPay , TotalDeduction = 0;
                    int totalPresent;
                    String present,stringTotal;
                    Employee employeee = employee.get(i);
                    SSS = employeee.getSSS() * 12;
                    Phil_Health = employeee.getPhilHealth() * 12;
                    Pag_IBIG = employeee.getPagIBIG() * 12;
                    TotalContribution = employeee.getTotalContribution() * 12;
                    TotalIncomeTax = employeee.getIncomeTax() * 12;
                    GrossPay = employeee.getGrossPay() * 12;
                    NetPay = employeee.getNetPay() * 12;
                    TotalDeduction = employeee.getTotalDeduction() * 12;
                    present=employeee.getPresent();
                    totalPresent=Integer.parseInt(present)*12;
                    stringTotal=String.valueOf(totalPresent);
                    YrEndEmployee.add(new Employee(GrossPay , employeee.getFName(), employeee.getPosition(), employeee.getSalary(),
                            employeee.getLName(), stringTotal, employeee.getAbsent(), employeee.getDateJoined(), SSS,
                            Pag_IBIG, NetPay, TotalIncomeTax, TotalContribution,
                            TotalDeduction, Phil_Health));
                }
                new YearEndGUI(YrEndEmployee, dateFormatted);

            }
        });
    }
}