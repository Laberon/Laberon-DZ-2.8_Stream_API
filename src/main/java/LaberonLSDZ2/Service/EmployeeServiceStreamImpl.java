package LaberonLSDZ2.Service;

import LaberonLSDZ2.Domain.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceStreamImpl implements EmployeeServiceStream {

    private EmployeeServiceImpl employeeService;

    public EmployeeServiceStreamImpl(EmployeeServiceImpl employeeService) {
        this.employeeService=employeeService;
    }
    @Override
    public Employee maxSalary(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow();//EmployeeNotFoundException::new
    }
    @Override
    public Employee minSalary(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow();//EmployeeNotFoundException::new
    }
    @Override
    public List<Employee> findEmployeeByDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment()==department)
                .collect(Collectors.toList());
    }
    @Override
    public Map<Integer, List<Employee>> findEmployee() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
