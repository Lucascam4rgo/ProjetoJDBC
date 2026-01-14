package app;

import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Program {

    public static void main(String[] args) {

        SellerDAO sellerDAO = DaoFactory.createSellerDAO();

        System.out.println("=== TEST 1: seller findByID ===");
        Seller seller = sellerDAO.findByID(3);

        System.out.println(seller);

        System.out.println("\n=== TEST 2: seller findByDepartment ===");
        Department dep = new Department(2, null);
        List<Seller> list = sellerDAO.findByDepartment(dep);

        for (Seller sel : list) {
            System.out.println(sel);
        }

    }
}
