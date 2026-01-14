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

        System.out.println("\n=== TEST 3: seller findAll ===");
        List<Seller> listAll = sellerDAO.findAll();

        for (Seller sel : listAll) {
            System.out.println(sel);
        }

//        System.out.println("\n=== TEST 4: seller insert");
//        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(),4000.0, dep);
//
//        sellerDAO.insert(newSeller);
//
//        System.out.println("Inserted! New ID: " + newSeller.getId());
//
//        System.out.println("\n=== TEST 5: seller update");

        seller = sellerDAO.findByID(1);
        seller.setName("Martha Wayne");
        sellerDAO.update(seller);
        System.out.println("Update complete");

    }
}
