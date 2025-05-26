package org.example;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.checkerframework.checker.units.qual.C;

import java.io.FileInputStream;
import java.util.*;
import java.util.jar.Attributes;

public class FireStoreConnection {
    Firestore db;

    public FireStoreConnection() {
        db = null;
        try {
            FileInputStream serviceAccount = new FileInputStream("src/main/java/org/example/ecpe205final-firebase-adminsdk-fbsvc-1235b48426.json");
            FirebaseOptions options = new FirebaseOptions.Builder().
                    setCredentials(GoogleCredentials.fromStream(serviceAccount)).
                    setDatabaseUrl("https://ecpe205final-default-rtdb.asia-southeast1.firebasedatabase.app/")
                    .build();
            FirebaseApp.initializeApp(options);
            db = FirestoreClient.getFirestore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEmployee(String lname, String fName, String position, String salary,
                            String present, String absent, Double grossPay,
                            Double SSS, Double PagIBIG, Double PhilHealth, Double Contribution,
                            Double IncomeTax,Double Deductions, Double NetPay , String dateJoined) {
        Map<String, Object> employee = new HashMap<>();
        employee.put("Last Name", lname);
        employee.put("First Name", fName);
        employee.put("Position", position);
        employee.put("Daily Salary", salary);
        employee.put("Present Days", present);
        employee.put("Absent Days", absent);
        employee.put("Gross Pay", grossPay);
        employee.put("SSS Contribution",SSS);
        employee.put("Pag-Ibig", PagIBIG);
        employee.put("PhilHealth",PhilHealth);
        employee.put("Total Contribution", Contribution);
        employee.put("Income Tax",IncomeTax);
        employee.put("Total Deductions",Deductions);
        employee.put("Net Pay",NetPay);
        employee.put("Date Joined" , dateJoined);

        ApiFuture<DocumentReference> result = db.collection("employees").add(employee);

        try {
            System.out.println("Added Documentation with Id: " + result.get().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateEmployee(Employee employee,int index){
        try {
            ApiFuture<QuerySnapshot>query=db.collection(("employees")).get();
            List<QueryDocumentSnapshot>documents=query.get().getDocuments();

            if(index>=0&&index<documents.size()){
                String documentId=documents.get(index).getId();
                DocumentReference docRef=db.collection("employees").document(documentId);
                Map<String,Object>updates=new HashMap<>();
                updates.put("Last Name",employee.getLName());
                updates.put("First Name",employee.getFName());
                updates.put("Position",employee.getPosition());
                updates.put("Daily Salary",employee.getSalary());
                updates.put("Present Days",employee.getPresent());
                updates.put("Absent Days",employee.getAbsent());
                updates.put("Gross Pay",employee.getGrossPay());
                updates.put("SSS Contribution",employee.getSSS());
                updates.put("Pag-Ibig",employee.getPagIBIG());
                updates.put("PhilHealth",employee.getPhilHealth());
                updates.put("Total Contribution",employee.getTotalContribution());
                updates.put("Total Deductions",employee.getTotalDeduction());
                updates.put("Net Pay",employee.getNetPay());
                updates.put("Income Tax",employee.getIncomeTax());
                docRef.update(updates);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int index){
        try {
            ApiFuture<QuerySnapshot>query=db.collection("employees").get();
            List<QueryDocumentSnapshot>documents=query.get().getDocuments();
            if(index>=0&&index<documents.size()){
                String documentId=documents.get(index).getId();
                db.collection("employees").document(documentId).delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean employeeExists(String lName, String fName) {
        try {
            ApiFuture<QuerySnapshot> query = db.collection("employees")
                    .whereEqualTo("Last Name", lName)
                    .whereEqualTo("First Name", fName)
                    .get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();
            return !documents.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Employee> getAllEmployees() {
        try {
            ApiFuture<QuerySnapshot>query=db.collection("employees").get();
            List<QueryDocumentSnapshot> documents=query.get().getDocuments();
            ArrayList<Employee>employees=new ArrayList<>();
            for(QueryDocumentSnapshot document:documents){
                String lName=document.getString("Last Name");
                String fName=document.getString("First Name");
                String position=document.getString("Position");
                String salary=document.getString("Daily Salary");
                String present=document.getString("Present Days");
                String absent=document.getString("Absent Days");
                String dateJoined = document.getString("Date Joined");
                double SSS = document.getDouble("SSS Contribution");
                double PagIBIG = document.getDouble("Pag-Ibig");
                double GrossPay = document.getDouble("Gross Pay");
                double NetPay = document.getDouble("Net Pay");
                double IncomeTax = document.getDouble("Income Tax");
                double TotalContribution = document.getDouble("Total Contribution");
                double TotalDeduction = document.getDouble("Total Deductions");
                double PhilHealth = document.getDouble("PhilHealth");
                employees.add(new Employee(GrossPay , fName , position , salary , lName , present , absent , dateJoined , SSS , PagIBIG , NetPay , IncomeTax , TotalContribution , TotalDeduction , PhilHealth));
            }
            return employees;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


