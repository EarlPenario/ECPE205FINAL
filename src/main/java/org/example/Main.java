package org.example;

import org.checkerframework.checker.units.qual.A;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

                if (fireStoreConnection.employeeExists(lName, fName)) {
                    JOptionPane.showMessageDialog(null, "This employee already exists!");
                    return;
                }

                if (lName.isEmpty() || fName.isEmpty() || position.isEmpty() ||
                        salary.isEmpty() || present.isEmpty() || absent.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                    return;
                }

                Employee employee=new Employee(lName,fName,position,salary,present,absent);
                fireStoreConnection.addEmployee(lName,fName,position,salary,present,absent);
                frame.table.addEmployee(employee);

                frame.lField.setText("");
                frame.fField.setText("");
                frame.positionField.setText("");
                frame.salaryField.setText("");
                frame.presentField.setText("");
                frame.absentField.setText("");

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

                    if (lName.isEmpty() || fName.isEmpty() || position.isEmpty() ||
                            salary.isEmpty() || present.isEmpty() || absent.isEmpty()) {
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
                    frame.table.employees.set(selectedRow,employee);
                    frame.table.fireTableDataChanged();
                    fireStoreConnection.updateEmployee(employee,selectedRow);

                    frame.lField.setText("");
                    frame.fField.setText("");
                    frame.positionField.setText("");
                    frame.salaryField.setText("");
                    frame.presentField.setText("");
                    frame.absentField.setText("");

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

            }
        });

        frame.yearEndReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}