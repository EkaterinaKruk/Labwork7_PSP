package org.example;

import javax.swing.*;
public class HRDatabaseApp extends JFrame{
    private EmployeeForm employeeForm;
    private HRDatabaseManager databaseManager;

    public HRDatabaseApp() {
        setTitle("HR Database App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        employeeForm = new EmployeeForm(this);
        databaseManager = new HRDatabaseManager();

        // Добавление панели на форму
        add(employeeForm.getPanel());

        setVisible(true);
    }

    public HRDatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HRDatabaseApp();
            }
        });
    }
}
