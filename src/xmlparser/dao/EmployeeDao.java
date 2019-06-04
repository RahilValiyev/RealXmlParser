package xmlparser.dao;

import xmlparser.model.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> getEmployeList();

    void addEmploye(List<Employee> employee);
}
