package jdbc_practice.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(
                    "jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:init.sql'",
                    "sa",
                    "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
