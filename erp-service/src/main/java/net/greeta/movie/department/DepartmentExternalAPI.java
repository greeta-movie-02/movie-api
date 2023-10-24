package net.greeta.movie.department;

public interface DepartmentExternalAPI {

    DepartmentDTO getDepartmentByIdWithEmployees(Long id);
    DepartmentDTO add(DepartmentDTO department);
}
