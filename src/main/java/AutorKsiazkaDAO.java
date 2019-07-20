import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AutorKsiazkaDAO {
    private final static String sqlInsert = "INSERT INTO autor_ksiazka(`idautor`,`ksiazka`) VALUES (?,?)";

    public void newAutorKsiazka(Autor autor, Ksiazka ksiazka) {
        try (Connection connection = DriverManager.getConnection(DBPropertie.getUrl(), DBPropertie.getUser(), DBPropertie.getPassword());
             PreparedStatement prStmt = connection.prepareStatement(sqlInsert);
        ) {
            prStmt.setInt(1, autor.getId());
            prStmt.setInt(2, ksiazka.getId());
            prStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
