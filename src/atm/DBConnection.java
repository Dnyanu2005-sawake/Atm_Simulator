package atm;

import java.sql.*;

public class DBConnection {

    public Connection con;

    public DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/atm_db?useSSL=false&serverTimezone=UTC",
                    "root",
                    "Dnyanu@2005"   // 👈 apna MySQL password
            );

            System.out.println("✅ Database Connected Successfully");

        } catch (Exception e) {
            System.out.println("❌ Database Connection Failed");
            e.printStackTrace();
        }
    }

    // 🔴 TEMPORARY MAIN (sirf test ke liye)
    public static void main(String[] args) {
        new DBConnection();
    }
}
