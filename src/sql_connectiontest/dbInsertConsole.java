package sql_connectiontest;

import java.sql.*;
import java.util.Scanner;

public class dbInsertConsole {
    public static void main (String[] args) {

        // CONNECTION STRING
        final String connectionURL = "jdbc:sqlserver://localhost:1433;database=GameDB;" +
                "integratedSecurity=true";

        // DATABASE VARIABLES
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        // USER INPUT VARIABLES
        int playerID;
        String firstName;
        String lastName;
        StringBuilder address;
        String postalCode;
        String province;
        String phoneNumber;

        // USER TO COMPUTER INPUT VARIABLES
        Scanner input = new Scanner(System.in);
        Scanner arrayInput = new Scanner(System.in);

        // USER INPUTS INFORMATION
        System.out.print("Enter First Name : ");
        firstName = input.next();
        System.out.print("Enter Last Name : ");
        lastName = input.next();

        System.out.print("Enter Address : ");
        // This will not happen with Java Swing, but it assists with Console App.
        String[] addressCombo = arrayInput.nextLine().split(" ");
        address = new StringBuilder();
        for (String anAddressCombo : addressCombo)
            address.append(anAddressCombo).append(" ");

        System.out.print("Enter Postal Code : ");
        postalCode = input.next().toUpperCase();

        System.out.print("Enter Province (2 Letters): ");
        province = input.next().substring(0,2).toUpperCase();

        System.out.print("Enter Phone Number : ");
        phoneNumber = input.next();

        // BEGIN CONNECTION TESTING
        try {
            // ESTABLISH CONNECTION
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionURL);

            // CREATE A QUERY TO GET THE ROW COUNT FROM PLAYER (TO SETUP PLAYER_ID)
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT (*) AS row_count FROM Player");

            // PREPARES AN INSERT INTO STATEMENT
            pst = con.prepareStatement(
                    "INSERT into Player (player_id, " +
                            "first_name, last_name, address, " +
                            "postal_code, province, phone_number)" +
                            "VALUES(?,?,?,?,?,?,?)");

            // GETS THE NEXT AVAILABLE ROW AND SETS PLAYER ID TO COLUMN LABELED AS ROW_COUNT
            rs.next();
            playerID = rs.getInt("row_count");

            // SETS THE ? FROM THE PREPARED STATEMENT TO ACTUAL VALUES
            pst.setInt(1,playerID);
            pst.setString(2,firstName);
            pst.setString(3,lastName);
            pst.setString(4, address.toString());
            pst.setString(5,postalCode);
            pst.setString(6,province);
            pst.setString(7,phoneNumber);

            // EXECUTES THE QUERY STATEMENT
            pst.executeUpdate();
        } catch (Exception e) {
            // CATCH ANY EXCEPTION
            e.printStackTrace();
        } finally {
            // CLOSE EVERYTHING SQL RELATED
            if(pst != null){
                try{
                    pst.close();
                } catch (Exception e){
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
    }
}
