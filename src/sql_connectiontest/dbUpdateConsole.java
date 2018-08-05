package sql_connectiontest;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class dbUpdateConsole {
    public static void main(String[] args) {

        // CONNECTION STRING
        String connectionURL = "jdbc:sqlserver://localhost:1433;database=GameDB;" +
                "integratedSecurity=true";

        // DATABASE VARIABLES
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        // INTEGER LIST TO GET ALL THE AVAILABLE IDS
        List<Integer> getID = new LinkedList<>();

        // USER TO COMPUTER INPUT VARIABLES
        Scanner inputID = new Scanner(System.in);

        // BEGIN CONNECTION TESTING
        try{
            // ESTABLISH CONNECTION
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionURL);

            // CREATES A QUERY TO GET THE PLAYER IDS FROM THE PLAYER TABLE
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT player_id FROM Player");

            // A WHILE LOOP UNTIL THE NEXT ROW DOESN'T EXIST
            // GET THE VALUE FROM PLAYER_ID COLUMN
            while(rs.next()){
                getID.add(rs.getInt("player_id"));
            }

            // IF THE LIST ISN'T EMPTY
            if(!getID.isEmpty()) {

                // USER TYPES IN THE CORRECT ID
                // MIGHT CHANGE INTO A DROP-DOWN BOX FOR EASIER FUNCTIONALITY
                // BUT KEPT FOR CONSOLE PURPOSES
                boolean idFound = false;
                int fetchId = 0;
                while (!idFound) {
                    System.out.print("Enter ID to edit : ");
                    fetchId = inputID.nextInt();
                    for (int i = 0; i < getID.size(); ) {
                        if (getID.get(i) == fetchId) {
                            idFound = true;
                            break;
                        }
                        i++;
                        if (i == getID.size())
                            System.out.println("No results, try again.");
                    }
                }

                // SQL STATEMENT TO UPDATE THE SET PLAYER WHERE PLAYER ID = TO SPECIFIED VALUE
                pst = con.prepareStatement(
                        "UPDATE Player " +
                                "SET first_name = ?, last_name = ?," +
                                "address = ?, postal_code = ?, province = ?," +
                                "phone_number = ? WHERE player_id = ?"
                );
                // USER INPUTS EVERYTHING (LOOK @ dbInsertConsole FOR REFERENCE)
                Scanner input = new Scanner(System.in);
                Scanner arrayInput = new Scanner(System.in);

                System.out.print("Enter NEW First Name : ");
                pst.setString(1, input.next());

                System.out.print("Enter NEW Last Name : ");
                pst.setString(2, input.next());

                System.out.print("Enter NEW Address : ");
                String[] addressCombo = arrayInput.nextLine().split(" ");
                StringBuilder address = new StringBuilder();
                for (String anAddressCombo : addressCombo)
                    address.append(anAddressCombo).append(" ");
                pst.setString(3, address.toString());

                System.out.print("Enter NEW Postal Code Name : ");
                pst.setString(4, input.next().toUpperCase());

                System.out.print("Enter NEW Province (2 Letters): ");
                pst.setString(5, input.next().substring(0, 2).toUpperCase());

                System.out.print("Enter NEW Phone Number : ");
                pst.setString(6, input.next());

                pst.setInt(7, fetchId);

                // EXECUTES THE QUERY STATEMENT
                pst.executeUpdate();
            }
            else{
                // IF THE LIST IS EMPTY
                System.out.println("No available rows");
            }

        }catch(Exception e){
            // CATCH ANY EXCEPTION
            e.printStackTrace();
        }finally {
            // CLOSE EVERYTHING SQL RELATED
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
