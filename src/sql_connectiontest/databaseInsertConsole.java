package sql_connectiontest;

import java.sql.*;
import java.util.Scanner;

public class databaseInsertConsole {
    public static void main (String[] args) {
        final String connectionURL = "jdbc:sqlserver://localhost:1433;database=GameDB;" +
                "integratedSecurity=true";

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        int playerID;
        String firstName;
        String lastName;
        StringBuilder address;
        String postalCode;
        String province;
        String phoneNumber;

        Scanner input = new Scanner(System.in);
        Scanner arrayInput = new Scanner(System.in);

        System.out.print("Enter First Name : ");
        firstName = input.next();
        System.out.print("Enter Last Name : ");
        lastName = input.next();

        System.out.print("Enter Address : ");
        String[] addressCombo = arrayInput.nextLine().split(" ");
        address = new StringBuilder();
        for (String anAddressCombo : addressCombo)
            address.append(anAddressCombo).append(" ");

        System.out.print("Enter Postal Code : ");
        postalCode = input.next();
        postalCode = postalCode.toUpperCase();

        System.out.print("Enter Province (2 Letters): ");
        province = input.next();
        if(province.length() > 2){
            province = province.substring(0,2);
            province = province.toUpperCase();
        }
        System.out.print("Enter Phone Number : ");
        phoneNumber = input.next();

        try {
            // Establishing a connection to your database
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionURL);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT (*) AS row_count FROM Player");
            pst = con.prepareStatement(
                    "INSERT into Player (player_id, " +
                            "first_name, last_name, address, " +
                            "postal_code, province, phone_number)" +
                            "VALUES(?,?,?,?,?,?,?)");

            rs.next();
            playerID = rs.getInt("row_count");

            pst.setInt(1,playerID);
            pst.setString(2,firstName);
            pst.setString(3,lastName);
            pst.setString(4, address.toString());
            pst.setString(5,postalCode);
            pst.setString(6,province);
            pst.setString(7,phoneNumber);

            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
