package model.dao.impl;

import db.DB;
import db.dbException;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDAO {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

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

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ps = conn.prepareStatement("SELECT seller.*, department.name as depName " +
                    "from seller INNER JOIN department on seller.departmentID = department.id " +
                    "where seller.id = ?");

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Department dep = instantiateDepartment(rs);

                return instantiateSeller(rs, dep);
            }
            return null;
        } catch (SQLException sql) {
            throw new dbException(sql.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeRS(rs);
        }

    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller seller = new Seller();

        seller.setId(rs.getInt("id"));
        seller.setName(rs.getString("name"));
        seller.setEmail(rs.getString("email"));
        seller.setBaseSalary(rs.getDouble("baseSalary"));
        seller.setBirthDate(rs.getDate("birthdate"));
        seller.setDepartment(dep);

        return seller;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("departmentID"));
        dep.setName(rs.getString("depName"));
        return dep;
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ps = conn.prepareStatement("SELECT seller.*, department.name as depName " +
                    "from seller INNER JOIN department on seller.departmentID = department.id " +
                    "order by name");

            rs = ps.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {

                Department dept = map.get(rs.getInt("departmentID"));

                if (dept == null) {
                    dept = instantiateDepartment(rs);
                    map.put(rs.getInt("departmentID"), dept);
                }

                Seller sel = instantiateSeller(rs, dept);
                list.add(sel);

            }
            return list;

        } catch (SQLException sql) {
            throw new dbException(sql.getMessage());
        }
        finally {
            DB.closeStatement(ps);
            DB.closeRS(rs);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department dep) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ps = conn.prepareStatement("SELECT seller.*, department.name as depName " +
                    "from seller INNER JOIN department on seller.departmentID = department.id " +
                    "where department.id = ? " +
                    "order by name");

            ps.setInt(1, dep.getId());

            rs = ps.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {

                Department dept = map.get(rs.getInt("departmentID"));

                if (dept == null) {
                    dept = instantiateDepartment(rs);
                    map.put(rs.getInt("departmentID"), dept);
                }

                Seller sel = instantiateSeller(rs, dept);
                list.add(sel);

            }
            return list;

        } catch (SQLException sql) {
            throw new dbException(sql.getMessage());
        }
        finally {
            DB.closeStatement(ps);
            DB.closeRS(rs);
        }
    }
}
