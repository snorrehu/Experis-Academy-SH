package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Connection conn = null;
        System.out.println("Simple SQL Tester");

        String url = "jdbc:sqlite:Northwind_small.sqlite";
        String user = "sa";
        String password = "";
        conn = DriverManager.getConnection(url, user, password);


        Employee[] employees = GetEmployeeData(conn);

        System.out.println();
        for(Employee e : employees)
        {
            System.out.println(e.toString());
        }


    }

    private static Employee[] GetEmployeeData(Connection conn) throws Exception {
        try {
            ResultSet rs = null;
            Employee[] employees = null;

            Statement stmt = conn.createStatement();

            // Count the number of Employees
            rs = stmt.executeQuery("SELECT count(*) FROM [Employee]");
            int count = rs.getInt(1);

            // Select them from the DB
            rs = stmt.executeQuery("SELECT * FROM [Employee]");

            System.out.println("Count: " + count);
            employees = new Employee[count];
            int index =0;
            while(rs.next())
            {
                employees[index] = new Employee(Integer.parseInt(rs.getString("Id")),rs.getString("FirstName"), rs.getString("LastName"));
                index++;
            }

            return employees;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


}

