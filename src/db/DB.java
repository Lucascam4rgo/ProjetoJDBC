package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConn() {
        try {
            if (conn == null) {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            }
        }
        catch (SQLException sql) {
            throw new dbException(sql.getMessage());
        }
        return conn;
    }

    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);

            return props;

        }
        catch (IOException i) {
            throw new dbException(i.getMessage());
        }
    }

    public static void closeConn() {
        try {
            if (conn != null) {
                conn.close();
            }
        }
        catch (SQLException sql) {
            throw new dbException(sql.getMessage());
        }
    }
}
