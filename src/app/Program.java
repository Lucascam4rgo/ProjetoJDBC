package app;

import db.DB;
import db.dbException;
import db.dbIntegrityException;
import model.entities.Department;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {

    public static void main(String[] args) {

        Department obj = new Department(1, "Books");

        System.out.println(obj);

    }
}
