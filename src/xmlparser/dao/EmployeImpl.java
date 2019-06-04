package xmlparser.dao;

import xmlparser.database.DataBase;
import xmlparser.model.Employee;

import xmlparser.query.SqlQuery;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeImpl implements EmployeeDao {

    DataBase dataBase = new DataBase();


    @Override
    public List<Employee> getEmployeList() {
        List<Employee> employeeList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            connection = dataBase.connect();
            if (connection != null) {
                ps = connection.prepareStatement(SqlQuery.GET_EMPLOYE);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Employee employee = new Employee();

                    employee.setId(rs.getInt("id"));
                    employee.setFullName(rs.getString("name"));
                    employee.setAddress(rs.getString("address"));
                    employee.setPhoneNumber(rs.getString("phonenumber"));
                    employee.setActvityName(rs.getString("activityname"));

                    employeeList.add(employee);

                }

            } else {
                System.out.println("connection yaranmadi");

            }


        } catch (Exception e) {
            //Todo log
            e.printStackTrace();
        } finally {
            DataBase.close(rs, ps, connection);
        }


        return employeeList;
    }

    @Override
    public void addEmploye(List<Employee> employeList) {
        Connection connection = null;
        PreparedStatement ps = null;


        try {
            connection = dataBase.connect();
            ps = connection.prepareStatement(SqlQuery.ADD_EMPLOYEE);

            int count = 0;
            for (Employee emp : employeList) {
                // ps.setInt(1,emp.getId());
                ps.setString(1, emp.getFullName());
                ps.setString(2, emp.getPhoneNumber());
                ps.setString(3, emp.getAddress());
                ps.setString(4, emp.getActvityName());
                count = ps.executeUpdate();

            }
            System.out.println("ugurla elave olundu");
            System.out.println("elave edilmis data sayi " + count);
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBase.close(null, ps, connection);
        }


    }
}
