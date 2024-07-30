

//tp num 1

package fr.digi.m052024;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    // Informations de connexion
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/compta";
    private static final String DD_USER = "root";   // Remplacez par votre utilisateur
    private static final String DD_PWD = "Fatou2022&"; // Remplacez par votre mot de passe

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Charger le driver MariaDB JDBC
            Class.forName("org.mariadb.jdbc.Driver");

            // Établir la connexion
            connection = DriverManager.getConnection(DB_URL, DD_USER, DD_PWD);

            // Vérifier la connexion
            if (connection != null) {
                System.out.println("Connexion réussie  !");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Driver non trouvé !");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur de connexion !");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Connexion fermée !");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erreur lors de la fermeture de la connexion !");
            }
        }
    }
}
