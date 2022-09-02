import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Class {
    public static void main(String[] args) throws IOException, SQLException {

        System.out.print("Host to Connect: ");
        String host = readLine();

        int port;

        while (true) {
            try {
                System.out.print("Port to Connect: ");
                String portStr = readLine();
                port = Integer.parseInt(portStr);
                break;
            } catch (Exception e) {
                System.out.println("Invalid Port");
            }
        }

        System.out.print("UserID to Connect: ");
        String userId = readLine();

        System.out.print("Password to Connect: ");
        String password = readPassword();

        System.out.println("Connecting...");

        String connectionUrl = "jdbc:sqlserver://" + host + ":" + port + ";user=" + userId + ";password=" + password + ";encrypt=true;trustServerCertificate=true";
        Connection con = DriverManager.getConnection(connectionUrl);


        System.out.println("Connect Successfully!");
        System.out.println("Try to query: SELECT 1");


        try {
            Statement stmt = con.createStatement();
            String SQL = "SELECT 1;";
            stmt.execute(SQL);
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
            return;
        }


        while (true)
        {
            System.out.print("Query: ");
            String query = readLine();

            System.out.println("Try to query: " + query);

            try
            {
                Statement command = con.createStatement();
                command.execute(query);

                System.out.println("Query succeed!");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static String readLine() throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);

        String a1 = br.readLine();
        return a1;
    }

    public static String readPassword() throws IOException {
        Console cnsl = System.console();

        if (cnsl == null) {
            System.out.println();
            System.out.println("WARNING: System.console() is null! Password not masking");
            return readLine();
        } else {
            return String.valueOf(cnsl.readPassword());
        }
    }
}
