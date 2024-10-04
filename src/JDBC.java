import java.sql.*;
import java.util.ArrayList;

public class JDBC {
    private static Connection minConnection;

    static {
        try {
            minConnection = DriverManager
                    .getConnection("jdbc:sqlserver://localhost\\SQLExpress;databaseName=KarakterRegistrering;user=sa;password=Erenbaran19;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

