package net.greeta.movie.department.mapper;

import net.greeta.movie.department.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import net.greeta.movie.department.DepartmentDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DepartmentMapper {
    DepartmentDTO departmentToEmployeeDTO(Department department);
    Department departmentDTOToEmployee(DepartmentDTO departmentDTO);
}
