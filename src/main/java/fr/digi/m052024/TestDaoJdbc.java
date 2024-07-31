

package fr.digi.m052024;


public class TestDaoJdbc {

    public static void main(String[] args) {
        FournisseurDao fournisseurDao = new FournisseurDaoJdbc();


        Fournisseur fournisseur = new Fournisseur(0, "France de matériaux");
        fournisseurDao.insert(fournisseur);


        System.out.println("Liste des fournisseurs après insertion :");
        fournisseurDao.extraire().forEach(System.out::println);

        fournisseurDao.update("France de matériaux", "France matériaux");

        System.out.println("Liste des fournisseurs après modification :");
        fournisseurDao.extraire().forEach(System.out::println);

        fournisseur = fournisseurDao.extraire().stream()
                .filter(f -> "France matériaux".equals(f.getNom()))
                .findFirst()
                .orElse(null);
        if (fournisseur != null) {
            fournisseurDao.delete(fournisseur);
        }

        System.out.println("Liste des fournisseurs après suppression :");
        fournisseurDao.extraire().forEach(System.out::println);

        Fournisseur fournisseurWithQuote = new Fournisseur(0, "L'Espace Création");
        fournisseurDao.insert(fournisseurWithQuote);

        System.out.println("Liste des fournisseurs après insertion avec quote :");
        fournisseurDao.extraire().forEach(System.out::println);
    }
}
