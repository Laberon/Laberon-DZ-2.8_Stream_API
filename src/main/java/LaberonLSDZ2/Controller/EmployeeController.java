package LaberonLSDZ2.Controller;


import LaberonLSDZ2.Domain.Employee;
import LaberonLSDZ2.Service.EmployeeServiceImpl;
import LaberonLSDZ2.Service.EmployeeServiceStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Comparator;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;
    private final EmployeeServiceStream employeeServiceStream;

    public EmployeeController(EmployeeServiceImpl employeeService, EmployeeServiceStream employeeServiceStream) {
        this.employeeService = employeeService;
        this.employeeServiceStream = employeeServiceStream;
    }

    @GetMapping(path = "/add")
    public String add(@RequestParam("name") String firstName,
                      @RequestParam("lastName") String lastName,
                      @RequestParam("salary") Integer salary,
                      @RequestParam("department") Integer department) {
        employeeService.addPerson(firstName,lastName,salary,department);
        return "Добавлен";
    }

    @GetMapping(path = "/find")
    public String find(@RequestParam("name") String firstName,
                       @RequestParam("lastName") String lastName) {//@RequestParam("lastName") String lastName
        return employeeService.findPersons(firstName, lastName).toString();
    }

    @GetMapping(path = "/remove")
    public String remove(@RequestParam("name") String firstname,
                         @RequestParam("lastName") String lastName,
                         int salary,
                         int department) {
        employeeService.removePerson(firstname, lastName, salary, department);
        return "Сотрудник: " + firstname + " удален";
    }

    @GetMapping(path = "/findAll")
    public Collection<Employee> find() {
        return employeeService.getAll();
    }

    @GetMapping(path = "/departments/min-salary")
    public String minSalary(@RequestParam("departments") int departments) {
        return String.valueOf(employeeServiceStream.minSalary(departments));
    }
    @GetMapping(path = "/departments/max-salary")
    public String maxSalary(@RequestParam("departments") int departments) {
        return String.valueOf(employeeServiceStream.maxSalary(departments));
    }
    @GetMapping(path = "/departments/find-By-Department")
    public String findEmployeeByDepartment(@RequestParam("departments") int departments) {
        return String.valueOf(employeeServiceStream.findEmployeeByDepartment(departments));
    }
    @GetMapping(path = "/departments/findEmployee")
    public String findEmployee() {
        return String.valueOf(employeeServiceStream.findEmployee());
    }
}
