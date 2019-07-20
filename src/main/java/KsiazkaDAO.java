//klasa DAO, rozwiazanie podzadania 1 i 2

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KsiazkaDAO {

    private static final String sqlFindBookById = "SELECT id, tytul FROM ksiazka WHERE id= ? ";
    private final static String sqlInsert = "INSERT INTO ksiazka(`tytul`) VALUES (?)";
    private static String sqlUpdate = "UPDATE ksiazka SET `tytul` = ? WHERE `id` = ?";
    private static String sqlDelete = "DELETE FROM ksiazka WHERE `id` = ?";
    private static final String sqlFindAll = "SELECT id, tytul FROM ksiazka ";
    private static final String sqlFindMaxId = "SELECT max(id) FROM ksiazka ";

    public Ksiazka findById(int id) {
        Ksiazka wynik = new Ksiazka();
        try (Connection connection = DriverManager.getConnection(DBPropertie.getUrl(), DBPropertie.getUser(), DBPropertie.getPassword());
             PreparedStatement prStmt = connection.prepareStatement(sqlFindBookById);
        ) {
            prStmt.setInt(1, id);
            ResultSet resultSet = prStmt.executeQuery();
            while (resultSet.next()) {
                wynik.setId(resultSet.getInt("id"));
                wynik.setTytul(resultSet.getString("tytul"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wynik;
    }

    public void newBook(Ksiazka nowaKsiazka) {
        try (Connection connection = DriverManager.getConnection(DBPropertie.getUrl(), DBPropertie.getUser(), DBPropertie.getPassword());
             PreparedStatement prStmt = connection.prepareStatement(sqlInsert);
        ) {
            prStmt.setString(1, nowaKsiazka.getTytul());
            prStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ubdateById(Ksiazka nowaKsiazka) {
        try (Connection connection = DriverManager.getConnection(DBPropertie.getUrl(), DBPropertie.getUser(), DBPropertie.getPassword());
             PreparedStatement prStmt = connection.prepareStatement(sqlUpdate);
        ) {
            prStmt.setString(1, nowaKsiazka.getTytul());
            prStmt.setInt(2, nowaKsiazka.getId());
            prStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        try (Connection connection = DriverManager.getConnection(DBPropertie.getUrl(), DBPropertie.getUser(), DBPropertie.getPassword());
             PreparedStatement prStmt = connection.prepareStatement(sqlDelete);
        ) {
            prStmt.setInt(1, id);
            prStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ksiazka> findAll() {
        List<Ksiazka> wynik = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DBPropertie.getUrl(), DBPropertie.getUser(), DBPropertie.getPassword());
             Statement stmt = connection.createStatement();
        ) {
            ResultSet resultSet = stmt.executeQuery(sqlFindAll);
            while (resultSet.next()) {
                wynik.add(new Ksiazka(resultSet.getInt("id"), resultSet.getString("tytul")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wynik;
    }

    public Ksiazka getLastBook() {
        Ksiazka wynik = null;
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
}
