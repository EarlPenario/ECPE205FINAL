package org.example;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class Table extends AbstractTableModel {
    ArrayList<Employee>employees;
    String [] columns={"Last Name","First Name","Position","Daily Salary","Days Present", "Days Absent"};
    public Table(){
        employees=new ArrayList<>();
    }
    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }
    public void addEmployee(Employee employee){
        employees.add(employee);
        fireTableDataChanged();
    }
    public void removeEmployee(int index){
        employees.remove(index);
        fireTableDataChanged();
    }
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee employee=employees.get(rowIndex);
        if(columnIndex==0){
            return employee.lName;
        } else if (columnIndex==1) {
            return employee.fName;
        } else if (columnIndex==2) {
            return employee.Position;
        } else if (columnIndex==3) {
            return employee.Salary;
        } else if (columnIndex==4) {
            return employee.present;
        } else {
            return employee.absent;
        }
    }
}
