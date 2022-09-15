package LaberonLSDZ2.Service;

import LaberonLSDZ2.Domain.Employee;
import LaberonLSDZ2.Exception.EmployeeAlreadyAddedException;
import LaberonLSDZ2.Exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    static final Map<String, Employee> employeeMap = new HashMap<>(Map.of());

    @Override
    public Employee addPerson(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);//,middleName
        if (employeeMap.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Message");
        }
        employeeMap.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public List<Employee> findPersons(String firstName, String lastName) {
        int salary=0;
        int department = 0;
        Employee employees = new Employee(firstName,lastName,salary,department);
        if (employeeMap.containsKey(employees.getFullName())) {
            return Collections.singletonList(employeeMap.get(employees.getFullName()));
        }
        throw new EmployeeNotFoundException("Not found");
    }

    @Override
    public Employee removePerson(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);//,middleName
        if (employeeMap.containsKey(employee.getFullName())) {
            return employeeMap.remove(employee.getFullName());
        }
        throw new EmployeeNotFoundException("Message");
    }

    @Override
    public Collection<Employee> getAll() {
        return employeeMap.values();
    }
}