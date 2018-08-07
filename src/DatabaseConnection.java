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
    private static ResultSetMetaData md = null;

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

    public static void insertPlayerToDatabase(
            String fName, String lName, String address,
            String pCode, String province, String number,
            String date, String score, int getGameID
            ) throws Exception{
        connectToDatabase();

        rs = stmt.executeQuery("SELECT COUNT (*) AS row_count FROM Player");
        pst = con.prepareStatement(
                "INSERT into Player (player_id, " +
                        "first_name, last_name, address, " +
                        "postal_code, province, phone_number)" +
                        "VALUES(?,?,?,?,?,?,?)");
        rs.next();
        int getPlayerID = rs.getInt("row_count");
        pst.setInt(1,getPlayerID);
        pst.setString(2,fName);
        pst.setString(3,lName);
        pst.setString(4, address);
        pst.setString(5,pCode);
        pst.setString(6,province);
        pst.setString(7,number);
        pst.executeUpdate();

        rs = stmt.executeQuery("SELECT COUNT (*) AS row_count FROM PlayerAndGame");
        pst = con.prepareStatement(
                "INSERT into PlayerAndGame (player_game_id, " +
                        "game_id, player_id, playing_date, score) " +
                        "UPDATE VALUES (?,?,?,?,?)");
        rs.next();
        int getPlayerAndGameID = rs.getInt("row_count");
        pst.setInt(1,getPlayerAndGameID);
        pst.setInt(2,getGameID);
        pst.setInt(3,getPlayerID);
        pst.setString(4, String.valueOf(Date.valueOf(date)));
        pst.setString(5,score);
    }

    public static void updatePlayerToDatabase (
            int getPlayerID, String fName, String lName,
            String address, String pCode, String province,
            String number) throws Exception{
        pst = con.prepareStatement(
                "UPDATE Player " +
                        "SET first_name = ?, last_name = ?," +
                        "address = ?, postal_code = ?, province = ?," +
                        "phone_number = ? WHERE player_id = ?"
        );
        pst.setString(1,fName);
        pst.setString(2,lName);
        pst.setString(3, address);
        pst.setString(4,pCode);
        pst.setString(5,province);
        pst.setString(6,number);
        pst.setInt(7,getPlayerID);

        pst.executeUpdate();
    }

    private static void connectToDatabase() throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection(URL);
        stmt = con.createStatement();
    }

    public static void databaseDisconnect(){
        try {
            con.close();
            stmt.close();
            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
