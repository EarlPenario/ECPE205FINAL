package org.example;

import javax.swing.*;
import java.awt.*;

public class PayslipGUI extends JFrame {
    JLabel name , dateJoined , line , PresentDays , dateIssued , Position , DailySalary , Employee , Employer , LineE , LineER;
    GridBagLayout layout;
    JTextArea Pay , Deductions;

    public PayslipGUI(Employee employee , String date){
        Container container = this.getContentPane();
        name = new JLabel("Name: " + employee.getFName() + " " + employee.getLName() + " ");
        dateJoined = new JLabel("Date Joined: " + employee.getDateJoined());
        line = new JLabel(" | ");
        PresentDays = new JLabel("Present Days: " + employee.getPresent());
        dateIssued = new JLabel("Date Issued: " + date);
        Position = new JLabel("Position: " + employee.getPosition());
        DailySalary = new JLabel("Daily Salary: " + employee.getSalary());
        Employee = new JLabel("Employee's Signature");
        Employer = new JLabel("Employer's Signature");
        LineE = new JLabel("___________________");
        LineER = new JLabel("___________________");


        Pay = new JTextArea(10 , 10);
        Pay.setEditable(false);
        Deductions = new JTextArea(10 , 10);
        Deductions.setEditable(false);

        String one = "Gross Pay: " + employee.getGrossPay() + "\n" +
                "Net Pay: " + employee.getNetPay();
        String two = "SSS: " + employee.getSSS() + "\n" +
                "Phil Health: " + employee.getPhilHealth() + "\n" +
                "Pag-IBIG: " + employee.getPagIBIG() + "\n" +
                "Total Contribution: " + employee.getTotalContribution() + "\n" +
                "Income Tax: " + employee.getIncomeTax() + "\n" +
                "Total Deduction: " + employee.getTotalDeduction();

        Pay.append(one);
        Deductions.append(two);

        layout = new GridBagLayout();
        container.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.fill=GridBagConstraints.BOTH;
        container.add(name,constraints);

        constraints.gridx=1;
        constraints.gridy=0;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.fill=GridBagConstraints.BOTH;
        container.add(line,constraints);

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

        constraints.gridx=1;
        constraints.gridy=0;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.fill=GridBagConstraints.BOTH;
        container.add(line,constraints);

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

        constraints.gridx=1;
        constraints.gridy=0;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.fill=GridBagConstraints.BOTH;
        container.add(line,constraints);

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

        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
