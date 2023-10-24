package net.greeta.movie.department;

import net.greeta.movie.employee.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;

public record DepartmentDTO(Long id,
                            Long organizationId,
                            String name,
                            List<EmployeeDTO> employees) {
    public DepartmentDTO(Long id, Long organizationId, String name) {
        this(id, organizationId, name, new ArrayList<>());
    }
}
