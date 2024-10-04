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
        try {
            String studID;
            String afviklingsID;
            String karakter;
            System.out.println("Indtast studeID:");
            studID = scan.next();
            System.out.println("indtast afviklingsID for eksamen:");
            afviklingsID = scan.next();
            System.out.println("indtast gyldig karakter eller adminstrativ bedømmelse:");
            karakter = scan.next();



        String sqlStatement = "INSERT INTO Eksamensforsøg VALUES (?, ?, ?)";
        PreparedStatement prestmt = minConnection.prepareStatement(sqlStatement);
            prestmt.setString(1, karakter);
            prestmt.setInt(2, Integer.parseInt(studID));
            prestmt.setInt(3, Integer.parseInt(afviklingsID));

        prestmt.execute();

            System.out.println("Studerende tilmeldt eksamensafvikling / Eksamensforsøg oprettet");
        }
        catch (SQLException e){
            System.out.printf(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

