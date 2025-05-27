package org.example;

import javax.swing.*;
import java.awt.*;

public class PayslipGUI extends JFrame {
    JLabel name , dateJoined , PresentDays , dateIssued , Position , DailySalary , Employee , Employer , LineE , LineER;
    GridBagLayout layout;
    JTextArea Pay , Deductions;

    public PayslipGUI(Employee employee , String date){
        Container container = this.getContentPane();
        name = new JLabel("Name: " + employee.getFName() + " " + employee.getLName() + " ");
        dateJoined = new JLabel("Date Joined: " + employee.getDateJoined());
        PresentDays = new JLabel("Present Days: " + employee.getPresent());
        dateIssued = new JLabel("Date Issued: " + date);
        Position = new JLabel("Position: " + employee.getPosition());
        DailySalary = new JLabel("Daily Salary: " + employee.getSalary());
        Employee = new JLabel("Employee's Signature");
        Employer = new JLabel("Employer's Signature");
        LineE = new JLabel("___________________");
        LineER = new JLabel("___________________");


        Pay = new JTextArea(5 , 20);
        Pay.setEditable(false);
        Deductions = new JTextArea(5 , 20);
        Deductions.setEditable(false);

        String one = String.format("Gross Pay: %.2f\nNet Pay: %.2f",
                employee.getGrossPay(),
                employee.getNetPay());
        String two = String.format("SSS: %.2f\nPhil Health: %.2f\nPag-IBIG: %.2f\n" +
                        "Total Contribution: %.2f\nIncome Tax: %.2f\n" +
                        "Total Deduction: %.2f",
                employee.getSSS(),
                employee.getPhilHealth(),
                employee.getPagIBIG(),
                employee.getTotalContribution(),
                employee.getIncomeTax(),
                employee.getTotalDeduction());

        Pay.append(one);
        Deductions.append(two);

        layout = new GridBagLayout();
        container.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets=new Insets(5,5,5,5);

        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.fill=GridBagConstraints.BOTH;
        container.add(name,constraints);

        constraints.gridx=2;
        constraints.gridy=0;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.fill=GridBagConstraints.BOTH;
        container.add(Position,constraints);

        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.fill=GridBagConstraints.BOTH;
        container.add(dateJoined,constraints);

        constraints.gridx=2;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.fill=GridBagConstraints.BOTH;
        container.add(PresentDays,constraints);

        constraints.gridx=0;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.fill=GridBagConstraints.BOTH;
        container.add(dateIssued,constraints);

        constraints.gridx=2;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.fill=GridBagConstraints.BOTH;
        container.add(DailySalary,constraints);

        constraints.gridx=0;
        constraints.gridy=3;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.fill=GridBagConstraints.BOTH;
        container.add(Pay,constraints);

        constraints.gridx=2;
        constraints.gridy=3;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.fill=GridBagConstraints.BOTH;
        container.add(Deductions,constraints);

        constraints.gridx=0;
        constraints.gridy=4;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.fill=GridBagConstraints.BOTH;
        container.add(Employee,constraints);

        constraints.gridx=2;
        constraints.gridy=4;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.fill=GridBagConstraints.BOTH;
        container.add(Employer,constraints);

        constraints.gridx=0;
        constraints.gridy=5;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.fill=GridBagConstraints.BOTH;
        container.add(LineE,constraints);

        constraints.gridx=2;
        constraints.gridy=5;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.fill=GridBagConstraints.BOTH;
        container.add(LineER,constraints);

        this.setTitle("Monthly Payslip");
        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }
}
