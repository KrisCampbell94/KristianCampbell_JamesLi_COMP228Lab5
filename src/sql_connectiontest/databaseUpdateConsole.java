package sql_connectiontest;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class databaseUpdateConsole {
    public static void main(String[] args) {
        String connectionURL = "jdbc:sqlserver://localhost:1433;database=GameDB;" +
                "integratedSecurity=true";

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        List<Integer> getID = new LinkedList<>();

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionURL);
            String SQL = "SELECT player_id FROM Player";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while(rs.next()){
                getID.add(rs.getInt("player_id"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
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
        for (var id:getID) {
            System.out.println(id);
        }
    }
}
