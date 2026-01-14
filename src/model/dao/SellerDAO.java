package model.dao;

import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public interface SellerDAO {

    void insert(Seller obj);
    void update(Seller obj);
    void deleteByID(Integer id);
    Seller findByID(Integer id);
    List<Seller> findAll();
    List<Seller> findByDepartment(Department dep);

}
