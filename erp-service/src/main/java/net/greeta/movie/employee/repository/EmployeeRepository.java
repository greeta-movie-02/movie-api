package net.greeta.movie.employee.repository;

import net.greeta.movie.employee.EmployeeDTO;
import org.springframework.data.repository.CrudRepository;
import net.greeta.movie.employee.model.Employee;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<EmployeeDTO> findByDepartmentId(Long departmentId);
    List<EmployeeDTO> findByOrganizationId(Long organizationId);
    void deleteByOrganizationId(Long organizationId);

}
