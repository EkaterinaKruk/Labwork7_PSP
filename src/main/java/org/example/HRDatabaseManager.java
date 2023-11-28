package org.example;

import javax.swing.*;
import java.sql.*;
public class HRDatabaseManager {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/emloyee";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    public void addEmployee(Employee employee) {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            createTableIfNotExists(connection);
            System.out.println("ID: " + employee.getId());
            System.out.println("isEmployeeExists: " + isEmployeeExists(connection, employee.getId()));
            if (employee != null && employee.getId() != 0 && !isEmployeeExists(connection, employee.getId())) {
                insertEmployee(connection, employee);
                JOptionPane.showMessageDialog(null, "Сотрудник успешно добавлен!");
            } else {
                JOptionPane.showMessageDialog(null, "Сотрудник с таким ID уже существует!");
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ошибка при добавлении сотрудника!");
        }
    }

    public void viewData() {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            createTableIfNotExists(connection);

            String query = "SELECT * FROM employees";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            StringBuilder result = new StringBuilder("Список сотрудников:\n");
            while (resultSet.next()) {
                result.append(resultSet.getString("id"))
                        .append(". ")
                        .append(resultSet.getString("last_name"))
                        .append(", ")
                        .append(resultSet.getString("first_name"))
                        .append(", ")
                        .append(resultSet.getString("middle_name"))
                        .append(", ")
                        .append(resultSet.getString("gender"))
                        .append(", ")
                        .append(resultSet.getDate("dob"))
                        .append(", ")
                        .append(resultSet.getString("address"))
                        .append(", ")
                        .append(resultSet.getString("position"))
                        .append("\n");
            }

            JOptionPane.showMessageDialog(null, result.toString());

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ошибка при просмотре данных!");
        }
    }

    private void createTableIfNotExists(Connection connection) throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS employees ("
                + "id INT PRIMARY KEY,"
                + "last_name VARCHAR(255) NOT NULL,"
                + "first_name VARCHAR(255) NOT NULL,"
                + "middle_name VARCHAR(255),"
                + "gender VARCHAR(10),"
                + "dob DATE,"
                + "address VARCHAR(255),"
                + "position VARCHAR(255))";
        Statement statement = connection.createStatement();
        statement.execute(createTableQuery);
    }

    private boolean isEmployeeExists(Connection connection, int id) throws SQLException {
        String checkIfExistsQuery = "SELECT id FROM employees WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(checkIfExistsQuery)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                ;
                return resultSet.next();
            }
        }
    }

    private void insertEmployee(Connection connection, Employee employee) throws SQLException {
        String insertQuery = "INSERT INTO employees (id, last_name, first_name, middle_name, gender, dob, address, position) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getFirstName());
            preparedStatement.setString(4, employee.getMiddleName());
            preparedStatement.setString(5, employee.getGender());
            preparedStatement.setDate(6, new java.sql.Date(employee.getDob().getTime()));
            preparedStatement.setString(7, employee.getAddress());
            preparedStatement.setString(8, employee.getPosition());
            preparedStatement.executeUpdate();
        }
    }
}
