package sql_connectiontest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class connectURL {
    public static void main (String[] args) {
        String connectionURL = "jdbc:sqlserver://localhost:1433;database=GameDB;" +
                "integratedSecurity=true";

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Establishing a connection to your database
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionURL);

            // Create and execute an SQL statement that returns some data
            String SQL = "SELECT * FROM Player";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("player_id") + " " +
                        rs.getString("first_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
