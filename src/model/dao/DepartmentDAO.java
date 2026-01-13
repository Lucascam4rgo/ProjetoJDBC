package model.dao;

import model.entities.Department;

import java.util.List;

public interface DepartmentDAO {

    void insert(Department obj);
    void update(Department obj);
    void deleteByID(Integer id);
    Department findByID(Integer id);
    List<Department> findAll();

}
