package fr.digi.m052024;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDelete {
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/compta";
    private static final String DD_USER = "root";
    private static final String DD_PWD = "Fatou2022&";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Charger le driver JDBC
            Class.forName("org.mariadb.jdbc.Driver");
            // Établir la connexion
            connection = DriverManager.getConnection(DB_URL, DD_USER, DD_PWD);
            // Préparer la requête SQL de suppression
            String sql = "DELETE FROM fournisseur WHERE nom = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "La Maison des Peintures");
            // Exécuter
            int rowsAffected = statement.executeUpdate();
            System.out.println("Nombre de lignes supprimées : " + rowsAffected);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermer
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
