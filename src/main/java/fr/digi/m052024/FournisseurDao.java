

package fr.digi.m052024;
import java.util.List;

public interface FournisseurDao {
    List<Fournisseur> extraire();
    void insert(Fournisseur fournisseur);
    int update(String ancienNom, String nouveauNom);
    boolean delete(Fournisseur fournisseur);
}
