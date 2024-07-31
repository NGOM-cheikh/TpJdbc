
// Tp
package fr.digi.m052024;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestSelect {
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/compta";
    private static final String DD_USER = "root";
    private static final String DD_PWD = "Fatou2022&";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Charger le driver JDBC
            Class.forName("org.mariadb.jdbc.Driver");
            // Établir la connexion
            connection = DriverManager.getConnection(DB_URL, DD_USER, DD_PWD);
            // Préparer la requête SQL de sélection
            String sql = "SELECT id, nom FROM fournisseur";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            List<Fournisseur> fournisseurs = new ArrayList<>();

            // Parcourir les résultats et créer des objets Fournisseur
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                fournisseurs.add(new Fournisseur(id, nom));
            }

            // Afficher les fournisseurs
            for (Fournisseur fournisseur : fournisseurs) {
                System.out.println(fournisseur);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermer les ressources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
