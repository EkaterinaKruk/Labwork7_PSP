package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class EmployeeForm {
    private JTextField idField, lastNameField, firstNameField, middleNameField, genderField, dobField, addressField, positionField;
    private JButton addButton, viewButton;
    private HRDatabaseApp app;

    public EmployeeForm(HRDatabaseApp app) {
        this.app = app;

        // Инициализация компонентов GUI
        idField = new JTextField(10);
        lastNameField = new JTextField(20);
        firstNameField = new JTextField(20);
        middleNameField = new JTextField(20);
        genderField = new JTextField(10);
        dobField = new JTextField(10);
        addressField = new JTextField(30);
        positionField = new JTextField(20);

        addButton = new JButton("Добавить");
        viewButton = new JButton("Просмотр");

        // Добавление слушателей
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee employee = getEmployeeData();
                if (employee != null) {
                    app.getDatabaseManager().addEmployee(employee);
                }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.getDatabaseManager().viewData();
            }
        });
    }

    public JPanel getPanel() {
        // Размещение компонентов на панели
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2));
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Фамилия:"));
        panel.add(lastNameField);
        panel.add(new JLabel("Имя:"));
        panel.add(firstNameField);
        panel.add(new JLabel("Отчество:"));
        panel.add(middleNameField);
        panel.add(new JLabel("Пол:"));
        panel.add(genderField);
        panel.add(new JLabel("Дата рождения (ГГГГ-ММ-ДД):"));
        panel.add(dobField);
        panel.add(new JLabel("Адрес прописки:"));
        panel.add(addressField);
        panel.add(new JLabel("Должность:"));
        panel.add(positionField);
        panel.add(addButton);
        panel.add(viewButton);

        return panel;
    }

    private Employee getEmployeeData() {
        try {
        int id = Integer.parseInt(idField.getText());
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String middleName = middleNameField.getText();
        String gender = genderField.getText();
        String dobText = dobField.getText();
        String address = addressField.getText();
        String position = positionField.getText();

        if (!isValidDate(dobText)) {
            JOptionPane.showMessageDialog(null, "Некорректный формат даты (ГГГГ-ММ-ДД)!");
            return null;
        }

       Date dob;
            dob = parseDate(dobText);
        //try {
            // dob = parseDate(dobText);
       // } catch (ParseException e) {
          //  e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Ошибка при вводе даты!");
           // return null;
       // }
        return new Employee(id,lastName, firstName, middleName, gender, dob, address, position);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ошибка при вводе ID!");
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isValidDate(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(dateString);
    }
}
