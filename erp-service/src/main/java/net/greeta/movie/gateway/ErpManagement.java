package net.greeta.movie.gateway;

import net.greeta.movie.employee.EmployeeDTO;
import org.springframework.web.bind.annotation.*;
import net.greeta.movie.department.DepartmentDTO;
import net.greeta.movie.department.DepartmentExternalAPI;
import net.greeta.movie.employee.EmployeeExternalAPI;
import net.greeta.movie.organization.OrganizationDTO;
import net.greeta.movie.organization.OrganizationExternalAPI;

@RestController
@RequestMapping("/api")
public class ErpManagement {

    private DepartmentExternalAPI departmentExternalAPI;
    private EmployeeExternalAPI employeeExternalAPI;
    private OrganizationExternalAPI organizationExternalAPI;

    public ErpManagement(DepartmentExternalAPI departmentExternalAPI,
                         EmployeeExternalAPI employeeExternalAPI,
                         OrganizationExternalAPI organizationExternalAPI) {
        this.departmentExternalAPI = departmentExternalAPI;
        this.employeeExternalAPI = employeeExternalAPI;
        this.organizationExternalAPI = organizationExternalAPI;
    }


    @GetMapping("/organizations/{id}/with-departments")
    public OrganizationDTO apiOrganizationWithDepartments(@PathVariable("id") Long id) {
        return organizationExternalAPI.findByIdWithDepartments(id);
    }

    @GetMapping("/organizations/{id}/with-departments-and-employees")
    public OrganizationDTO apiOrganizationWithDepartmentsAndEmployees(@PathVariable("id") Long id) {
        return organizationExternalAPI.findByIdWithDepartmentsAndEmployees(id);
    }

    @PostMapping("/organizations")
    public OrganizationDTO apiAddOrganization(@RequestBody OrganizationDTO o) {
        return organizationExternalAPI.add(o);
    }

    @PostMapping("/employees")
    public EmployeeDTO apiAddEmployee(@RequestBody EmployeeDTO employee) {
        return employeeExternalAPI.add(employee);
    }

    @GetMapping("/departments/{id}/with-employees")
    public DepartmentDTO apiDepartmentWithEmployees(@PathVariable("id") Long id) {
        return departmentExternalAPI.getDepartmentByIdWithEmployees(id);
    }

    @PostMapping("/departments")
    public DepartmentDTO apiAddDepartment(@RequestBody DepartmentDTO department) {
        return departmentExternalAPI.add(department);
    }
}
