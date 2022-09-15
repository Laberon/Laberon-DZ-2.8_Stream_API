package LaberonLSDZ2.Service;

import LaberonLSDZ2.Domain.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeServiceStream {

    Employee maxSalary(int department);

    Employee minSalary(int department);

    List<Employee> findEmployeeByDepartment(int department);

    Map<Integer, List<Employee>> findEmployee();
}
