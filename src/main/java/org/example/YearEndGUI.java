package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class YearEndGUI extends JFrame {
    JLabel name, dateJoined, present, dateIssued, position, dailySalary,
            Employee, Employer, employeeSig, employerSig;
    Container container;
    GridBagLayout layout;
    JTextArea yearPay, yearDeductions;
    GridBagConstraints constraints;

    public YearEndGUI(ArrayList<Employee> employees, String date) {
        container = this.getContentPane();
        layout = new GridBagLayout();
        container.setLayout(layout);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        for (Employee employee : employees) {

            JPanel employeePanel = new JPanel(layout);
            constraints = new GridBagConstraints();
            constraints.insets = new Insets(5, 5, 5, 5);

            name = new JLabel("Name: " + employee.getFName() + " " + employee.getLName());
            dateJoined = new JLabel("Date Joined: " + employee.getDateJoined());
            present = new JLabel("Present Days: " + employee.getPresent());
            dateIssued = new JLabel("Date Issued: " + date);
            position = new JLabel("Position: " + employee.getPosition());
            dailySalary = new JLabel("Daily Salary: " + employee.getSalary());
            Employee = new JLabel("Employee's Signature");
            Employer = new JLabel("Employer's Signature");
            employeeSig = new JLabel("___________________");
            employerSig = new JLabel("___________________");


            yearPay = new JTextArea(5, 20);
            yearPay.setEditable(false);
            yearDeductions = new JTextArea(5, 20);
            yearDeductions.setEditable(false);


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

            yearPay.setText(one);
            yearDeductions.setText(two);


            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridwidth=1;
            constraints.gridheight=1;
            employeePanel.add(name, constraints);

            constraints.gridx = 2;
            constraints.gridy = 0;
            constraints.gridwidth=1;
            constraints.gridheight=1;
            employeePanel.add(position, constraints);

            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.gridwidth=1;
            constraints.gridheight=1;
            employeePanel.add(dateJoined, constraints);

            constraints.gridx = 2;
            constraints.gridy = 1;
            constraints.gridwidth=1;
            constraints.gridheight=1;
            employeePanel.add(present, constraints);

            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.gridwidth=1;
            constraints.gridheight=1;
            employeePanel.add(dateIssued, constraints);

            constraints.gridx = 2;
            constraints.gridy = 2;
            constraints.gridwidth=1;
            constraints.gridheight=1;
            employeePanel.add(dailySalary, constraints);

            constraints.gridx = 0;
            constraints.gridy = 3;
            constraints.gridwidth=1;
            constraints.gridheight=1;
            employeePanel.add(new JScrollPane(yearPay), constraints);

            constraints.gridx = 2;
            constraints.gridy = 3;
            constraints.gridwidth=1;
            constraints.gridheight=1;
            employeePanel.add(yearDeductions, constraints);

            constraints.gridx = 0;
            constraints.gridy = 4;
            constraints.gridwidth=1;
            constraints.gridheight=1;
            employeePanel.add(Employee, constraints);

            constraints.gridx = 2;
            constraints.gridy = 4;
            constraints.gridwidth=1;
            constraints.gridheight=1;
            employeePanel.add(Employer, constraints);

            constraints.gridx = 0;
            constraints.gridy = 5;
            constraints.gridwidth=1;
            constraints.gridheight=1;
            employeePanel.add(employeeSig, constraints);

            constraints.gridx = 2;
            constraints.gridy = 5;
            constraints.gridwidth=1;
            constraints.gridheight=1;
            employeePanel.add(employerSig, constraints);

            mainPanel.add(employeePanel);
            mainPanel.add(new JSeparator());
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(800, 600));
        container.add(scrollPane);

        this.setTitle("Year End Report");
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}