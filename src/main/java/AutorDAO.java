import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {
    private final String sqlInsert = "INSERT INTO autor(`imie`,`nazwisko`) VALUES (?,?)";
    private final String sqlFindAutorById = "SELECT * FROM autor WHERE id= ? ";
    private final String sqlFindMaxId = "SELECT max(id) FROM autor ";
    private final String sqlFindBooksFromAutor = "SELECT * FROM ksiazka WHERE id IN (SELECT ak.ksiazka FROM autor_ksiazka ak WHERE ak.idautor = ?)";

    public void newAutor(Autor nowyAutor) {
        try (Connection connection = DriverManager.getConnection(DBPropertie.getUrl(), DBPropertie.getUser(), DBPropertie.getPassword());
             PreparedStatement prStmt = connection.prepareStatement(sqlInsert);
        ) {
            prStmt.setString(1, nowyAutor.getImie());
            prStmt.setString(2, nowyAutor.getNazwisko());
            prStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Autor findById(int id) {
        Autor wynik = new Autor();
        try (Connection connection = DriverManager.getConnection(DBPropertie.getUrl(), DBPropertie.getUser(), DBPropertie.getPassword());
             PreparedStatement prStmt = connection.prepareStatement(sqlFindAutorById);
        ) {
            prStmt.setInt(1, id);
            ResultSet resultSet = prStmt.executeQuery();
            while (resultSet.next()) {
                wynik.setId(resultSet.getInt("id"));
                wynik.setImie(resultSet.getString("imie"));
                wynik.setNazwisko(resultSet.getString("nazwisko"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wynik;
    }

    public Autor getLastAutor() {
        Autor wynik = null;
        try (Connection connection = DriverManager.getConnection(DBPropertie.getUrl(), DBPropertie.getUser(), DBPropertie.getPassword());
             Statement stmt = connection.createStatement();
        ) {
            ResultSet resultSet = stmt.executeQuery(sqlFindMaxId);
            resultSet.next();
            int maxId = resultSet.getInt(1);
            wynik = findById(maxId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wynik;
    }

    public List<Ksiazka> findAllBooksAutors(Autor autor) {
        List<Ksiazka> wynik = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DBPropertie.getUrl(), DBPropertie.getUser(), DBPropertie.getPassword());
             PreparedStatement prstmt = connection.prepareStatement(sqlFindBooksFromAutor);
        ) {
            prstmt.setInt(1, autor.getId());
            ResultSet resultSet = prstmt.executeQuery();
            while (resultSet.next()) {
                wynik.add(new Ksiazka(resultSet.getInt("id"), resultSet.getString("tytul")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wynik;
    }

    public boolean checkAutorBooks(Autor autor, String titleBook) {
        List<Ksiazka> ksiazki = findAllBooksAutors(autor);
        for(Ksiazka ksiazka:ksiazki){
            if(ksiazka.getTytul().equalsIgnoreCase(titleBook)){
                return true;
            }
        }
        return false;
    }
}
