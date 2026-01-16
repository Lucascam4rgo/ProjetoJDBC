package app;

import model.dao.DaoFactory;
import model.dao.DepartmentDAO;
import model.dao.impl.DepartmentDaoJDBC;
import model.entities.Department;

import java.util.List;

public class Program2 {

    public static void main(String[] args) {

        DepartmentDAO dept = DaoFactory.createDepartmentDAO();

//        System.out.println("Teste de insert: ");
//        Department deptInserted = new Department(null, "Games");
//
//        dept.insert(deptInserted);

//        System.out.println("\nTeste de update");
//        Department deptUpdated = new Department(6, "Games");
//
//        dept.update(deptUpdated);

//        System.out.println("\nTeste de delete");
//        dept.deleteByID(8);

//        System.out.println("\nTeste de achar por id");
//        Department deptFound = dept.findByID(3);
//        System.out.println(deptFound);

            System.out.println("\nTeste de ver todos os departamentos");
            List<Department> depts = dept.findAll();

            for (Department dep : depts) {
                System.out.println(dep);
            }

    }

}
