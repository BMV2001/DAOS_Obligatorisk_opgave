import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class JDBC {
    private static Connection minConnection;
    private static Scanner scan = new Scanner(System.in);

    static {
        try {
            minConnection = DriverManager
                    .getConnection("jdbc:sqlserver://localhost\\SQLExpress;databaseName=DAOS_Projekt;user=sa;password=010401;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {
    opgA();

    }

    public static void opgA() throws SQLException {
        //parameter for eksamensforsøg
        String studID;
        String afviklingsID;
        System.out.println("Indtast venligst et studieID(tal ID) for en studerende, og en afvklings ID(tal ID) for den tilknyttede eksamen:");
        studID = scan.next();
        afviklingsID = scan.next();

        String sqlStatement = "INSERT INTO Eksamensforsøg VALUES (?, ?, ?)";
        PreparedStatement prestmt = minConnection.prepareStatement(sqlStatement);
        prestmt.setInt(2, Integer.parseInt(studID));
        prestmt.setInt(3, Integer.parseInt(afviklingsID));

        prestmt.execute();

        System.out.println("Studerende tilmeldt eksamensafvikling / Eksamensforsøg oprettet");

    }
}

