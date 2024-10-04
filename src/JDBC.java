import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class JDBC {
    private static Connection minConnection;
    private static Scanner scan = new Scanner(System.in);

    static {
        try {
            minConnection = DriverManager
                    .getConnection("jdbc:sqlserver://localhost\\SQLExpress;databaseName=Karakterregistrering;user=sa;password=2057;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
    opgA();
    opgB();
    opgC();
    }

    public static void opgA() {
        //parameter for eksamensforsøg
        try {
            String studID;
            String afviklingsID;
            String karakter;
            System.out.println("Indtast studieID:");
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
            if (e.getErrorCode() == 50001) {
                System.out.printf(e.getMessage());
            }
            else if (e.getErrorCode() == 547 && e.getMessage().contains("column 'ID'")){
                System.out.println("Indtast gyldigt afviklingsID");
            }
            else if (e.getErrorCode() == 547 && e.getMessage().contains("column 'karakter'")){
                System.out.println("Indtast gyldig karakter/administrativ bedømmelse");
            }
        } catch (NumberFormatException e){
            System.out.println(e.getMessage());
            System.out.println("you don fucked up");
        }
    }

    public static void opgB(){
        try {
            String termin;
            String startDato;
            String slutDato;
            String eksamensID;
            System.out.println("Indtast termin: ");
            termin = scan.next();
            System.out.println("indtast startdato på formen YYYY-MM-DD: ");
            startDato = scan.next();
            System.out.println("indtast slutdato på formen YYYY-MM-DD: ");
            slutDato = scan.next();
            System.out.println("indtast eksamensID: ");
            eksamensID = scan.next();

            String sqlStatement = "INSERT INTO Eksamensafvikling VALUES (?, ?, ?, ?)";
            PreparedStatement prestmt = minConnection.prepareStatement(sqlStatement);
            prestmt.setString(1, termin);
            prestmt.setString(2, startDato);
            prestmt.setString(3, slutDato);
            prestmt.setString(4, eksamensID);

            prestmt.execute();
            System.out.println("Oprettet eksamensafvkling for eksamen: " + eksamensID);
        } catch (SQLException e){
            System.out.println(e.getErrorCode());
            System.out.println(e.getMessage());
            if (e.getErrorCode() == 2628 && e.getMessage().contains("column 'termin'")){
                System.out.println("Termin input kan kun være på 5 karakter, f.eks. S2025");
            }
            else if (e.getErrorCode() == 241){
                System.out.println("Forkert dato format");
            }
            else if (e.getErrorCode() == 547 && e.getMessage().contains("column 'navn'")){
                System.out.println("Indtast gyldig eksamensID");
            }
        }
    }

    public static void opgC(){
        try {
            System.out.println("Indtast termin: ");
            String termininput = scan.next();
            System.out.println("Indtast eksamen: ");
            String eksamensinput = scan.next();

            String sqlStatement = "EXEC opgC ?,?";
            PreparedStatement prestmt = minConnection.prepareStatement(sqlStatement);
            prestmt.setString(1,termininput);
            prestmt.setString(2,eksamensinput);

            ResultSet res = prestmt.executeQuery();
            System.out.println("ID:\tNavn:\tKarakter:\t");
            while(res.next()){
                int id = res.getInt(1);
                String navn = res.getString(2);
                String karakter = res.getString(3);

                System.out.println(id + "\t" + navn+ "\t" + karakter+ "\t");
            }
        } catch (SQLException e) {

        }
    }
}

