import javax.swing.*;
import java.sql.*;
import java.util.List;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;database=GameDB;" +
            "integratedSecurity=true";
    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private static PreparedStatement pst = null;

    public static List<String> fillListString(List<String> aList, boolean isPlayerNotGame)
    throws Exception{
        connectToDatabase();
        String query;
        if(isPlayerNotGame) {
            query = "SELECT first_name, last_name FROM Player";
            rs = stmt.executeQuery(query);
            while(rs.next()){
                aList.add(rs.getString("first_name") + " "
                        + rs.getString("last_name"));
            }
        }
        else {
            query = "SELECT game_title FROM Game";
            rs = stmt.executeQuery(query);
            while(rs.next()){
                aList.add(rs.getString("game_title"));
            }
        }

        if(aList.isEmpty()){
            String message;
            if(isPlayerNotGame)
                message = "No available rows from Player Table to collect.";
            else
                message = "No available rows from Game Table to collect.";
            JOptionPane.showMessageDialog(
                    null,message);
        }

        return aList;
    }

    private static void connectToDatabase() throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection(URL);
        stmt = con.createStatement();
    }

}
