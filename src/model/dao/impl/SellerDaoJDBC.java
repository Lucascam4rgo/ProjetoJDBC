package model.dao.impl;

import model.dao.SellerDAO;
import model.entities.Seller;

import java.util.List;

public class SellerDaoJDBC implements SellerDAO {

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteByID(Integer id) {

    }

    @Override
    public Seller findByID(Integer id) {
        return null;
    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }
}
