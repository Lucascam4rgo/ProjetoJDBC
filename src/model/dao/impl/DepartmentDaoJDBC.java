package model.dao.impl;

import db.DB;
import db.dbException;
import model.dao.DepartmentDAO;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDAO {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("insert into department (name) values(?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, obj.getName());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new dbException("Algo de errado aconteceu e nenhuma linha foi afetada.");
            }
            else {
                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                    System.out.println("Department inserted");
                }
            }
        }
        catch (SQLException sql) {
            throw new dbException(sql.getMessage());
        }
        finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void update(Department obj) {

        PreparedStatement ps = null;
        try {

            ps = conn.prepareStatement("update department set name = ? " +
                    "where id = ?");

            ps.setString(1, obj.getName());
            ps.setInt(2, obj.getId());

            ps.executeUpdate();

            System.out.println("Department updated!");

        }
        catch (SQLException sql) {
            throw new dbException(sql.getMessage());
        }
        finally {
            DB.closeStatement(ps);
        }

    }

    @Override
    public void deleteByID(Integer id) {
        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement("DELETE FROM department where id = ?");

            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new dbException("Nenhuma linha apagada: ID não existe");
            }
            else {
                System.out.println("Linha com id " + id + " apagada!");
            }

        }
        catch (SQLException sql) {
            throw new dbException(sql.getMessage());
        }
        finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public Department findByID(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conn.prepareStatement("select * from department " +
                    "where id = ?");

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                return instantiateDepartment(rs);
            }
            else {
                System.out.println("No ID found");
                return null;
            }
        }
        catch (SQLException sql) {
            throw new dbException(sql.getMessage());
        }
        finally {
            DB.closeStatement(ps);
            DB.closeRS(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Department> list = new ArrayList<>();

        try {

            ps = conn.prepareStatement("select * from department");

            rs = ps.executeQuery();

            while (rs.next()) {
                Department dept = instantiateDepartment(rs);
                list.add(dept);
            }

            return list;

        }
        catch (SQLException sql) {
            throw new dbException(sql.getMessage());
        }
        finally {
            DB.closeStatement(ps);
            DB.closeRS(rs);
        }
    }

    private static Department instantiateDepartment(ResultSet rs) {
        Department dept = new Department();

        try {
            dept.setId(rs.getInt("id"));
            dept.setName(rs.getString("name"));
        }
        catch (SQLException sql) {
            throw new dbException(sql.getMessage());
        }

        return dept;
    }
}
