

package fr.digi.m052024;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDaoJdbc implements FournisseurDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/compta";
    private static final String DD_USER = "root";
    private static final String DD_PWD = "Fatou2022&";

    @Override
    public List<Fournisseur> extraire() {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        String query = "SELECT id, nom FROM fournisseur";
        try (Connection connection = DriverManager.getConnection(DB_URL, DD_USER, DD_PWD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                fournisseurs.add(new Fournisseur(id, nom));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fournisseurs;
    }

    @Override
    public void insert(Fournisseur fournisseur) {
        String query = "INSERT INTO fournisseur (nom) VALUES (?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DD_USER, DD_PWD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, fournisseur.getNom());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int update(String ancienNom, String nouveauNom) {
        String query = "UPDATE fournisseur SET nom = ? WHERE nom = ?";
        int rowsAffected = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DD_USER, DD_PWD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, nouveauNom);
            preparedStatement.setString(2, ancienNom);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public boolean delete(Fournisseur fournisseur) {
        String query = "DELETE FROM fournisseur WHERE id = ?";
        boolean success = false;
        try (Connection connection = DriverManager.getConnection(DB_URL, DD_USER, DD_PWD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, fournisseur.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
}
